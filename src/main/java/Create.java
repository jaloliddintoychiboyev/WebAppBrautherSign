import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class Create extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter respWriter = resp.getWriter();
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        DbService dbService = new DbService();
        Users user = null;
        try {
            user = dbService.createUser(firstname, lastname, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (user == null) {
            respWriter.write("<h1>This Username  Allready Exist!!! </h1>");
        } else {
            respWriter.write("<h1>Successfull Create User :" + firstname + " " + lastname + "</h1>");

        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("create.html");
    }
}
