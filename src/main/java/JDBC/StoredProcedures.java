package JDBC;

import java.sql.*;

public class StoredProcedures {
    public static void main(String[] args) throws SQLException {

        Connection conn = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Get a connection to database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo", "darko", "Test123$");

            // IN parameteres
            increaseSalariesForDepartment(conn, "HR", 3000);

            // INOUT parameters
            greetDepartment(conn, "Legal");

            // OUT
            int count = getCountForDepartment(conn, "HR");
            System.out.println("Count of employees for HR: " + count);

            // with ResultSet return from DB
            rs = getEmployeesForDepartment(conn, "HR");
            display(rs);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close(conn, null, rs);
        }
    }

    private static void increaseSalariesForDepartment(Connection conn, String department, int amount) throws SQLException {
        CallableStatement stmt = conn.prepareCall("call increase_salaries_for_department(?,?)");
        stmt.setString(1, department);
        stmt.setInt(2, amount);

        stmt.execute();

        showSalaries(conn, "HR");

        close(stmt, null);
    }

    private static String greetDepartment(Connection conn, String department) throws SQLException {
        CallableStatement stmt = conn.prepareCall("call greet_the_department(?)");
        stmt.registerOutParameter(1, Types.VARCHAR);
        stmt.setString(1, department);

        // Call the stored procedure
        stmt.execute();

        // Get the value of the INOUT parameter
        String result = stmt.getString(1);

        System.out.println(result);

        close(stmt, null);

        return result;
    }

    private static int getCountForDepartment(Connection conn, String department) throws SQLException {
        CallableStatement stmt = conn.prepareCall("call get_count_for_department(?, ?)");
        stmt.setString(1, department);
        stmt.registerOutParameter(2, Types.INTEGER);
        // Call the stored procedure
        stmt.execute();

        // Get the value of the OUT parameter
        int count = stmt.getInt(2);

        close(stmt, null);

        return count;
    }

    private static ResultSet getEmployeesForDepartment(Connection conn, String department) throws SQLException {
        CallableStatement stmt = conn.prepareCall("call get_employees_for_department(?)");
        stmt.setString(1, department);
        // Call the stored procedure
        stmt.execute();
        // Get the result set
        return stmt.getResultSet();
    }

    private static void display(ResultSet myRs) throws SQLException {
        while (myRs.next()) {
            String lastName = myRs.getString("last_name");
            String firstName = myRs.getString("first_name");
            double salary = myRs.getDouble("salary");
            String department = myRs.getString("department");

            System.out.printf("%s, %s, %.2f, %s\n", lastName, firstName, salary, department);
        }
    }

    private static void showSalaries(Connection myConn, String theDepartment) throws SQLException {
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            // Prepare statement
            myStmt = myConn
                    .prepareStatement("select * from employees where department=?");

            myStmt.setString(1, theDepartment);

            // Execute SQL query
            myRs = myStmt.executeQuery();

            // Process result set
            while (myRs.next()) {
                String lastName = myRs.getString("last_name");
                String firstName = myRs.getString("first_name");
                double salary = myRs.getDouble("salary");
                String department = myRs.getString("department");

                System.out.printf("%s, %s, %s, %.2f\n", lastName, firstName, department, salary);
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            close(myStmt, myRs);
        }
    }

    private static void close(Connection myConn, Statement myStmt,
                              ResultSet myRs) throws SQLException {
        if (myRs != null) {
            myRs.close();
        }

        if (myStmt != null) {
            myStmt.close();
        }

        if (myConn != null) {
            myConn.close();
        }
    }

    private static void close(Statement myStmt, ResultSet myRs)
            throws SQLException {

        close(null, myStmt, myRs);
    }
}
