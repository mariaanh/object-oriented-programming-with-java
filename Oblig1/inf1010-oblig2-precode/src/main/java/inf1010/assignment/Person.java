package inf1010.assignment;


public class Person implements Comparable<Person> {
    String email;
    String name;
    String username;

    public Person(String name, String email, String username) {
	this.email = email;
	this.name = name;
	this.username = username;
    }

    /**
     * Compare class this with an other person,
     * with respect to person's name.
     */    
    public int compareTo(Person other) {
	return this.name.compareTo(other.name);
    }

    /**
     * @returns this person's email.
     */
    public String getEmail() {
	return this.email;
    }

    /**
     * @returns this person's name.
     */
    public String getName() {
	return this.name;
    }

    /**
     * @returns this person's username.
     */
    public String getUsername() {
	return this.username;
    }
}
