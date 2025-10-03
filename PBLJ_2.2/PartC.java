import java.io.*;
import java.util.*;

class Employee {
    int id;
    String name, designation;
    double salary;

    public Employee(int id, String name, String designation, double salary) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return id + " | " + name + " | " + designation + " | " + salary;
    }
}

public class PartC {
    static final String FILE_NAME = "employees.txt";

    public static void addEmployee(Scanner sc) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            System.out.print("Enter ID: ");
            int id = sc.nextInt();
            sc.nextLine(); // consume newline
            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Designation: ");
            String desig = sc.nextLine();
            System.out.print("Enter Salary: ");
            double salary = sc.nextDouble();

            Employee e = new Employee(id, name, desig, salary);
            bw.write(e.toString());
            bw.newLine();
            System.out.println("Employee added.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void displayEmployees() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            System.out.println("Employee Records:");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("No records found.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // ✅ only one scanner for entire program
        int choice;
        do {
            System.out.println("\n--- Employee Management Menu ---");
            System.out.println("1. Add Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            if (!sc.hasNextInt()) break; // safety check
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> addEmployee(sc);
                case 2 -> displayEmployees();
                case 3 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 3);

        sc.close();
    }
}
