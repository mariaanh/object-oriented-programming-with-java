import java.util.Iterator;
import java.util.NoSuchElementException;

public class BeholderFEM2E <E extends SammenlignbarX2 <E>> implements EksamensSamling <E> {
    
    static protected class doubleLinkedList {
	Node<E> dummy; // forste node er en null node, en dummy.
	doubleLinkedList nextList;
	E exampleElementWE1; // eksempel element brukes til å sammenligne egenskap1
	int antE1; // antall elementer med egenskap 1

	public doubleLinkedList() {
	    dummy = null;
	    exampleElementWE1 = null;
	    nextList = null;
	}

	public doubleLinkedList(E e) {
	    exampleElementWE1 = e;
	    dummy = new Node<E>(this, null);
	    dummy.next = new Node<E>(this, e);
	    antE1++;
	}
    }

    static protected class Node {
	E elem;
	Node<E> next;
	doubleLinkedList list;
	
	public Node(doubleLinkedList list, E element) {
	    next = null;
	    elem = element;
	    this.list = list;
	}

	public Node(E element) {
	    next = null;
	    elem = element;
	}
    }
    
    private doubleLinkedList dummyList; // forste liste er en null liste, en dummy
    private int ant;
    private doubleLinkedList settListenEtterDenne;
    
    public BeholderFEM2E() {
	dummyList = new doubleLinkedList();
	ant = 0;
    }

    public void settInnElement(E e) {
	doubleLinkedList list = dummyList;
	
	// hvis det ligger ingen element i lista
	if (list.nextList == null) {
	    doubleLinkedList newlist = new doubleLinkedList(e);
	    list.nextList = newlist;
	}

	while(list.nextList != null) {
	    if (list.nextList.exampleElementWE1.sammenlignE1(e) > 0) {
		// hvis egenskap 1 ikke finnes i lista
		doubleLinkedList tail = list.nextList;
		list.nextList = new doubleLinkedList(e);
		list.nextList.nextList = tail;
		ant++;
		list.nextList.antE1++;
		break;
		
	    }
	    
	    if (list.nextList.exampleElementWE1.sammenlignE1(e) == 0) {
		// hvis egenskap 1 allerede finnes i lista.
		Node<E> node = list.nextList.dummy;
		while (node.next != null) {
		    // antar at samme element kan finnes flere ganger i lista
		    if (node.next.elem.sammenlignE2(e) >= 0) {
			Node<E> tail = node.next;
			node.next = new Node<E>(node.list, e);
			ant++;
			node.list.antE1++;
			break;
		    }
		    node = node.next;
		}
		
		break;
	    } 

	    list = list.nextList;
	}
    }

    public Node<E> finnNodenForanE(E e) {
	doubleLinkedList list = dummyList;
	
	while (list.nextList != null) {
	    
	    // hvis egenskap1 matcher
	    if (list.nextList.exampleElementWE1.sammenlignE1(e) == 0) {
		Node<E> node = list.dummy;
		
		while (node.next != null) {
		    if (node.next.elem.sammenlignE2(e) == 0) {
			settListenEtterDenne = list;
			return node;	
		    }

		    node = node.next;
		}
	    }

	    list = list.next
	}
	
	return null;
    }

    public E finnElement(E e) {
	return finnNodenForan(e).next.elem;
    }

    public boolean fjernElement(E e) {
	Node<E> node = finnNodenForan(e);
	if (node == null)
	    return false;

	if (node != null) {
	    Node<E> tail = node.next.next;
	    node.next = tail;
	    ant--;
	    node.list.antE1--;
	    
	    if (node.list.antE1 == 0) {
		settListenEtterDenne.next = settListenEtterDenne.next.next;
	    }
	}
    }

    public int antallIBeholder() {
	return ant;
    }

    public boolean fjernAlleMedEgenskap1(E e) {
	doubleLinkedList list = dummyList;

	while(list.next != null) {
	    if (list.next.exampleElementWE1.sammenlignE1(e) == 0) {
		list.next = list.next.next;
		ant = ant - list.next.antE1;
		return true;
	    }
	}

	return false;
    }

    public boolean fjernAlleMedEgenskap2(E e) {
	doubleLinkedList list = dummyList;

	while(list.next != null) {
	    Node<E> node = list.next.dummy;
	    
	    while(node.next != null) {
		if (node.next.elem.sammenlignE2(e) == 0) {
		    node.next = node.next.next;
		    if (node.next.list.antE1 == 0) {
			list.next = list.next.next;
		    }
		}
		node = node.next;
	    }

	    list = list.next;
	}

	return true;
    }

    public Iterator<E> iterator() {
	return new Iter<E>();
    }

    class Iter<E> implements Iterator<E> {
	doubleLinkedList currentList;
	doubleLinkedList preList;
	Node<E> currentNode;
	Node<E> preNode;
	int antallIgjen;
	boolean calledNext = false;
	
	public Iter() {
	    anttallIgjen = antallIbeholder;

	    if (antallIgjen > 0) {
		currentList = dummyList.nextList;
		preList = dummyList;
		currentNode = dummyList.nextList.dummy;
	    }
	}

	public hasNext() {
	    return antallIgjen != 0;
	}

	public E next() {
	    if (!hasNext()) 
		throw new NoSuchElementException;
	    
	    if (currentNode.next != null) {
		preNode = currentNode;
		currentNode = currentNode.next;
		antallIgjen--;
		calledNext = true;
		return currentNode.elem;		
	    }

	    // Nar currentNode.next == null
	    preList = currentList;
	    currentList = currentList.nextList;
	    preNode = currentList.nextList.dummy;
	    currentNode = currentList.dummy.next;
	    calledNext = true;
	    antallIgjen--;
	    return currentList.dummy.next.elem;
	}

	public void remove() {
	    if (calledNext == false)
		throw new IllegalStateException;
	    
	    currentNode.list.antE1--;
	    antIBeholder--;

	    if (currentNode.list.antE1 == 0) {
		preList.next = preList.next.next;
	    }
	    
	    preNode.next = currentNode.next;
	    calledNext = false;
	}
    }

    protected static class Stabel {
	Node<E> dummy;
	antS = 0;

	public Stabel() {
	    dummy = new Node<E>(null);
	}
    }

    private Stabel stack = new Stabel();

    public void addStack(E e) {
	if (stack.antS == 0) {
	    stack.dummy.next = new Node<E>(e);
	}
	
	Node<E> newNode = new Node<E>(e);
	newNode.next = stack.dummy.next;
	stack.dummy.next = newNode;
    }

    public void finnLøsninger() {

	Node<E> node = stack.dummy;
	
	if (node.next != null) {
	    E elementE2 = node.next.elem;
	    doubleLinkedList list = dummyList;
	    int antForskjelligeEgenskap1 = 0;
	    
	    while(node.next != 0) {
		if (node.next.elem.sammenlignE2(elementE2) == 0) {
		    node.next = node.next.next;
		}
		
		node = node.next;
	    }
	    
	    while(list.next != 0) {
		antForskjelligeEgenskap1++;
	    }
	    
	    while(list.next != 0) {
		Node<E> tmp = list.next.dummy;
		
		while(tmp.next != null) {
		    if (tmp.next.elem.sammenlign(elementE2) == 0) {
			antForskjelligeEgenskap1--;
			break;
		    }
		}

		list = list.next;
	    }
	    
	    if (antForskjelligeEgenskap1 == 0) 
		finnLøsning();

	    finnLøsninger();
	}
	
    }
    
}

interface SammenlingbarX2 <T> {
    int sammenlignE1(T e);
    int sammenlignE2(T e);
}

interface EksamensSamling <E> extends Iterable<E> {
    void settInnElement(E e);
    E finnElement(E e);
    boolean fjernAlleMedEgenskap1(E e);
    boolean fjernAlleMedEgenskap2(E e);
    int antallIBeholder();
}

