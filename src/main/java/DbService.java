import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbService  {
    String Url = "jdbc:postgresql://localhost:5432/db";
    String dbUser = "postgres";
    String Passworddb = "1809";

    public Users loginUser(String usernamew, String passwordw) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            Connection connection = DriverManager.getConnection(Url, dbUser, Passworddb);
            String query = "select * from users where username=? and password=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, usernamew);
            preparedStatement.setString(2, passwordw);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                Users users = new Users(id, firstname, lastname, username, password);
                return users;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public Users createUser(String firstname, String lastname, String username, String password) throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection connection1 = DriverManager.getConnection(Url, dbUser, Passworddb);
        PreparedStatement preparedStatement = connection1.prepareStatement("select * from users where username=?;");
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        Users users = null;
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String firstname1 = resultSet.getString("firstname");
            String lastname1 = resultSet.getString("lastname");
            String username1 = resultSet.getString("username");
            String password1 = resultSet.getString("password");
            users = new Users(id, firstname1, lastname1, username1, password1);
        }
        if (users == null) {
            Connection connection = DriverManager.getConnection(Url, dbUser, Passworddb);
            PreparedStatement preparedStatement1 = connection.prepareStatement("insert into users(firstname,lastname,username,password) values(?,?,?,?)");
            preparedStatement1.setString(1, firstname);
            preparedStatement1.setString(2, lastname);
            preparedStatement1.setString(3, username);
            preparedStatement1.setString(4, password);
            preparedStatement1.executeUpdate();
            return new Users(firstname, lastname, username, password);
        } else {
            return null;
        }


    }

    public Users updateUsers(Integer id, String firstname, String lastname, String username, String password) throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection connection = DriverManager.getConnection(Url, dbUser, Passworddb);
        PreparedStatement preparedStatement = connection.prepareStatement("select * from users where id =" + id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Users users = null;
        Users users1 = null;
        String firstname1 = "";
        String lastname1 = "";
        String username1 = "";
        String password1 = "";
        while (resultSet.next()) {//kiritilgan id li user mavjudligi tekshirildi
            int id1 = resultSet.getInt("id");
            firstname1 = resultSet.getString("firstname");
            lastname1 = resultSet.getString("lastname");
            username1 = resultSet.getString("username");
            password1 = resultSet.getString("password");
            users = new Users(id1, firstname1, lastname1, username1, password1);
        }
            Users users2 = null;//username uniqueligi tekshirildi!
        if (username1!=username){
            Connection connection2 = DriverManager.getConnection(Url, dbUser, Passworddb);
            PreparedStatement preparedStatement2 = connection2.prepareStatement("select * from users where username=?;");
            preparedStatement2.setString(1, username);
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            while (resultSet2.next()) {
                int id2 = resultSet2.getInt("id");
                String firstname2 = resultSet2.getString("firstname");
                String lastname2 = resultSet2.getString("lastname");
                String username2 = resultSet2.getString("username");
                String password2 = resultSet2.getString("password");
                users2 = new Users(id2, firstname2, lastname2, username2, password2);
            }
        }
        if (users != null && users2 == null) {
            Connection connection1 = DriverManager.getConnection(Url, dbUser, Passworddb);
            PreparedStatement preparedStatement1 = connection1.prepareStatement("update users set firstname=?,lastname=?,username=?,password=? where id=" + id + ";");
            if (firstname == null) {
                preparedStatement1.setString(1, firstname1);
            } else {
                preparedStatement1.setString(1, firstname);
            }
            if (lastname == null) {
                preparedStatement1.setString(2, lastname1);
            } else {
                preparedStatement1.setString(2, lastname);
            }
            if (username == null) {
                preparedStatement1.setString(3, username1);
            } else {
                preparedStatement1.setString(3, username);
            }
            if (password == null) {
                preparedStatement1.setString(4, password1);
            } else {
                preparedStatement1.setString(4, password);
            }
            users1 = new Users(id, firstname, lastname, username, password);
            preparedStatement1.executeUpdate();
            return users1;
        }
        return null;
    }

    public List<Users> usersList() throws SQLException {
        List<Users> usersList = new ArrayList<>();
        Connection connection = DriverManager.getConnection(Url, dbUser, Passworddb);
        PreparedStatement preparedStatement = connection.prepareStatement("select * from users");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Users users = new Users();
            users.setId(resultSet.getInt("id"));
            users.setFirstname(resultSet.getString("firstname"));
            users.setLastname(resultSet.getString("lastname"));
            users.setUsername(resultSet.getString("username"));
            users.setPassword(resultSet.getString("password"));
            usersList.add(users);
        }
        return usersList;
    }

    public Users deleteUser(Integer id) throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection connection3 = DriverManager.getConnection(Url, dbUser, Passworddb);
        PreparedStatement preparedStatement3 = connection3.prepareStatement("select * from users where id="+id+";");
        ResultSet resultSet = preparedStatement3.executeQuery();
        Users users3 = null;
        while (resultSet.next()) {
            int id3 = resultSet.getInt("id");
            String firstname3 = resultSet.getString("firstname");
            String lastname3 = resultSet.getString("lastname");
            String username3 = resultSet.getString("username");
            String password3 = resultSet.getString("password");
            users3 = new Users(id3, firstname3, lastname3, username3, password3);
        }
        if (users3!=null){
            Connection connection = DriverManager.getConnection(Url, dbUser, Passworddb);
            PreparedStatement preparedStatement = connection.prepareStatement("delete from users where id="+id+";");
            preparedStatement.executeUpdate();
            return users3;


        }
            return null;
    }


}