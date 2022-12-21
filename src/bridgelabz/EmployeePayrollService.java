package bridgelabz;

import java.sql.*;

public class EmployeePayrollService {
    public static void main(String[] args) throws SQLException {
        System.out.println("Connecting database to java code: ");

        String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service";
        String userName = "root";
        String password = "password";
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded!");
            connection = DriverManager.getConnection(jdbcURL, userName, password);
            System.out.println("Connection done!!!");
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT SUM(BasicPay), AVG(basicPay), MAX(basicPay), MIN(basicPay) FROM employee_payroll WHERE gender = 'F' GROUP BY gender ");
            while (resultSet.next()) {
                System.out.println("Sum of all basic salary " + resultSet.getDouble("SUM(BasicPay)") + "\n" + "Average of all basic salary " + resultSet.getDouble("AVG(BasicPay)") + "\n" + "Min of all basic salary " + resultSet.getDouble("MIN(BasicPay)") + "\n" + "MAX of all basic salary " + resultSet.getDouble("MAX(BasicPay)"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
}