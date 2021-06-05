package hibernate.DBUtils;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJDBC {
    public static void main(String[] args) {
        String dbHost = "jdbc:mysql://localhost:3306/hibernatedemo";
        String dbUser = "darko";
        String dbPass = "Test123$";

        try {
            System.out.println("Connecting to DB " + dbHost);
            Connection conn = DriverManager.getConnection(dbHost, dbUser, dbPass);
            System.out.println("Connection successful!");
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
