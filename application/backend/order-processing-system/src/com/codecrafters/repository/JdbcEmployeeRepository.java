package com.codecrafters.repository;

import com.codecrafters.database.DataSourceConnectionFactory;
import com.codecrafters.exception.EmployeeLoginUpdateFailureException;
import com.codecrafters.exception.EmployeeNotFoundException;
import com.codecrafters.exception.WrongCredentialsException;
import com.codecrafters.model.Employee;

import java.sql.*;
import java.time.LocalDateTime;

public class JdbcEmployeeRepository implements EmployeeRepository {

    // method to retrieve employee password
    public int retrieveEmployeePassword(int id, int password) {
        int retrievedPassword = 0;
        try (Connection connection = DataSourceConnectionFactory.getConnection()) {
            String sqlQuery = "SELECT password FROM Employees WHERE employeeId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeQuery();
                try (ResultSet resultSet = preparedStatement.getResultSet()) {
                    if (resultSet.next()) {
                        retrievedPassword = resultSet.getInt("password");

                    } else {
                        throw new WrongCredentialsException("Wrong Username Supplied");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return retrievedPassword;
    }

    // method to check employee login status
    @Override
    public boolean employeeLoginStatus(int id) {
        boolean loggedIn = false;
        try (Connection connection = DataSourceConnectionFactory.getConnection()) {
            String sqlQuery = "SELECT loggedIn FROM Employees WHERE employeeId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeQuery();
                try (ResultSet resultSet = preparedStatement.getResultSet()) {
                    if (resultSet.next()) {
                        loggedIn = resultSet.getBoolean("loggedIn");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return loggedIn;
    }

    // method to update employee login status
    @Override
    public void updateEmployeeLoginStatus(int id, boolean status) {
        try (Connection connection = DataSourceConnectionFactory.getConnection()) {
            if (status) {
                String sqlQuery = "UPDATE Employees SET loggedIn = ?, lastLoggedIn = ? WHERE employeeId = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                    preparedStatement.setBoolean(1, true);
                    preparedStatement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
                    preparedStatement.setInt(3, id);

                    int rowsUpdated = preparedStatement.executeUpdate();

                    if (rowsUpdated <= 0) {
                        throw new EmployeeLoginUpdateFailureException("Login update failed for employee ID: " + id);
                    }
                }
            } else {
                String sqlQuery = "UPDATE Employees SET loggedIn = ? WHERE employeeId = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                    preparedStatement.setBoolean(1, false);
                    preparedStatement.setInt(2, id);

                    int rowsUpdated = preparedStatement.executeUpdate();

                    if (rowsUpdated <= 0) {
                        throw new EmployeeLoginUpdateFailureException("Login update failed for employee ID: " + id);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // method to load employee using employee id
    @Override
    public Employee loadEmployee(int id) {
        try (Connection connection = DataSourceConnectionFactory.getConnection()) {
            String sqlQuery = "SELECT * FROM Employees WHERE employeeId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        Employee employee = new Employee();
                        employee.setEmployeeID(resultSet.getInt("employeeId"));
                        employee.setUserName(resultSet.getString("userName"));
                        employee.setPassword(resultSet.getInt("password"));
                        employee.setLastLoggedIn(resultSet.getTimestamp("lastLoggedIn").toLocalDateTime());
                        employee.setLoggedIn(resultSet.getBoolean("loggedIn"));
                        return employee;
                    } else {
                        throw new EmployeeNotFoundException("employee not found with id" + id);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
