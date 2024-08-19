package no.uib.inf101.eksamensøving.høst2020.skole;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        // Create an instance of SchoolClass
        SchoolClass myClass = new SchoolClass("Math101", 30);

        // Add students to the class
        myClass.add(new Student("Alice", 1, "GIRL"));
        myClass.add(new Student("Bob", 2, "BOY"));
        myClass.add(new Student("Carol", 3, "GIRL"));
        myClass.add(new Student("David", 4, "BOY"));

        // Test getClassName
        System.out.println("Class Name: " + myClass.getClassName()+"\n");
             
        System.out.println("Number of girls: " + myClass.countGirls()+"\n");

        // Test countBoys
        System.out.println("Number of boys: " + myClass.countBoys()+"\n");

        // Test iterator
        System.out.println("Students in the class:");
        Iterator<Student> iterator = myClass.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            System.out.println(student.name);
        }
    }
}
