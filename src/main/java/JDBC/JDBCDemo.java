package JDBC;

import java.sql.*;


public class JDBCDemo {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Get a connection to database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo", "darko", "Test123$");
            // Create a statement
            Statement stmt = conn.createStatement();
            // Execute SQL query
            ResultSet rs = stmt.executeQuery("SELECT * FROM employees");
            // Process the result set
            while(rs.next()){
                System.out.println(rs.getString("last_name") + " " + rs.getString("first_name"));
            }

            String insertSql = "INSERT INTO employees (first_name, last_name, email) VALUES ('Toma', 'Tomić', 'test7@test.com');";

            stmt.executeUpdate(insertSql);

            System.out.println("Insert complete.");

            String updateSQL = "UPDATE employees SET email='test3@test.com' WHERE id=4";

            stmt.executeUpdate(updateSQL);

            System.out.println("Update complete.");

            String deleteSQL = "DELETE FROM employees WHERE last_name='Tomić'";

            int rowsAffected = stmt.executeUpdate(deleteSQL);

            System.out.println("Rows affected: " + rowsAffected);
            System.out.println("Delete complete.");
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
