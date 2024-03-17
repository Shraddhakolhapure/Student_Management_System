package student;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {

    public static Connection createConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/student_manage"; 
            String username = "root";
            String password = "123456";

            con = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Established!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con; // Return the connection object
    }
}
