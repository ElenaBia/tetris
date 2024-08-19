package no.uib.inf101.eksamensøving.høst2020.skole;


/**
 * This class represents a student.
 * Each student has a name and a unique studentID.
 * A student is either a BOY or a GIRL
 * 
 * @author Martin Vatshelle
 *
 */
public class Student {

	private boolean isGirl;
	public String name;
	private int studentID;
	
	/**
	 * Constructs a new Student object
	 * @param name - name of student
	 * @param studentID - unique ID for the student
	 * @param gender - either BOY or GIRL
	 */
	public Student(String name, int studentID, String gender){
		this.name = name;
		this.studentID= studentID; 

		if(gender.equals("GIRL")) {
			isGirl = true;
		}else {
			if(gender.equals("BOY")) {
				isGirl = false;
			}else {
				throw new IllegalArgumentException("gender must be either BOY or GIRL");
			}
		}
	}
	
	/**
	 * Checks if this student is a GIRL
	 * @return true if girl, otherwise false
	 */
	boolean isGirl(){
		return isGirl;
	}

	/**
	 * Checks if this student is a BOY
	 * @return true if boy, otherwise false
	 */
	boolean isBoy(){
		return !isGirl;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Student) {
			Student other = (Student)obj;
			return this.studentID == other.studentID;
		}
		return false;
	}
}
