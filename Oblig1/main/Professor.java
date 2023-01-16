package inf1010.assignment;



public class Professor extends Person implements Teacher {
    Subject subject;
    
    /**
     * Add name, email and username.
     */
    public Professor(String name, 
		     String email, 
		     String username) {
	super(name, email, username);
    }

    /**
     * Returns an array of students
     * of the subject.
     */
    public Person[] getStudents() {
	if (this.subject == null) 
	    return new Person[0];

	return this.subject.getStudents();	
    }

    /**
     * Returns the subject this professor
     * is teaching
     */
    public Subject getSubject() {
	return subject;
    }
}
