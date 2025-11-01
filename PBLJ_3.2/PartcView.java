import java.util.List;
import java.util.Scanner;

public class PartcView {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PartcController controller = new PartcController();
        while (true) {
            System.out.println("\n1. Add Student 2. View Students 3. Update Student 4. Delete Student 5. Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Department: ");
                    String dept = sc.nextLine();
                    System.out.print("Marks: ");
                    int marks = sc.nextInt();
                    controller.addStudent(new Partc(0, name, dept, marks));
                    System.out.println("Added!");
                    break;

                case 2:
                    List<Partc> students = controller.getAllStudents();
                    System.out.println("ID\tName\tDepartment\tMarks");
                    for (Partc s : students) {
                        System.out.println(s.getStudentID() + "\t" + s.getName() + "\t" + s.getDepartment() + "\t" + s.getMarks());
                    }
                    break;

                case 3:
                    System.out.print("StudentID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("New Name: ");
                    String newName = sc.nextLine();
                    System.out.print("New Dept: ");
                    String newDept = sc.nextLine();
                    System.out.print("New Marks: ");
                    int newMarks = sc.nextInt();
                    controller.updateStudent(new Partc(id, newName, newDept, newMarks));
                    System.out.println("Updated!");
                    break;

                case 4:
                    System.out.print("StudentID: ");
                    int delId = sc.nextInt();
                    controller.deleteStudent(delId);
                    System.out.println("Deleted!");
                    break;

                case 5:
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid!");
            }
        }
    }
}