package no.uib.inf101.eksamensøving.høst2020.skole;


import java.util.ArrayList;
import java.util.Iterator;



public class SchoolClass implements ISchoolClass {

    //instans variabler
    String className;
    int studentCapacity;
    ArrayList<Student> classList = new ArrayList<Student>();
    

    //konstruktør
    public SchoolClass(String name, int capacity){
        this.className=name;
        this.studentCapacity=capacity;
    }

    @Override
    public Iterator<Student> iterator() {
        return classList.iterator();
    }

    @Override
    public String getClassName() {
        return this.className;
    }

    @Override
    public int countGirls() {
        int totalGirls = 0;
        Iterator<Student> iterator = classList.iterator(); // Create a single iterator
        while (iterator.hasNext()) {
            Student currStudent = iterator.next();
            if (currStudent.isGirl()) {
                totalGirls++;
            }
        }
        return totalGirls;

  
    }

    @Override
    public int countBoys() {
        int totalBoys = 0;
        Iterator<Student> iterator = classList.iterator(); // Create a single iterator
        while (iterator.hasNext()) {
            Student currStudent = iterator.next();
            System.out.println(currStudent.name); // Debug statement to see the student being processed
            if (currStudent.isGirl()) {
                totalBoys++;
            }
        }
        return totalBoys; 
    }

    @Override
    public void add(Student student) {
        classList.add(student);
    }
    
}
