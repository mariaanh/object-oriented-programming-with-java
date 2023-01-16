package inf1010.assignment;


class TeachingAssistant extends Student implements Teacher {
    Group group;

    public TeachingAssistant(String name, 
			     String email,
			     String username) {
	super(name,email,username);
    }

    /** initialise variable group */    
    public void initiateGroup(Group group) {
	this.group = group;
    }

    /** @returns this.group */
    public Group getGroup() {
	return this.group;
    }
    
    /** @returns an array of students
     * in the group. If group is not initialized
     * @returns Student[0].
     */
    public Student[] getStudents() {
	if (group != null) 
	    return this.group.getStudents();
	
	return new Student[0];
    }

    /**
     * @returns the subject of the group. If group
     * is not initialized 
     * @returns null.
     */
    public Subject getSubject() {
	if (group == null) 
	    return null;

	return group.getSubject();
    }
}
