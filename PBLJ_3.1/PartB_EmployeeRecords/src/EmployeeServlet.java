import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class EmployeeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String empid = request.getParameter("empid");
        String url = "jdbc:mysql://localhost:3306/companydb";
        String user = "root";
        String pass = "yourpassword";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);
            Statement stmt = con.createStatement();
            ResultSet rs;
            if (empid != null && !empid.isEmpty()) {
                rs = stmt.executeQuery("SELECT * FROM Employee WHERE EmpID=" + empid);
            } else {
                rs = stmt.executeQuery("SELECT * FROM Employee");
            }
            out.println("<h2>Employee Records</h2>");
            out.println("<table border='1'><tr><th>EmpID</th><th>Name</th><th>Salary</th></tr>");
            boolean found = false;
            while (rs.next()) {
                found = true;
                out.println("<tr><td>" + rs.getInt("EmpID") + "</td><td>" +
                            rs.getString("Name") + "</td><td>" +
                            rs.getDouble("Salary") + "</td></tr>");
            }
            out.println("</table>");
            if (!found)
                out.println("<p>No records found.</p>");
            con.close();
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
        out.close();
    }
}