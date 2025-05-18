package MainApplication;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/healthcare";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "ubm 2llll";

    @SuppressWarnings("exports")
	public Connection getConnection() {
        Connection connection = null;

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Database connected successfully!");

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed.");
            e.printStackTrace();
        }

        return connection;
    }

	public static PreparedStatement prepareStatement(String sql) {
		// TODO Auto-generated method stub
		return null;
	}
}
