package inf1010.assignment;

import inf1010.lib.two.IfiCollection;
import java.util.NoSuchElementException;

public class Subject implements Comparable<Subject> {
    String courseCode;
    IfiCollection<Group> groups;
    Professor lecturer;
    
    /**
     * Implements comparable according to the
     * courseCode.
     */
    
    public int compareTo(Subject other) {
	return this.courseCode.compareTo(other.courseCode);
    }
    
    /**
     * Inserting a student 
     */
    public boolean enrollStudent(Student s, int group) {
	if (s == null) {
	    throw new NullPointerException();
	}

	for (Group g : groups) {
	    if (g.number == group) {
		g.addStudent(s);
		return true;
	    }
	}
	
	if (group > groups.size())
	    throw new NoSuchElementException();
	
	return false;
    }

    /**
     *  Returns the courseCode.
     */
    public String getCourseCode() {
	return this.courseCode;
    }

    /**
     * Returns the lecturer.
     */
    public Teacher getLecturer() {
	Teacher teacher = this.lecturer;
	return teacher;
    }
    
    /**
     * Returns the totall number of
     * students. Assume that a student
     * can not be in more than one
     * group.
     */
    public int getStudentBodySize() {
	int totallStudents = 0;
	
	for (Group g : groups) {
	    totallStudents += g.getStudentBodySize();
	}
	
	return totallStudents;
    }

    /**
     * Return an array of Students
     * in this subject.
     */   
    public Person[] getStudents() {
	int size = this.getStudentBodySize();
	Person[] s = new Person[size];
	
	for (Group g : groups) {
	    for (Person p : g.getStudents()) {
		for (int i = 0; i < size; i++) {
		    s[i] = p;
		}
	    }
	}
	return s;
    }

    /**
     * Return number an array of teachers
     */
    public Teacher[] getTeachers() {
	Teacher teacher;
	int groupsSize = groups.size();
	Teacher[] teachers = new Teacher[groupsSize];
	
	teacher = this.lecturer;
	teachers[0] = teacher;

	for (Group g : groups) {
	    for (int i = 1; i < groupsSize; i++) {
		teacher = g.teachingAssistant;
		teachers[i] = teacher;
	    }
	}

	return teachers;
    }
}
