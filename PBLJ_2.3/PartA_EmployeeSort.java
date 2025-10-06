import java.util.*;

class Employee {
    String name;
    int age;
    double salary;

    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return name + " | Age: " + age + " | Salary: " + salary;
    }
}

public class PartA_EmployeeSort {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Alice", 28, 50000));
        employees.add(new Employee("Bob", 35, 70000));
        employees.add(new Employee("Charlie", 30, 60000));

        // Sort by name alphabetically
        employees.sort((e1, e2) -> e1.name.compareTo(e2.name));
        System.out.println("Sort by name (alphabetically): " + employees);

        // Sort by age ascending
        employees.sort((e1, e2) -> Integer.compare(e1.age, e2.age));
        System.out.println("Sort by age (ascending): " + employees);

        // Sort by salary descending
        employees.sort((e1, e2) -> Double.compare(e2.salary, e1.salary));
        System.out.println("Sorted by salary descending: " + employees);
    }
}
