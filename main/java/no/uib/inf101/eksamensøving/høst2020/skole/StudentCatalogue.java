package no.uib.inf101.eksamensøving.høst2020.skole;

import java.util.ArrayList;
import java.util.List;


/**
 * This class keeps track of students in the two courses INF101 and INF102.
 * Any student can be in Either 1 or the other class
 * 
 * @author Martin Vatshelle
 *
 */
public class StudentCatalogue {

	private List<Student> inf101Students;
	private List<Student> inf102Students;
	
	/**
	 * This constructor creates a new StudentCatalogue containing students
	 * in the two courses INF101 and INF102.
	 * Since the students in the two courses are mostly the same all 
	 * students are in both courses.
	 * @param students
	 */
	public StudentCatalogue(List<Student> students) {
		inf101Students = new ArrayList<Student>(students);
		inf102Students = new ArrayList<Student>(students);
	}

	/**
	 * This methods checks if a student is in INF101
	 */
	public boolean isINF101Student(Student s) {
		return inf101Students.contains(s);
	}

	/**
	 * This methods checks if a student is in INF102
	 */
	public boolean isINF102Student(Student s) {
		return inf102Students.contains(s);
	}
	
	/**
	 * This method adds a student to INF101
	 */
	public void addInf101Student(Student s){
		inf101Students.add(s);
	}

	/**
	 * This method adds a student to INF102
	 */
	public void addInf102Student(Student s){
		inf102Students.add(s);
	}

	/**
	 * This method removes a student from INF101 
	 * if the student is in the course
	 */
	public void removeInf101Student(Student s){
		inf101Students.remove(s);
	}

	/**
	 * This method removes a student from INF101 
	 * if the student is in the course
	 */
	public void removeInf102Student(Student s){
		inf102Students.remove(s);
	}
}
