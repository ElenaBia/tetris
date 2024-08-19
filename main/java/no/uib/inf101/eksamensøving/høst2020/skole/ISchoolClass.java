package no.uib.inf101.eksamensøving.høst2020.skole;

public interface ISchoolClass extends Iterable<Student>{

	/**
	 * Returns the name of the class e.g. INF102
	 * @return
	 */
	public String getClassName();
	
	/**
	 * Counts how many of the students in this class are girls
	 * @return number of girls
	 */
	public int countGirls();

	/**
	 * Counts how many of the students in this class are boys
	 * @return number of boys
	 */
	public int countBoys();
	
	/**
	 * Adds a new student to the class.
	 * If student is already in the class throw InvalidArgumentException
	 * If student is null throw NullPointerException
	 * 
	 * @param student
	 */
	public void add(Student student);
}