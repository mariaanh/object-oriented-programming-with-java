package inf1010.assignment;

import java.util.Arrays;
import inf1010.lib.two.IfiCollection;
import java.util.Iterator;

public class Student extends Person {
    
    /**
     * Make a new empty OrderedSet called groups.
     */
    IfiCollection<Group> groups = new OrderedSet<Group>();

    public Student(String name, String email, String username) {
	super(name, email, username);
    }

    public void addGroup(Group group) {
	groups.add(group);
    }
    
    /**
     * Return the elements in groups.
     */
    public Group[] getGroups() {
	if (groups == null)
	    return new Group[0];

	Group[] a = new Group[1];
	
	return groups.toArray(a);
    }
    
    /**
     * Return an array of subjects of the 
     * corresponding groups this person is member
     * of.
     */
    public Subject[] getSubjects() {
	int sizeOfSubjects = groups.size();
	Subject[] subjects = new Subject[sizeOfSubjects];
	
	if (groups == null)
	    return new Subject[0];

	for (Group g : groups) {
	    for (int i = 0; i < sizeOfSubjects; i++) {
		subjects[i] = g.subject;
	    }
	}
	
	return subjects;
    }

    /**
     * Return an array of the teachers of the
     * corresponding groups this person is 
     * member of.
     */
    public Teacher[] getTeachers() {
	int sizeOfTeachers = groups.size();
	Teacher[] teachers = new Teacher[2*sizeOfTeachers];
	
	if (groups == null) 
	    return new Teacher[0];

	for (Group g : groups) {
	    for (int i = 0; i < 2*sizeOfTeachers; i += 2) {
		Teacher teacher = g.teachingAssistant;
		teachers[i] = teacher;
		teachers[i+1] = g.subject.lecturer;
	    }
	}
	
	return teachers;
    }
}