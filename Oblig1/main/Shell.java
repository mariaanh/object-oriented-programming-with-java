package inf1010.assignment;

import inf1010.lib.two.IfiCollection;
import inf1010.lib.two.ShellBase;
import java.util.Iterator;

public class Shell {
	
    public static void main(String[] args) {
	OrderedSet<Person> set = new OrderedSet<Person>();
	Person person1 = new Person("a","email","username");
	Person person2 = new Person("b","email","username");
	Person person3 = new Person("c","email","username");
	Person person4 = new Person("d","email","username");
       
	set.add(person1);
	set.add(person2);
	set.add(person3);
	set.add(person4);
	System.out.println(person1.getName());
	System.out.println(person1.getEmail());
	System.out.println(person1.getUsername());

	Student student = new Student("name","email","username");
      
	//Shell s = new Shell();
	//s.createTestDatabase();
	//s.inputLoop();
    }
    
    public Shell() {
	IfiCollection<Person> people = new OrderedSet<Person>();
	IfiCollection<Subject> subjects = new OrderedSet<Subject>();
	/*
	int groupNumber = 0;
	public void addGroup(String subject, 
			     String teachingAssistant) {
	    // this is not correct
	    Group g = new Group(groupNumber,subject, teachingAssistant);
	    groupNumber += 1;
	    // create a group and give the group a number.	    
	}

	public void addProfessor(String name, String username,
				 String email) {
	    Person professor = new Professor(name,username,
						email);
	    // create a professor object
	    
	    people.add(professor);
	    // add professor to people
	}

	public void addStudent(String name, String email,
			       String username) {
	    Person student = new Student(name, 
					 email,
					 username);
	    people.add(student);
	    // add student to people.	    
	}

	public void addSubject(String courseCode,
			       String professor) {
	    return new UnSupportedOperationException();
	}

	public void addTeachingAssistant(String name,
					 String username,
					 String email) {
	    return new UnSupportedOperationException();
	}
	
	public void enrollStudent(String student, 
				  String subject,
				  int group) {
	    return new UnSupportedOperationException();
	}

	public void showPerson(String name) {
	    return new UnSupportedOperationException();
	}

	public void showSubject(String name) {
	    return new UnSupportedOperationException();
	}
	*/
    }
    
}