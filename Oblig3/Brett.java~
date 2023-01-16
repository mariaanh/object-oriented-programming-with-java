import java.io.FileNotFoundException;
import java.io.File;
import java.io.PrintWriter;
import java.io.PrintStream;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Vector;

public class Brett {

    public int dimension;
    public int boxRow;
    public int boxColumn;
    public Rute[][] myBoard;

    private Kolonne[] columns;
    private Rad[] rows;
    private Boks[][] boxs;
    public SudokuBuffer bufferSolutions = new SudokuBuffer();
    
    /** Initiate all the things needed for solving
     * the board.
     */
    public void initiate(Scanner file) {
	readFile(file);
	fillColumnsAndRows();
	fillBoxs();
	giveColumnsRowsBoxsToRute();
	setNextToRute();
	giveItSelfToRute();

    }
    
    /**
     * Print solutions to file.
     */
    public void printToFile(PrintStream outfile ) {
	ArrayList<int[][]> sol = bufferSolutions.getBuffer();
	
	if (!sol.isEmpty()) {
	    for (int k = 0; k < sol.size(); k++) {
	  	int[][] solution = sol.get(k);
		
		outfile.print(k + ": ");
		
	  	for (int i = 0; i < dimension; i++) {
	  	    for (int j = 0; j < dimension; j++) {
	  		outfile.print(solution[i][j]);
	 	    }
	  	    outfile.print("\\"+"\\" + " ");
	  	}

		outfile.println();
	    }
	} else {
	    outfile.print("no solutions");
	}
    }
    
    /** Set a tail to each route, that is link the all the rutes
     */
    public void setNextToRute() {
	
 	Rute prev = null;	    
	for (int i = 0; i < dimension; i++) {
	    for (int j = 0; j < dimension; j++) {
		if (prev != null) {
		    prev.setRuteNext(myBoard[i][j]);
		}

		prev = myBoard[i][j];
	    }
	}
    }
    /** Give this board as a pointer to rute.
     */
    public void giveItSelfToRute() {
	for (int i = 0; i < dimension; i++) {
	    for (int j = 0; j < dimension; j++) {
		myBoard[i][j].setMyBoard(this);
	    }
	}
    }
    /** Give Columns, Rows and Boxs as pointer to Rutes
     */
    public void giveColumnsRowsBoxsToRute() {
	for (int i = 0; i < dimension; i++) {
	    for (int j = 0; j < dimension; j++) {
		myBoard[i][j].setMyColumn(columns[j]);
		myBoard[i][j].setMyRow(rows[i]);
		myBoard[i][j].setMyBox(boxs[i/boxRow][j/boxColumn]);
	    }
	}	
    }
    
    /** Solve the sudoku board
     */
    public void solve() {
	myBoard[0][0].settTallMegOgResten(); 
    }

    /** Store solution in sudokubuffer.
     */
    public void saveSolution() {
	int[][] s = new int[dimension][dimension];

	for (int i = 0; i < dimension; i++) {
	    for (int j = 0; j < dimension; j++) {
		s[i][j] = myBoard[i][j].getData();
	    }
	}

	bufferSolutions.add(s);
	
    }

    /** Fill in values in boxs
     */
    public void fillBoxs() {
	boxs = new Boks[dimension/boxRow][dimension/boxColumn];
	int counter = 0;

	for (int i = 0; i < dimension/boxRow; i++) {
	    for (int j = 0; j < dimension/boxColumn; j++) {
		boxs[i][j] = new Boks(counter);
		boxs[i][j].setSize(boxRow, boxColumn);
		counter++;
	    }
	}
	
	for (int i = 0; i < dimension; i++) {
	    for (int j = 0; j < dimension; j++) {
		boxs[i/boxRow][j/boxColumn].setRute(i%boxRow, j%boxColumn, myBoard[i][j]);
	    }
	    
	}
	
    }
    /** Fills in values in columns and rows.
     */
    public void fillColumnsAndRows() {
	columns = new Kolonne[dimension];
	rows = new Rad[dimension];

	for (int i = 0; i < dimension; i++) {
	    columns[i] = new Kolonne(i);
	    columns[i].setSize(dimension);
	    rows[i] = new Rad(i);
	    rows[i].setSize(dimension);
	}
	
	for (int i = 0; i < dimension; i++) {
	    for (int j = 0; j < dimension; j++) {
		columns[j].setRute(i, myBoard[i][j]);
		rows[i].setRute(j, myBoard[i][j]);
	    } 
	}
    }
    
    /** get scanner file.
     */
    public Scanner getScannerFile(String filename) {
	try {
	    Scanner file = new Scanner(new File(filename));
	    return file;
	} catch (FileNotFoundException e) {
	    System.err.println("An error occured when attempt to open file:\n");
	    System.err.println(filename + "\n");
	    System.exit(1);
	}
	
	return null;
    }
    
    /** Read file.
     */
    public void readFile(Scanner file) {
	int y = 0;
	
	while (file.hasNext()) {
	    String line = file.nextLine().trim();
	    
	    if (line.equals(""))
		continue;
	    
	    String[] words = line.split("");
	    Vector<String> data = new Vector<String>();

	    for (String value : words) {
		if (value.equals(""))
		    continue;

		if (y == 0)
		    dimension = Integer.parseInt(value);

		if (y == 1)
		    boxRow = Integer.parseInt(value);

		if (y == 2) 
		    boxColumn = Integer.parseInt(value);
		
		data.add(value);
	    }

	    if (y == 2) {

		myBoard = new Rute[dimension][dimension];
		for (int i = 0; i < dimension; i++) {
		    for (int j = 0; j < dimension; j++) {
			myBoard[i][j] = new Rute(0);
		    }
		}
	    }

	    for (int x = 0; x < data.size(); x++) {
	      	if (y > 2) {
	     	    if (!data.get(x).equals(".")) {
	     		if ((int)data.get(x).charAt(0) >= 65) {
			    int current = (int)data.get(x).charAt(0) - 55;
	     		    myBoard[y - 3][x].setData(current);
	     		    myBoard[y - 3][x].setFixed();
	     		} else {
	     		    myBoard[y - 3][x].setData(Integer.parseInt(data.get(x)));
	     		    myBoard[y - 3][x].setFixed();
	     		}
	     	    }
	     	}
	    }
	   	    
	    y = y + 1;
	    
	}

    }

    
    
}

/** SudokuBuffer to store solutions
 */
class SudokuBuffer {
    int count;
    ArrayList<int[][]> buffer = new ArrayList<int[][]>();

    void add(int[][] solution) {
	if (count < 500)
	    buffer.add(solution);
	count = count + 1;
    }

    ArrayList getBuffer() {
	return buffer;
    }
}