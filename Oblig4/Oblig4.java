import java.util.Scanner;
import java.util.Vector;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Oblig4 {

    public static void main(String[] args) {
	int threadCnt;
	String infile;
	int wordCnt = 0;

	Vector<String> data = new Vector<String>();

	if (args.length < 3) {
	    System.out.println("Too few arguments");
	    System.exit(0);
	}
	
	threadCnt = Integer.parseInt(args[0]);

	/**
	 * Read file.
	 **/
	try {
	    Scanner readfile = new Scanner(new File(args[1]));

	    int y = 0;

	    while (readfile.hasNext()) {
		String line = readfile.nextLine().trim();
		
		if (line.equals(""))
		    continue;
		
		if (y == 0) {
		    wordCnt = Integer.parseInt(line);
		    y = y + 1;
		    continue;
		}

		data.add(line);

		y = y + 1;
	    }

	    if (wordCnt != data.size()) {
		System.out.println("Word count does not match totall words in the file");
		System.exit(0);
	    }

	} catch (FileNotFoundException ee) {
	    System.err.println("File format is wrong");
	}
	
	/**
	 * Creates monitor and sort and write to the given outfile.
	 **/
	Monitor mon = new Monitor(wordCnt, threadCnt, data);
	mon.startThreads();
	mon.mergeResults();
	String[] sortedData = mon.getSortedData();
	
	try {
	    PrintStream outfile = new PrintStream(args[2]);
	    
	    outfile.println(sortedData.length);
	    
	    for (int i = 0; i < sortedData.length; i++) {
		outfile.println(sortedData[i]);
	    }
	} catch (FileNotFoundException ee) {
	    System.out.println("File format is wrong");
	}
    }    
}