package inf1010.assignment;

import java.util.Arrays;
import inf1010.lib.two.IfiCollection;

public class Group implements Comparable<Group> {
    int number;
    Subject subject;
    TeachingAssistant teachingAssistant;
   

    /** 
     * Make an empty linked list called students.
     * This list contains elements of type Student.
     */ 
    IfiCollection<Person> students = new OrderedSet<Person>();

    public Group(int number, Subject subject, 
		 TeachingAssistant teachingAssistant) {
	this.number = number;
	this.subject = subject;
	this.teachingAssistant = teachingAssistant;
    }

    /** 
     * Add a student to the list students
     */
    public boolean addStudent(Student s) {
	return students.add(s);
    }
    
    /**
     * Implements Comprable. Compare the groups
     * according to it's number.
     */    
    public int compareTo(Group other) {
	return this.number - other.number;
    }
    
    /**
     * Return the group's number.
     */
    public int getNumber() {
	return this.number;
    }

    /**
     * Return the totall number of students
     * of the group.
     */
    public int getStudentBodySize() {
	return students.size();
    }
    
    /**
     * Return an array of students
     * in the group
     */
    public Person[] getStudents() {
	Person[] a = new Person[1];
	
	return students.toArray(a);
    }

    /**
     * Return the subject of the group.
     */
    public Subject getSubject() {
	return this.subject;
    }

    /**
     * Return the teaching assistant of
     * the group.
     */
    public TeachingAssistant getTeachingAssistant() {
	return this.teachingAssistant;
    }
}

