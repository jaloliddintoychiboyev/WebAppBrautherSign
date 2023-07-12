import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class Update extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        DbService dbService = new DbService();
        Integer id = Integer.valueOf(req.getParameter("id"));
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            Users users = dbService.updateUsers(id, firstname, lastname, username, password);
            if (users==null){
                writer.write("<h1>"+id+": equal Id not Found!!! or Username:"+username+" Already Exists!!! </h1>");
            }else {
                writer.write("<h1>Succesfull Update User id:"+id+" </h1>");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("update.html");

    }
}
