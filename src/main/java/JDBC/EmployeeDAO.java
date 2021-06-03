package JDBC;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EmployeeDAO {
    private final Connection myConn;

    public EmployeeDAO() throws Exception {

        // get db properties
        Properties props = new Properties();
        props.load(new FileInputStream("db.properties"));

        String user = props.getProperty("user");
        String password = props.getProperty("password");
        String dburl = props.getProperty("dburl");

        // connect to database
        myConn = DriverManager.getConnection(dburl, user, password);

        System.out.println("DB connection successful to: " + dburl);
    }

    private static void close(Connection myConn, Statement myStmt, ResultSet myRs)
            throws SQLException {

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

    public static void main(String[] args) throws Exception {
        EmployeeDAO dao = new EmployeeDAO();
        System.out.println(dao.searchEmployees("thom"));

        System.out.println(dao.getAllEmployees());
    }

    public List<Employee> getAllEmployees() throws Exception {
        List<Employee> list = new ArrayList<>();

        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery("select * from employees");

            while (myRs.next()) {
                Employee tempEmployee = convertRowToEmployee(myRs);
                list.add(tempEmployee);
            }

            return list;
        } finally {
            close(myStmt, myRs);
        }
    }

    public List<Employee> searchEmployees(String lastName) throws Exception {
        List<Employee> list = new ArrayList<>();

        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            lastName += "%";
            myStmt = myConn.prepareStatement("select * from employees where last_name like ?");

            myStmt.setString(1, lastName);

            myRs = myStmt.executeQuery();

            while (myRs.next()) {
                Employee tempEmployee = convertRowToEmployee(myRs);
                list.add(tempEmployee);
            }

            return list;
        } finally {
            close(myStmt, myRs);
        }
    }

    private Employee convertRowToEmployee(ResultSet myRs) throws SQLException {

        int id = myRs.getInt("id");
        String lastName = myRs.getString("last_name");
        String firstName = myRs.getString("first_name");
        String email = myRs.getString("email");
        BigDecimal salary = myRs.getBigDecimal("salary");

        Employee tempEmployee = new Employee(id, lastName, firstName, email, salary);

        return tempEmployee;
    }

    private void close(Statement myStmt, ResultSet myRs) throws SQLException {
        close(null, myStmt, myRs);
    }
}
