package inf1010.assignment;

import inf1010.lib.two.IfiCollection;
import inf1010.lib.two.ShellBase;
import java.util.Iterator;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Shell extends ShellBase {
	
    public static void main(String[] args) { 
     
	Shell s = new Shell();
	s.createTestDatabase();
	s.inputLoop();
	
    }

    IfiCollection<Person> people;
    IfiCollection<Subject> subjects;	
      
    public Shell() {
	people = new OrderedSet<Person>();
	subjects = new OrderedSet<Subject>();
    }

    /**
     * Add a group to a specified subject. If the specified 
     * subject or teaching assistant is not in the list. 
     * Print out an explaination message. 
     * 
     * @param subject courseCode of the subject.
     * @param teachingAssistant username of the teaching
     * assistant of the group.
     */
    protected void addGroup(String subject, 
			    String teachingAssistant) { 
	for (Person p : people) { 
	    if (teachingAssistant.equals(p.username)) {
		for (Subject s : subjects) {
		    if (subject.equals(s.courseCode)) {
			Subject enrollSubject = s;
			
			if (!(p instanceof TeachingAssistant)) {
			    System.out.println("Parameter input name:  "
					       + teachingAssistant + 
					       " was not initiated with type TeachingAssistant");
			    return;
			}

			TeachingAssistant assistant = (TeachingAssistant)p;
			Group g = new Group(enrollSubject, 
					    assistant);
			
			assistant.initiateGroup(g);
			
		       
			if (enrollSubject.addGroup(g)) { 
			    System.out.println("Group with courseCode " + 
					       subject +
					       " and teaching assistant " +
					       teachingAssistant +
					       " is successfully added");
			    return;
			}
			
			System.out.println("Group with name " +
					   subject +
					   " and teaching assistant " +
					   teachingAssistant + 
					   " is already in IfiCollection groups");
			return;
		    }
		}
		
		System.out.println("Subject with name " + subject
				   + " does not exists");
		return;
	    }	    
	}

	System.out.println("Teaching assistant with name" + teachingAssistant
			   + " does not exists");
	return;
    }
    
    /**
     * Create a professor with the specified parameters
     * and convert type of professor to person.
     * then add this to IfiCollection people.
     */
    protected void addProfessor(String name, 
				String username,
				String email) {
	if (name == null || username == null || email == null) {
	    System.out.println("addProfessor: One of the parameter can not be null");
	    return;
	}
     	
	Person professor = new Professor(name,
					 email,
					 username);
	if (people.add(professor)) {
	    System.out.println("Professor " + name
			       +" sucessfully added");
	    return;
	}

	if (!people.add(professor)) {
	    System.out.println("Professor with name "
			       + name +
			       " is already in the list");
	}
    }
    
    /**
     * Create a student with the specifed parameters
     * and add it to IfiCollection people.
     */
    protected void addStudent(String name, 
			      String username,
			      String email) {

	Person student = new Student(name, 
				     email,
				     username);
	if (people.add(student)) {
	      System.out.println("Student with name "
				 + name +
				 " is successfully added");
	      return;
	}
	
	System.out.println("Student with name "
			   + name +
			   " is already in the list");
	return;
    }
    
    /**
     * A add a subject with the specified parameter.
     * Find the professor in the list and create
     * a new subject. Thereafter add this subject to
     * the list subjects.
     */
    protected void addSubject(String courseCode,
			      String professor) {
	for (Person p : people) {
	    if (professor.equals(p.username)) {
		if (!(p instanceof Professor)) {
		    System.out.println("Name: " +
				       professor +
				       " was not initiated with type Professor.");
		    System.out.println("addSubject with courseCode: " + 
				       courseCode + " is unsuccessful");
		    return;
		}

		Professor pf = (Professor)p;
	
		
		Subject subject = new Subject(courseCode,
					      pf);
		if (subjects.add(subject)) {
		    System.out.println("Subject: " + courseCode
				       + " with professor: " +
				       professor
				       + " is successfully added");
		    return;
		}
		
		System.out.println("Subject: " + courseCode
				   + " is already added");
		return;
	    }
	}

	System.out.println("Name: " + professor + 
			   " is not in the list");
	System.out.println("Thus adding subject: " + courseCode +
			   " is unsuccessful");
    }
    
    /**
     * Create a new teaching assistant.
     * add this the list people.
     * If a person with the same name is already in
     * the list. Adding is unsuccessful and a
     * explaination message prints out.
     */
    protected void addTeachingAssistant(String name,
					String username,
					String email) {
	
	Person assistant = new TeachingAssistant(name,
						 email,
						 username);
       
	if (people.add(assistant)) {
	    System.out.println("Name: " + name + 
			       " is sucessfully created as a teaching assistant" + 
			       " and successfully added");
	    return;
	}

	for (Person p : people) {
	    if (username.equals(p.username)) {
		if (p instanceof Person) {
		    ((Student)assistant).groups = ((Student)p).groups; 
		    people.remove(p);
		    people.add(assistant);
		    System.out.println("Name: " + assistant.username);
		    System.out.print(" is already in the list as instance person \n");
		    System.out.println("This existed person is removed from the list");
		    System.out.println("A teachingAssistant instance is made for this person");
		    System.out.println(username + " is sucessfully added");
		    
		    return;
		}
		
		System.out.println("Name: " + name + " is already in the list"
				   + " with instance teaching assistance");
	    }
	}
    }
    
    /**
     * Enroll a student in a group, that is
     * insert a student in a group with the specified
     * parameters.
     */
    protected void enrollStudent(String student, 
				 String subject,
				 int group) {
	for (Subject s : subjects) {
	    if (subject.equals(s.courseCode)) {
		for (Person p : people) {
		    if (student.equals(p.username)) {
			if (!(p instanceof Student)) {
			    System.out.println("Name: " +
					       student +
					       " is not initialized with type Student");
			    return;
			}
			
			Student std = (Student)p;
			
			if (group > s.groupsSize()) {
			    System.out.println("Group number: " + group +
					       " does not exists");
			    System.out.println("Enroll student: " + student +
					       " is therefore unsuccessful");
			    return;
			}

			if (s.enrollStudent(std, group)) {
			    System.out.println("Name: " + student +
					       " is enrolled in " +
					       subject + "with group number: "
					       + group);
			    return;
			}

			System.out.println("Name: " + student +
					   " is already enrolled in this subject");
			return;
		    }
		}

		System.out.println("Name: " + student + "is not in the list");
		return;

	    }    
	}
	
	System.out.println("Name: " + subject + " is not in the list");
    }
    
    /**
     * Show info. of a person with the specified
     * name.
     */
    protected void showPerson(String username) {
	
	for (Person p : people) { 
	    if (username.equals(p.username)) {
		Person person = p;	    
			
		if (person instanceof Professor) {
		    Professor pf = (Professor)person;
		    Student[] students;
		    
		    students = pf.getStudents();
		    
		    System.out.println("Professor");
		    
		    System.out.print("Name: " + pf.name + "\n" + 
				     "Email: " + pf.email + "\n" +
				     "UserName: " + pf.username + "\n");
		    
		    if (pf.getSubject() != null) {
			System.out.println("Teach: " +
					   pf.getSubject().courseCode);
		    } else {
			System.out.println("The teaching subject has not been initialized");
		    }

		    if (pf.getStudents().length == 0) {
			System.out.println("students of the teaching subject" + 
					   " have not been added");
			return;
		    }
		    
		    System.out.println("Teach these students:");

		    for (Student s : students)
			System.out.println(s.name);
		    
		    return;
		    
		} else if (person instanceof TeachingAssistant) {
		    TeachingAssistant assistant = (TeachingAssistant)person;
		    Subject tSubject = assistant.getSubject();
		    Student[] students = assistant.getStudents();
		    Group[] groups = assistant.getGroups();
		    
		    System.out.print("Name: " + assistant.name + "\n" + 
				     "Email: " + assistant.email + "\n" +
				     "UserName: " + assistant.username + "\n");
		    
		    if (assistant.getSubject() != null) {
			System.out.print("Teaching Assistant of: \n" + 
					 "Group number: " + assistant.getGroup().number + "\n" +
					 "Subject: " + tSubject.courseCode + "\n");					    
		    } else {
			System.out.println("The teaching subject of " + 
					   "this person is not initialized");
			System.out.println("Thus info of the subject can " +
					   "not be written");
		    } 
		      
		    if (assistant.getStudents().length != 0) {
			System.out.println("Teach these students:");
			
			for (Student s : students)
			    System.out.println(s.name);	
		    } else {
			System.out.println("The students this person is teaching " +
					   "have not been added, " +
					   " or variable subject has not been initialized"); 
		    }
 
		    if (groups.length != 0 ) {		   		    
			System.out.println("Enrolled in these groups:");
		    
			for (Group g : groups) {
			    System.out.println("Course Code: " + g.subject.courseCode);
			    System.out.println("Lectuer: " + g.subject.lecturer);
			    System.out.println("Teaching assistant: " + 
					       g.getTeachingAssistant().name);
			}
		    } else {
			System.out.println("This teaching assistant has not enrolled in any group");
		    }

		    return;

		} else if (person instanceof Student) {
		    Student student = (Student)person;
		    Group[] groups = student.getGroups();
		    
		    System.out.print("Name: " + student.name + "\n" + 
				     "Email: " + student.email + "\n" +
				     "UserName: " + student.username + "\n");
		    
		    if (groups.length != 0) {
			System.out.println("Enrolled in these groups:");
			
			for (Group g : groups) {
			    System.out.println("Course Code: " + g.subject.courseCode);
			    System.out.println("Lectuer: " + g.subject.lecturer);
			    System.out.println("Teaching assistant: " + 
					       g.getTeachingAssistant().name);
			    
			}
		    } else {
			System.out.println("This student has not enrolled in any group");
		    }
		    
		    return;

		} else {
		    System.out.println("This person is only a person");
		
		    System.out.print("Name: " + person.name + "\n" + 
				     "Email: " + person.email + "\n" +
				     "UserName: " + person.username + "\n");		
		}
	    }
	}

	System.out.println("Username: " + username + " does not exists in the list");
	System.out.println("Note! Your parameter has to be a username of the person");
    }

    /**
     * Show info. of a subject.
     */
    protected void showSubject(String name) {
	for (Subject s : subjects) {
	    if (name.equals(s.courseCode)) {
		Subject subject = s;
		System.out.println("Course code: " + subject.courseCode);

		if (subject.getGroups().length != 0) {
		    System.out.println("Contains these groups:");
		    
		    for (Group g : subject.getGroups()) {
			System.out.println("Group number: " + g.number);
			
			if (g.getStudents().length != 0) {
			    System.out.println("Contains these students: ");
			    
			    for (Student std : g.getStudents()) {
				System.out.println("Name: " + std.name);
			    }
			} else {
			    System.out.println("Students have not added to this group");
			}
		    }
		    
		    return;
		}
		
		System.out.println("IfiCollection groups in this subject is empty");
		System.out.println("The groups in this subject are not yet created");
		return;
	    }
	}
	
	System.out.println("The subject has not been added to the list");
    }

    /**
     * Print out the names of the persons in the list people
     * and the subject courseCodes. 
     */
    protected void list() {
	if (people.isEmpty()) {
	    System.out.println("no person has been added");
	} else {
	    System.out.println("List of people: ");
	    
	    for (Person p : people)
		System.out.println(p.name);
	}
	
	if (subjects.isEmpty()) {
	    System.out.println("no subject has been added");
	    
	} else {
	    System.out.println();
	    System.out.println("List of subject: ");
	    
	    for (Subject s : subjects) 
		System.out.println(s.courseCode);
	}
    }
}