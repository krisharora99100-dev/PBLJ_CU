import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AttendanceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String studentId = request.getParameter("studentid");
        String date = request.getParameter("date");
        String status = request.getParameter("status");
        String url = "jdbc:mysql://localhost:3306/schooldb";
        String user = "root";
        String pass = "yourpassword";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);
            PreparedStatement ps = con.prepareStatement("INSERT INTO Attendance VALUES (?, ?, ?)");
            ps.setString(1, studentId);
            ps.setString(2, date);
            ps.setString(3, status);
            int i = ps.executeUpdate();
            if (i > 0) {
                out.println("<h3>Attendance recorded successfully for Student ID: " + studentId + "</h3>");
            } else {
                out.println("<h3>Error recording attendance.</h3>");
            }
            con.close();
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
        out.close();
    }
}