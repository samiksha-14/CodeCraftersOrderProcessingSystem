package com.codecrafters.repository;

import com.codecrafters.database.DataSourceConnectionFactory;
import com.codecrafters.exception.WrongCredentialsException;
import com.codecrafters.model.Customer;

import java.sql.*;

public class JdbcCustomerRepository implements CustomerRepository {
    @Override
    public int retrieveCustomerPassword(int id, int password) {

        int retrievedPass = 0;

        try (Connection connection = DataSourceConnectionFactory.getConnection()) {
            String sql = "SELECT password FROM Customer WHERE customerID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id); // Set the parameter value
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        retrievedPass = resultSet.getInt("password");
                    } else {
                        throw new WrongCredentialsException("Wrong Username Supplied");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retrievedPass;
    }

    @Override
    public int retrieveCustomerPassword(String name, int password) {
        int retrievedPass = 0;

        try (Connection connection = DataSourceConnectionFactory.getConnection()) {
            String sqlQuery = "SELECT password FROM Customer WHERE name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                preparedStatement.setString(1, name); // Set the parameter value

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        retrievedPass = resultSet.getInt("password");
                    } else {
                        throw new WrongCredentialsException("Wrong Credentials");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retrievedPass;
    }

    @Override
    public boolean checkIfCustomerExists(int id) {
        boolean customerExists = false;

        try (Connection connection = DataSourceConnectionFactory.getConnection()) {
            String sqlQuery = "SELECT customerID FROM Customer WHERE customerID = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                preparedStatement.setInt(1, id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        customerExists = true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerExists;
    }

    @Override
    public boolean checkIfCustomerExists(String name) {
        boolean customerExists = false;

        try (Connection connection = DataSourceConnectionFactory.getConnection()) {
            String sqlQuery = "SELECT customerID FROM Customer WHERE NAME = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                preparedStatement.setString(1, name);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        customerExists = true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerExists;
    }

    @Override
    public int getCustomerId(String name) {
        int retrievedCustomerId = 0;
        try (Connection connection = DataSourceConnectionFactory.getConnection()) {
            String sqlQuery = "SELECT customerID FROM Customer WHERE name = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                preparedStatement.setString(1, name);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        retrievedCustomerId = resultSet.getInt("customerId");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return retrievedCustomerId;
    }

    @Override
    public void addNewCustomer(Customer customer) {
        try (Connection connection = DataSourceConnectionFactory.getConnection()) {
            String sqlQuery = "INSERT INTO Customer (name, gstNumber, address, city, email, phone, pinCode, password) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                preparedStatement.setString(1, customer.getName());
                preparedStatement.setString(2, customer.getGstNumber());
                preparedStatement.setString(3, customer.getAddress());
                preparedStatement.setString(4, customer.getCity());
                preparedStatement.setString(5, customer.getEmail());
                preparedStatement.setString(6, customer.getPhone());
                preparedStatement.setString(7, customer.getPinCode());
                preparedStatement.setInt(8, customer.getPassword());

                int rowsAffected = preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
