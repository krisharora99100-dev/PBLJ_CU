import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username.equals("admin") && password.equals("1234")) {
            out.println("<h2>Welcome, " + username + "!</h2>");
        } else {
            out.println("<h3>Invalid username or password!</h3>");
        }
        out.close();
    }
}