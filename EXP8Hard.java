import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class AttendanceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String rollNo = request.getParameter("rollNo");
        String subject = request.getParameter("subject");
        String status = request.getParameter("status");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_portal", "root", "password");

            PreparedStatement ps = con.prepareStatement("INSERT INTO attendance (roll_no, subject, status) VALUES (?, ?, ?)");
            ps.setString(1, rollNo);
            ps.setString(2, subject);
            ps.setString(3, status);
            int result = ps.executeUpdate();

            if (result > 0) {
                out.println("<h3>Attendance recorded successfully!</h3>");
            } else {
                out.println("<h3>Error saving attendance.</h3>");
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
}
