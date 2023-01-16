class A {
    int id;
    Rute[][] data;
    String type;
    int rsize;
    int csize;

    A(int id) {
	this.id = id;
    }
    
    /** @param size set size of the object.
     * (column or row).
     */
    void setSize(int size) {
	data = new Rute[size][1];
	this.rsize = size;
	this.csize = 1;
    }

    /** @param size set size of box
     */
    void setSize(int rsize, int csize) {
	data = new Rute[rsize][csize];
	this.rsize = rsize;
	this.csize = csize;
    }

    /** Set rute for column or row
     */
    void setRute(int index, Rute r) {
	if (index > rsize) {
	    throw new IndexOutOfBoundsException();
	}

	data[index][0] = r;
    }

    /** Set rute for box
     */
    void setRute(int rindex, int cindex, Rute r) {
	if (rindex > rsize || cindex > csize) 
	    throw new IndexOutOfBoundsException();

	data[rindex][cindex] = r;
    }

    /** @return data
     */
    Rute[][] getData() {
	return data;
    }

    /** @param v.
     * @return true if v is valid in
     * its container. Otherwise false.
     */
    boolean validValue(int v) {
	int i;
	int j;
      
	for (i = 0; i < rsize; i++) 
	    for (j = 0; j < csize; j++) 
		if (data[i][j].getData() == v)
		    return false;

	return true;
	
    }
}
