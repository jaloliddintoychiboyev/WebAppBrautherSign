import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class Delete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("delete.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        DbService dbService = new DbService();
        PrintWriter writer=resp.getWriter();
        try {
            Users users = dbService.deleteUser((id));
            if (users==null){
                writer.write("<h1>"+id+":Id equals not Found!!!</h1>");
            }else {
                writer.write("<h1>Id:"+id+" User Succesfull Delete!!!</h1>");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
