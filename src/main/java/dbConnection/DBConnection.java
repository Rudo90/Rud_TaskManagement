package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {

    private static volatile DBConnection instance;
    private static final String URL = "jdbc:mysql://localhost:3306/task_management";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static DBConnection getProvider() {

        if (instance == null) {
            synchronized (DBConnection.class) {
                if (instance == null) {
                    instance = new DBConnection();
                }
            }
        }
        return instance;
    }

    public Connection getConnection(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
