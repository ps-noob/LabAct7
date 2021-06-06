package Pankaj.LabAct7;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/first")
public class First extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        String nameReg = req.getParameter("nameReg");
        PrintWriter out = resp.getWriter();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "root");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM STUDENT.STUDENT_DETAILS WHERE NAME='" + nameReg + "' OR STUDENT_REG_NO='" + nameReg + "';");
            out.write(
                    "<table border = \"1px\">\n" +
                            "    <tr>\n" +
                            "        <td>Reg no</td>\n" +
                            "        <td>Name</td>\n" +
                            "        <td>Age</td>\n" +
                            "        <td>Address</td>\n" +
                            "        <td>Email</td>\n" +
                            "        <td>Phone Details</td>\n" +
                            "    </tr>\n");
            while (rs.next()) {
                out.write(
                        "    <tr>\n" +
                                "        <td>"+rs.getString(1)+"</td>\n" +
                                "        <td>"+rs.getString(2)+"</td>\n" +
                                "        <td>"+rs.getString(3)+"</td>\n" +
                                "        <td>"+rs.getString(4)+"</td>\n" +
                                "        <td>"+rs.getString(5)+"</td>\n" +
                                "        <td>"+rs.getString(6)+"</td>\n" +
                                "    </tr>\n");
            }
            out.write("</table>");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}