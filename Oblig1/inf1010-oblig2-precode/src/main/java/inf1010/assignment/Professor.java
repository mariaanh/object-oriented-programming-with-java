package inf1010.assignment;



public class Professor extends Person implements Teacher {
    Subject subject;
    
    public Professor(String name, 
		     String email,
		     String username) {
	super(name,email,username);
    }

    /**
     * @returns an array of students
     * in the subject. If Subject is null
     * @returns Student[0]. 
     */
    public Student[] getStudents() {
	if (this.subject == null) 
	    return new Student[0];

	return this.subject.getStudents();	
    }

    /**
     * @returns the subject this professor
     * is teaching
     */
    public Subject getSubject() {
	return subject;
    }
}
