package MainApplication;

import java.sql.Connection;

public class test {
    public static void main(String[] args) {
        DatabaseConnection db = new DatabaseConnection();
        Connection conn = db.getConnection();

        if (conn != null) {
            System.out.println("Connection test successful.");
        } else {
            System.out.println("Connection test failed.");
        }
    }
}