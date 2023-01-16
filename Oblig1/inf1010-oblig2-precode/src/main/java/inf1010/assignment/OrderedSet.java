package inf1010.assignment;

import java.util.Iterator;
import java.util.Arrays;
import java.util.NoSuchElementException;

import inf1010.lib.two.IfiCollection;

/** A generic linked set */
public class OrderedSet<E extends Comparable<? super E>> implements
		IfiCollection<E> {

    protected static class Node<E> {
	public Node<E> next;
	public E elem;

	public Node(E element) {
	    this.next = null;
	    this.elem = element;
	}
    }

    /**
     * Iterator class for this list.
     * See Java.util.Iterator<E> for details.
     */
    protected static class Iter<E> implements Iterator<E> {
	Node<E> current;
	boolean calledNext = false;
        Node<E> preNode;

	public Iter(Node<E> node) {
	    this.current = node;
	}
	
	public boolean hasNext() {
	    return this.current.next != null;
	}
	
	public E next() {
	    if (!this.hasNext())
		throw new NoSuchElementException();
	    
	    preNode = this.current;
	    this.current = this.current.next;
	    this.calledNext = true;
	    
	    return this.current.elem;
	}
	
	public void remove() {
	    if (this.calledNext == false) 
		throw new IllegalStateException();
	    
	    this.preNode.next = current.next;
	    this.current = preNode; 
	    this.calledNext = false;
	}
    }

    protected Node<E> dummy;
    protected int size;

    /**
     * Returns an empty Linked list.
     */
    public OrderedSet() {
	this.dummy = new Node<E>(null);
	this.size = 0;
    }

    /**
     * Add an element to the list.
     * @throws NullPointerException is the element is null.
     *
     * @param e element to add to this list.
     *
     * @returns {@code true} is the element is added, otherwise
     * {@code false}
     */
    public boolean add(E e) {
	Node<E> node;
	
	if (e == null)
	    throw new NullPointerException();
	
	node = this.dummy;
	
	while (node.next != null) {
	    if (node.next.elem.compareTo(e) > 0) {
		Node<E> tail = node.next;
		node.next = new Node<E>(e);
		node.next.next = tail;
		this.size += 1;
		return true;
	    } else if (node.next.elem.compareTo(e) == 0) {
		return false;
	    } else {
		node = node.next;
	    }
	}

	node.next = new Node<E>(e);
	this.size += 1;
	return true;
    }

    /**
     * Check whether element is in the list.
     * @param e element.
     *
     * @returns true is it does.
     * @returns false if element is not in the list.
     *
     * @throws NullPointerException is element is null.
     */    
    public boolean contains(E e) {
	Node node = this.dummy;

	if (e == null)
	    throw new NullPointerException();
	
	for (E element : this) { 
	    if (element.compareTo(e) == 0)
		return true;
	    node = node.next;
	}

	return false;	    
    }
    
    /**
     * Remove an element from the list.
     * @param e element.
     *
     * @returns {@code true} if element is in the list,
     * otherwise {@code false }. and add 1 to the size.
     */
    public boolean remove(E e) {
	Node<E> node;

	if (e == null)
	    throw new NullPointerException();
	
	node = this.dummy;

	while (node.next != null) {
	    if (node.next.elem.compareTo(e) == 0) {
		node.next = node.next.next;
		this.size -= 1;
		return true;
	    }
	    
	    node = node.next;
	}

	return false;
    }
    
    /**
     * @returns the size of the list.
     */
    public int size() {
	Node node = this.dummy;
	this.size = 0;

	while (node.next != null) {
	    size += 1;
	    node = node.next;
	}
	
	return size;
    }
    
    /**
     * Check whether the list is empty.
     * @returns {@code true} is the list is
     * empty, otherwise {@code false}.
     */
    public boolean isEmpty() {
	return this.dummy.next == null;
    }
    
    /**
     * Clear the list by breaking the 
     * linkage of the dummy.
     */
    public void clear() {
	this.dummy.next = null;
	this.size = 0;
    }
    
    /**
     * @param int index
     *
     * @returns the element of the specified index.
     *
     * @throws IndexOutOfBoundsException if the
     * given index does not exits.
     */
    public E get(int index) {
       
	int thisIndex = 0;
	Node node = this.dummy;
	     
	for (E element : this) {
	    if (thisIndex == index) {
		return element;
	    }
	    
	    thisIndex += 1;
	}
 
	if (index > thisIndex) {
	    throw new IndexOutOfBoundsException();
	}

	return null;
    }

    /**
     * @param a Array, If the length of this array is 
     * smaller than the size of the list. A new array
     * of the same runtime will be given.
     *
     * @returns an array with the elements in the list
     * ordered from smallest to largest with respect
     * to the comparable of the elements. If the size of 
     * the list is zero. 
     * @returns an empty array, E[0].
     *
     * @throws ArrayStoreException if the array is not 
     * the same type
     * @throws NullPointerException if the parameter
     * is null.
     *
     * See ifiCollection for more details.
     */
    public E[] toArray(E[] a) {
	Node<E> node;
	
	if (a == null)
	    throw new NullPointerException();

	if (a.length < this.size())
	    a = Arrays.copyOf(a, this.size());

	node = this.dummy.next;

	for (int i = 0; i < this.size; i++) {	    
	    a[i] = node.elem;
	    node = node.next;
	}

	if (a.length > this.size())
	    a[this.size] = null;

	if (this.size() == 0) 
	    a = Arrays.copyOf(a, this.size());
	
	return a;
    }
    
    /**
     * @returns an iterator.
     */
    public Iterator<E> iterator() {
	return new Iter<E>(this.dummy);
    }

}