import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class SearchEmployeeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String empId = request.getParameter("empId");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "password");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM employees WHERE id=?");
            ps.setString(1, empId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                out.println("<h3>Employee Found:</h3>");
                out.println("ID: " + rs.getInt(1) + "<br/>");
                out.println("Name: " + rs.getString(2) + "<br/>");
                out.println("Department: " + rs.getString(3));
            } else {
                out.println("<h3>No employee found with ID: " + empId + "</h3>");
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
}
