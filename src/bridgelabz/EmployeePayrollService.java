package bridgelabz;

import java.sql.*;

public class EmployeePayrollService {
    public static void main(String[] args) throws SQLException {
        System.out.println("Connecting database to java code: ");

        String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service";
        String userName = "root";
        String password = "password";
        Connection connection = null;
        double basicPay = 400000.00;
        int id = 1;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded!");
            connection = DriverManager.getConnection(jdbcURL, userName, password);
            System.out.println("Connection done!!!");
            String query = "UPDATE employee_payroll SET basicPay=? WHERE id=?";
            PreparedStatement prestatement = connection.prepareStatement(query);
            prestatement.setDouble(1, basicPay);
            prestatement.setInt(2, id);
            prestatement.executeUpdate();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employee_payroll");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + " | " + resultSet.getString("name") + " | " + resultSet.getString("gender") + " | " + resultSet.getString("phone_number") + " | " + resultSet.getString("address") + " | " + resultSet.getString("department") + " | " + resultSet.getDouble("basicPay") + " | " + resultSet.getDouble("deductions") + " | " + resultSet.getDouble("taxablePay") + " | " + resultSet.getDouble("netPay") + " | " + resultSet.getDouble("incomeTax") + " | " + resultSet.getDate("start"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
}