import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartcController {
    private Connection conn;
    public PartcController() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentDB", "root", "chir1122");
    }
    public void addStudent(Partc s) throws SQLException {
        String sql = "INSERT INTO Student (Name, Department, Marks) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getName());
            ps.setString(2, s.getDepartment());
            ps.setInt(3, s.getMarks());
            ps.executeUpdate();
        }
    }
    public List<Partc> getAllStudents() throws SQLException {
        List<Partc> students = new ArrayList<>();
        String sql = "SELECT * FROM Student";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                students.add(new Partc(rs.getInt("StudentID"),
                                         rs.getString("Name"),
                                         rs.getString("Department"),
                                         rs.getInt("Marks")));
            }
        }
        return students;
    }
    public void updateStudent(Partc s) throws SQLException {
        String sql = "UPDATE Student SET Name=?, Department=?, Marks=? WHERE StudentID=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getName());
            ps.setString(2, s.getDepartment());
            ps.setInt(3, s.getMarks());
            ps.setInt(4, s.getStudentID());
            ps.executeUpdate();
        }
    }
    public void deleteStudent(int studentID) throws SQLException {
        String sql = "DELETE FROM Student WHERE StudentID=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentID);
            ps.executeUpdate();
        }
    }
}