import java.sql.*;
import java.util.*;

public class EmployeeDaoImpl implements EmployeeDao {
    Connection connection;

    public EmployeeDaoImpl() {
        connection = ConnectionFactory.getConnection();
    }
    @Override
    public void addEmployee(Employee employee) throws SQLException {
        String sql = "insert into employee (name, email) values (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, employee.getName());
        preparedStatement.setString(2, employee.getEmail());
        int rowsAdded = preparedStatement.executeUpdate();
        if( rowsAdded > 0)
            System.out.println("employee added");
        else
            System.out.println("Oops! something went wrong, please try again");
    }

    @Override
    public void updateEmployee(Employee employee) {
    try {
        String sql = "update employee set name = ?, email = ? where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,employee.getName());
        preparedStatement.setString(2,employee.getEmail());
        preparedStatement.setInt(3,employee.getId());
        int rowsUpdated = preparedStatement.executeUpdate();
        if( rowsUpdated > 0)
            System.out.println("employee updated");
        else
            System.out.println("No employee found with id: " + employee.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEmployee(int id) {
    try {
        String sql = "delete from employee where id = ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        int rowsDeleted = preparedStatement.executeUpdate();
        if( rowsDeleted > 0)
            System.out.println("employee deleted");
        else
            System.out.println("No employee found with id: " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
    try {
        String sql = "SELECT * FROM employees";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
           int id = (resultSet.getInt("id"));
           String name = (resultSet.getString("name"));
           String email = (resultSet.getString("email"));
           employees.add(new Employee(id, name, email));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
        return employees;
    }

    @Override
    public Employee getEmployeeById(int id) {
        Employee employee = null;
        try{
            String sql = "select * FROM employees where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
            } else {
                System.out.println("No employee found with id " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }
}
