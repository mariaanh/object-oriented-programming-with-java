import java.util.Vector;
import java.util.LinkedList;

public class Monitor {
    Vector <String> data;
    int wordCnt;
    int threadCnt;

    String[] sortedData;
    LinkedList<MyThread> threads = new LinkedList<MyThread>();

    /** Constructor
     * @param wordCnt
     * @param threadCnt
     * @param data
     */
    Monitor (int wordCnt, int threadCnt, Vector<String> data) {
	this.wordCnt = wordCnt;
	this.data = data;
	this.threadCnt = threadCnt;
    }

    /** Start all threads
     */
    public void startThreads() {
	for (int i = 0; i < threadCnt; i++) {
	    
	    int size = ((((i + 1)*wordCnt)/threadCnt) + 1) - ((i*wordCnt)/threadCnt) - 1;
	    Vector<String> elements = new Vector<String>();
	    
	    for (int j = 0; j < size; j++) {
		elements.add(data.get(0));
		data.remove(0);
	    }
	    
	    MyThread thread = new MyThread(size, elements, i, this);
	    thread.start();
	    threads.add(thread);
	}
    }

    /** Merge results from the threads
     */    
    public void mergeResults() {
	Vector<String> result = new Vector<String>();

	try {

	    for (int i = 0; i < threadCnt; i++) {
		threads.get(i).join();		
		result = merge2Vectors(result, threads.get(i).getData());
	    }

	} catch (InterruptedException ee) {
	    System.out.println("interrupted");
	}

	String[] sortedArray = new String[this.wordCnt];
	sortedData = result.toArray(sortedArray);
	
	if (sortedData.length != wordCnt)
	    System.out.println("Result is incorrect");
	
	
	for (int i = 0; i < sortedData.length; i++) {
	    if (sortedData[i] == null) 
		System.out.println("One of the elements is null");
	    
	}
    }

    /** Merge two vectors
     * @return merged vector<string>
     * @param r
     *        vector1
     * @prama r2
     *        vector2
     */
    private Vector<String> merge2Vectors(Vector<String> r, Vector<String> r2) {
	Vector<String> merged = new Vector<String>();

	while (r.size() > 0 && r2.size() > 0) {
	    String name = r.get(0);
	    String name2 = r2.get(0);
	    if (name.compareTo(name2) <= 0) {
		merged.add(name);
		r.remove(0);

	    } else {
		merged.add(name2);
		r2.remove(0);
	    }
	}

	while (r.size() > 0) {
	    merged.add(r.get(0));
	    r.remove(0);
	}

	while (r2.size() > 0) {
	    merged.add(r2.get(0));
	    r2.remove(0);
	}
	
	return merged;
    }

    /**
     * @return sortedData
     */
    public String[] getSortedData() {
	return sortedData;

    }
}