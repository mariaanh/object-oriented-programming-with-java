package inf1010.assignment;

interface Teacher<E> {
    /** 
     * @returns an array of students
     */
    Person[] getStudents();

    /**
     * @returns a subject
     */
    Subject getSubject();
}