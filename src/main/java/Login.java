import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("index.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        DbService dbService = new DbService();
        Users users = dbService.loginUser(username, password);
        if (users == null) {
            writer.write("<h1 style:'color:red'> User not Found!!!</h1>" +
                    " <h4>Don't you have account?</h4>" +
                    "<a href=\"/create\">Register</a><br>");
        } else {
            writer.write("<h1 style:'color:green'>Welcome dear User:" + users.getFirstname() + " " + users.getLastname() + " </h1><br>");
            writer.write("<h1>Informs:</h1><br>");
            writer.write("<h1>[id=" + users.getId() + ", firstname=" + users.getFirstname() + ", lastname=" + users.getLastname() + ",username=" + users.getUsername() + ",password " + users.getPassword() + "]</h1>");
            writer.write(" <h4>Do you want  update?</h4>\n" +
                    "<a href=\"/update\">Update</a><br>");
            if (username.equals("1111") && password.equals("1111")) {
                try {
                    List<Users> usersList = dbService.usersList();
                    writer.write("<h1>Users List:</h1><br>");
                    String royxat="<table border=\"glass\">\n" +
                            "    <tr>\n" +
                            "        <th>Id</th>\n" +
                            "        <th>Firstname</th>\n" +
                            "        <th>Lastname</th>\n" +
                            "        <th>Username</th>\n" +
                            "        <th>Password</th>\n" +
                            "        <th>Delete</th>" +
                            "</tr>\n";
                    for (int j = 0; j < usersList.size(); j++) {
                        royxat+="    <tr>\n" +
                                "            <th>" + usersList.get(j).getId() + "</th>\n" +
                                "            <th>" + usersList.get(j).getFirstname() + "</th>\n" +
                                "            <th>" + usersList.get(j).getLastname() + "</th>\n" +
                                "            <th>" + usersList.get(j).getUsername() + "</th>\n" +
                                "            <th>" + usersList.get(j).getPassword() + "</th>\n" +
                                "<th><a href=/delete>Delete</a></th>" +
                                "    </tr>\n";
                    }
                    royxat+=  "</table>\n";
                        writer.write(royxat);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }

}
