import java.util.Vector;

public class MyThread extends Thread {
    int wordCnt;
    int id;
    Vector<String> data;
    Monitor mon;

    /** Constructor
     * @param wordCnt
     *        Totall number of words.
     * @param data
     *        elements to be sorted.
     * @param id
     *        Thread's id.
     * @param mon
     *        it's monitor.
     */
    MyThread(int wordCnt, Vector<String> data, int id, Monitor mon) {
	this.wordCnt = wordCnt;
	this.data = data;
	this.id = id;
	this.mon = mon;
    }

    /** QuickSort
     * @param elements
     *        a vector of elements
     * @return 
     *       a vector of sorted elements
     * algorithm: choose a pivot.
     * moves each element into an upper
     * and lower Half array wether the element
     * is smaller or bigger than the pivot.
     * Recursively quickSort upper and lower half.
     * concatenate upper and lowerHalf.
     * Each element of the input are invariant.
     * They are not changed through the process.
     * Except their position of index in the array.
     */
    public Vector<String> quickSort(Vector<String> elements) {
	if (elements.size() <= 1)
	    return elements;

	String pivot = elements.get(0);
	
	Vector<String> lowerHalf = new Vector<String>();
	Vector<String> upperHalf = new Vector<String>();
	
	for (int i = 0; i < elements.size(); i++) {
	    if (elements.get(i).compareTo(pivot) < 0) {
		lowerHalf.add(elements.get(i));
	    
	    } else if (elements.get(i).compareTo(pivot) > 0) {
		upperHalf.add(elements.get(i));
	    }
	}

	return concatenate(quickSort(lowerHalf), pivot, quickSort(upperHalf));
	
    }

    /** Concatenate two vectors
     * @param lower
     *        lowerHalf vector
     * @param upper
     *        upperHalf vector
     * @return vector<String>
     *        concatenated vector.
     */
    private Vector<String> concatenate(Vector<String> lower, String pivot, Vector<String> upper) {	
	int size1 = lower.size();
	int size2 = upper.size();
	
	Vector<String> ret = new Vector<String>();
	
	for (int i = 0; i < size1; i++) {
	    ret.add(lower.get(i));
	}
	
	ret.add(pivot);

	for (int j = 0; j < size2; j++) {
	    ret.add(upper.get(j));
	}

	return ret;
    }

    /** Run quickSort
     */
    public void run() {
	data = quickSort(data);
    }
    
    /**
     * @return data
     */
    public Vector<String> getData() {
	return data;
    }
    
    /** Print data
     */
    public void print() {
	for (int i = 0; i < data.size(); i++) {
	    System.out.println(data.get(i));
	}
    }
}