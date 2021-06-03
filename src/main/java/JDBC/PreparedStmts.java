package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PreparedStmts {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Get a connection to database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo", "darko", "Test123$");

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM employees WHERE salary > ? AND department=?");
            stmt.setInt(1, 30000);
            stmt.setString(2, "HR");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                System.out.println(rs.getString("first_name") + " " + rs.getString("last_name"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
