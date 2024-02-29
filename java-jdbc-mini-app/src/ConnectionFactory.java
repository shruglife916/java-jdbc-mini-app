import java.sql.*;
import java.util.ResourceBundle;


public class ConnectionFactory {
    private static Connection connection = null;
    private ConnectionFactory() { }

    public static Connection getConnection() {
        if(connection == null) {
            ResourceBundle bundle = ResourceBundle.getBundle("config");
            String url = bundle.getString("url");
            String username = bundle.getString("username");
            String password = bundle.getString("password");
            /*
            String url = jdbc:mysql://localhost:3306/demo;
            String username = "root";
            String password = "ThePyramids3";
             */

            try{
                connection=DriverManager.getConnection(url,username,password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }

}
