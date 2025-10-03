import java.io.*;

class Student implements Serializable {
    private int studentID;
    private String name;
    private double grade;

    public Student(int studentID, String name, double grade) {
        this.studentID = studentID;
        this.name = name;
        this.grade = grade;
    }

    public void display() {
        System.out.println("ID: " + studentID + ", Name: " + name + ", Grade: " + grade);
    }
}

public class PartB {
    public static void main(String[] args) {
        String filename = "student.ser";

        // Serialize
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            Student s = new Student(101, "Krish", 89.5);
            oos.writeObject(s);
            System.out.println("Student object serialized.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialize
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            Student s2 = (Student) ois.readObject();
            System.out.println("Deserialized Student:");
            s2.display();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
