abstract class Ruteabstract {
    private int data;
    private Rute next;

    public Kolonne myColumn;
    private Rad myRow;
    private Boks myBox;
    private Brett myBoard;

    private boolean fixed = false;
    
    /** Set data
     */
    public boolean setData(int data) {
	if (!fixed) {
	    this.data = data;
	    return true;
	} else 
	    return false;
    }

    /** Change fixed to true.
     */
    public void setFixed() {
	fixed = true;
    }

    /** Get next rute.
     */
    public Rute getNext() {
	return next;
    }
    
    /** Get data
     */
    public int getData() {
	return data;
    }

    /** ask whether rute is fixed.
     */
    public boolean isFixed() {
	return fixed;
    }
    
    /** Set my board
     */
    public void setMyBoard(Brett b) {
	this.myBoard = b;
    }

    /** Set rute next
     */
    public void setRuteNext(Rute newnext) {
	this.next = newnext;
    }

    /** set my column
     */
    public void setMyColumn(Kolonne c) {
	this.myColumn = c;
    }

    /** Set my row
     */
    public void setMyRow(Rad r) {
	this.myRow = r;
    }

    /** set my box
     */
    public void setMyBox(Boks b) {
	this.myBox = b;
    }
    
    /** Check if value is valid in its column
     * row and box.
     */
    public boolean validValue(int v) {
	if (myColumn.validValue(v) == false ||
	    myBox.validValue(v) == false ||
	    myRow.validValue(v) == false)

	    return false;
	
	return true;
    }

    /** As described by the assignment.
     */
    public void settTallMegOgResten() {
	if (!fixed) {
	    for ( int i = 1; i <= myBoard.dimension; i++) {
		if (validValue(i)) {
		    data = i;
		    if (next == null) {
			myBoard.saveSolution();
	
		    } else {
			next.settTallMegOgResten();
		    }
		    
		}
	    }
	    
	    data = 0;

	} else {
	    if (next != null) {
		next.settTallMegOgResten();
		
	    } else {
		myBoard.saveSolution();
	    }
	} 
    }
}
