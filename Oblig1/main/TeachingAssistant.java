package inf1010.assignment;


class TeachingAssistant extends Student implements Teacher {
    Group group;

    public TeachingAssistant(String name, String email,
			     String username) {
	super(name,email,username);
    }

    public Group getGroup() {
	return this.group;
    }
    
    public Person[] getStudents() {
	return this.group.getStudents();
    }

    public Subject getSubject() {
	return group.subject;
    }
}
