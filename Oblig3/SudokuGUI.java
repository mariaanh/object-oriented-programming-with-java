import java.io.PrintStream;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/** 
 * Tegner ut et Sudoku-brett.
 * @author Christian Tryti
 * @author Stein Gjessing
 */
public class SudokuGUI extends JFrame {

    private final int RUTE_STRELSE = 50;/* St¿rrelsen p hver rute */
    private final int PLASS_TOPP = 50;/* Plass p toppen av brettet */

    private JTextField[][] brett;   /* for  tegne ut alle rutene */
    private int dimensjon;/* st¿rrelsen p brettet (n) */
    private int vertikalAntall;/* antall ruter i en boks loddrett */
    private int horisontalAntall;/* antall ruter i en boks vannrett */

    private ArrayList <int[][]> solutions;
    private Brett sudoku;
    private JPanel brettPanel;

    /** Lager et brett med knapper langs toppen. */
    public SudokuGUI(int dim, int hd, int br) {
	dimensjon = dim;
	vertikalAntall = hd;
	horisontalAntall = br;

	brett = new JTextField[dimensjon][dimensjon];

	setPreferredSize(new Dimension(dimensjon * RUTE_STRELSE, 
				       dimensjon  * RUTE_STRELSE + PLASS_TOPP));
	setTitle("Sudoku " + dimensjon +" x "+ dimensjon );
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setLayout(new BorderLayout());

	JPanel knappePanel = lagKnapper();
	brettPanel = lagBrettet();

	getContentPane().add(knappePanel,BorderLayout.NORTH);
	getContentPane().add(brettPanel,BorderLayout.CENTER);
	pack();
	setVisible(true);
    }

    /** oppdaterer bildet med nye verdier */
    private void updateView() {
	getContentPane().remove(brettPanel);
	brettPanel = lagBrettet();
	getContentPane().add(brettPanel);
	pack();

	for (int i = 0; i < dimensjon; i++) {
	    for (int j = 0; j < dimensjon; j++) {
		int tmp = sudoku.myBoard[i][j].getData();
		char c = Character.forDigit(tmp, dimensjon + 1);
		c = Character.toUpperCase(c);
		brett[i][j].setText(Character.toString(c));
	    }
	}
    }
    /** open file */
    private class GetFilLytter implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    JFileChooser fc = new JFileChooser();
	    fc.showOpenDialog(null);
	    File file = fc.getSelectedFile();

	    try {
		Scanner f = new Scanner(file);
		sudoku = new Brett();
		sudoku.initiate(f);
		dimensjon = sudoku.dimension;
		vertikalAntall = sudoku.boxRow;
		horisontalAntall = sudoku.boxColumn;
		
		updateView();
	    } catch (FileNotFoundException ee) {	
	    } catch (NullPointerException ee) {
	    } catch (StringIndexOutOfBoundsException ee) {
		System.err.println("file format is wrong");
	    }
	}
    }

    private class FinnSolnLytter implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    sudoku.solve();
	    solutions = sudoku.bufferSolutions.getBuffer();

	    	    
	    getContentPane().remove(brettPanel);
	    brettPanel = lagBrettet();
	    getContentPane().add(brettPanel);
	    pack();
	    
	    int[][] tmpArray = solutions.get(0);
	    
	    for ( int i = 0; i < dimensjon; i++) {
		for (int j = 0; j < dimensjon; j++) {
		    int tmp = tmpArray[i][j];
		    char c = Character.forDigit(tmp, dimensjon + 1);
		    c = Character.toUpperCase(c);
		    brett[i][j].setText(Character.toString(c));
		}
	    }

	   
	}
    }
    
    private class NextSolnLytter implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    if (solutions.size() == 0) 
		return;
	    
	    getContentPane().remove(brettPanel);
	    brettPanel = lagBrettet();
	    getContentPane().add(brettPanel);
	    pack();
	    
	    int[][] tmpArray = solutions.get(0);
	    solutions.remove(tmpArray);
	    
	    for ( int i = 0; i < dimensjon; i++) {
		for (int j = 0; j < dimensjon; j++) {
		    int tmp = tmpArray[i][j];
		    char c = Character.forDigit(tmp, dimensjon + 1);
		    c = Character.toUpperCase(c);
		    brett[i][j].setText(Character.toString(c));
		}
	    }

	}
    }
    /** 
     * Lager et panel med alle rutene. 
     * @return en peker til panelet som er laget.
     */
    private JPanel lagBrettet() {
	int topp, venstre;
	JPanel brettPanel = new JPanel();
	brettPanel.setLayout(new GridLayout(dimensjon,dimensjon));
	brettPanel.setAlignmentX(CENTER_ALIGNMENT);
	brettPanel.setAlignmentY(CENTER_ALIGNMENT);
	setPreferredSize(new Dimension(new Dimension(dimensjon * RUTE_STRELSE, 
						     dimensjon * RUTE_STRELSE)));
	for(int i = 0; i < dimensjon; i++) {
	    /* finn ut om denne raden trenger en tykker linje p toppen: */
	    topp = (i % vertikalAntall == 0 && i != 0) ? 4 : 1;
	    for(int j = 0; j < dimensjon; j++) {
		/* finn ut om denne ruten er en del av en kolonne 
		   som skal ha en tykkere linje til venstre:       */
		venstre = (j % horisontalAntall == 0 && j != 0) ? 4 : 1;

		JTextField ruten = new JTextField();
		ruten.setBorder(BorderFactory.createMatteBorder
				(topp,venstre,1,1, Color.black));
		ruten.setHorizontalAlignment(SwingConstants.CENTER);
		ruten.setPreferredSize(new Dimension(RUTE_STRELSE, RUTE_STRELSE));

		if (sudoku != null) {
		    String tmp = Integer.toString(sudoku.myBoard[i][j].getData());
		    
		    ruten.setText(tmp);
		    brett[i][j] = ruten;
		    brettPanel.add(ruten);
		} else {
		    ruten.setText("0");
		    brett[i][j] = ruten;
		    brettPanel.add(ruten);
		}
	    }
	}
	return brettPanel;
    }

    /** 
     * Lager et panel med noen knapper. 
     * @return en peker til panelet som er laget.
     */
    private JPanel lagKnapper() {
	JPanel knappPanel = new JPanel();
	knappPanel.setLayout(new BoxLayout(knappPanel, BoxLayout.X_AXIS));
	JButton finnSvarKnapp = new JButton("solution");
	JButton nesteKnapp = new JButton("nextSolution");
	JButton filKnapp = new JButton("load file");
	knappPanel.add(finnSvarKnapp);
	knappPanel.add(nesteKnapp);
	knappPanel.add(filKnapp);
	GetFilLytter listener1 = new GetFilLytter();
	FinnSolnLytter listener2 = new FinnSolnLytter();
	NextSolnLytter listener3 = new NextSolnLytter();
	filKnapp.addActionListener(listener1);
	finnSvarKnapp.addActionListener(listener2);
	nesteKnapp.addActionListener(listener3);
	return knappPanel;
    }

    public static void main(String[] args) {
	if (args.length < 1)
	    new SudokuGUI(16, 4, 4);
	
	if (args.length == 1) {
	    SudokuGUI gui = new SudokuGUI(16, 4, 4);
	    try {
		Scanner f = new Scanner(new File(args[0]));
		gui.sudoku = new Brett();
		gui.sudoku.initiate(f);
		gui.dimensjon = gui.sudoku.dimension;
		gui.vertikalAntall = gui.sudoku.boxRow;
		gui.horisontalAntall = gui.sudoku.boxColumn;
		
		gui.updateView();
	    } catch (FileNotFoundException ee) {	
	    } catch (NullPointerException ee) {
	    } catch (StringIndexOutOfBoundsException ee) {
		System.err.println("file format is wrong");
	    }

	    gui.sudoku.solve();
	    gui.solutions = gui.sudoku.bufferSolutions.getBuffer();
	    
	    gui.getContentPane().remove(gui.brettPanel);
	    gui.brettPanel = gui.lagBrettet();
	    gui.getContentPane().add(gui.brettPanel);
	    gui.pack();
	    
	    int[][] tmpArray = gui.solutions.get(0);
	    
	    for ( int i = 0; i < gui.dimensjon; i++) {
		for (int j = 0; j < gui.dimensjon; j++) {
		    int tmp = tmpArray[i][j];
		    char c = Character.forDigit(tmp, gui.dimensjon + 1);
		    c = Character.toUpperCase(c);
		    gui.brett[i][j].setText(Character.toString(c));
		}
	    }
	}

	if (args.length == 2) {
	    try {
		Scanner f = new Scanner(new File(args[0]));
		Brett sudoku = new Brett();
		sudoku.initiate(f);
		sudoku.solve();
		PrintStream outfile = new PrintStream(args[1]);
		sudoku.printToFile(outfile);

	    } catch (FileNotFoundException ee) {	
	    } catch (NullPointerException ee) {
	    } catch (StringIndexOutOfBoundsException ee) {
		System.err.println("file format is wrong");
	    } 
	} 
	
	else
	    System.out.print("too many arguments");
	    
    }



    
}

