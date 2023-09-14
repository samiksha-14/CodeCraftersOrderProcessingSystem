package com.codecrafters.repository;

import com.codecrafters.database.DataSourceConnectionFactory;
import com.codecrafters.exception.InvoiceFetchingFailedException;
import com.codecrafters.exception.OrderStatusUpdateFailureException;
import com.codecrafters.model.Invoice;
import com.codecrafters.model.OrderInfoBase;
import com.codecrafters.model.OrderInfoCustomer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JdbcOrdersRepositoryCustomer implements OrdersRepositoryCustomer {
    @Override
    public List<OrderInfoBase> loadPendingOrders(int id) {

        List<OrderInfoBase> pendingOrders = new ArrayList<>();

        try (Connection connection = DataSourceConnectionFactory.getConnection()) {
            String sqlQuery = "SELECT o.OrderID, o.OrderDate, o.CustomerID, o.CustomerShippingAddress, o.TotalOrderValue, o.ShippingCost, o.ShippingAgency, os.Status " +
                    "FROM Orders o " +
                    "INNER JOIN Order_Status os ON o.OrderID = os.OrderID " +
                    "WHERE o.CustomerID = ? AND os.Status = 'Pending'";


            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                preparedStatement.setInt(1, id)
                ;
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int orderID = resultSet.getInt("orderID");
                        String orderDate = resultSet.getString("orderDate");
                        float totalOrderValue = resultSet.getFloat("totalOrderValue");
                        float shippingCost = resultSet.getFloat("shippingCost");


                        pendingOrders.add(new OrderInfoCustomer(orderID, orderDate, totalOrderValue, shippingCost));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pendingOrders;
    }

    @Override
    public Date retrieveOrderDate(int orderId) {
        Date date = new Date();

        try (Connection connection = DataSourceConnectionFactory.getConnection()) {
            String sqlQuery = "SELECT OrderDate FROM Orders WHERE OrderID = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                preparedStatement.setInt(1, orderId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        date = resultSet.getDate("OrderDate");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return date;
    }

    @Override
    public boolean checkIfOrderExists(int orderId) {
        boolean orderExists = false;
        try (Connection connection = DataSourceConnectionFactory.getConnection()) {
            String sqlQuery = "SELECT COUNT(*) FROM Orders WHERE OrderID = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                preparedStatement.setInt(1, orderId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        if (count > 0) {
                            orderExists = true;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderExists; // Returns false if an exception occurred or no order was found
    }

    @Override
    public List<OrderInfoBase> loadCompletedOrders(int id) {
        List<OrderInfoBase> completedOrders = new ArrayList<>();

        try (Connection connection = DataSourceConnectionFactory.getConnection()) {
            String sqlQuery = "SELECT o.OrderID, o.OrderDate, o.CustomerID, o.CustomerShippingAddress, o.TotalOrderValue, o.ShippingCost, o.ShippingAgency, os.Status " +
                    "FROM Orders o " +
                    "INNER JOIN Order_Status os ON o.OrderID = os.OrderID " +
                    "WHERE o.CustomerID = ? AND os.Status = 'Completed'";


            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int orderID = resultSet.getInt("orderID");
                        String orderDate = resultSet.getString("orderDate");
                        float totalOrderValue = resultSet.getFloat("totalOrderValue");
                        float shippingCost = resultSet.getFloat("shippingCost");
                        String status = resultSet.getString("status");


                        completedOrders.add(new OrderInfoCustomer(orderID, orderDate, totalOrderValue, shippingCost, status));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return completedOrders;
    }

    @Override
    public List<OrderInfoBase> loadApprovedOrders(int id) {
        List<OrderInfoBase> approvedOrders = new ArrayList<>();

        try (Connection connection = DataSourceConnectionFactory.getConnection()) {
            String sqlQuery = "SELECT o.OrderID, o.OrderDate, o.CustomerID, o.CustomerShippingAddress, o.TotalOrderValue, o.ShippingCost, o.ShippingAgency, os.Status " +
                    "FROM Orders o " +
                    "INNER JOIN Order_Status os ON o.OrderID = os.OrderID " +
                    "WHERE o.CustomerID = ? AND os.Status = 'Approved'";


            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int orderID = resultSet.getInt("orderID");
                        String orderDate = resultSet.getString("orderDate");
                        float totalOrderValue = resultSet.getFloat("totalOrderValue");
                        float shippingCost = resultSet.getFloat("shippingCost");
                        String status = resultSet.getString("status");


                        approvedOrders.add(new OrderInfoCustomer(orderID, orderDate, totalOrderValue, shippingCost, status));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return approvedOrders;
    }

    @Override
    public boolean isOrderApprovedCompleted(int id) {
        boolean isApprovedOrCompleted = false;

        try (Connection connection = DataSourceConnectionFactory.getConnection()) {
            String sqlQuery = "SELECT Status FROM Order_Status WHERE OrderID = ? AND (Status = 'Approved' OR Status = 'Completed')";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                preparedStatement.setInt(1, id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // If the query returned a row, it means the order is either Approved or Completed
                        isApprovedOrCompleted = true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isApprovedOrCompleted;
    }

    @Override
    public Invoice loadInvoiceForApprovedCompletedOrder(int id) {
        Invoice invoice = null;

        try (Connection connection = DataSourceConnectionFactory.getConnection()) {
            String sqlQuery = "SELECT *" +
                    "FROM Invoice " +
                    "WHERE orderID = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int invoiceID = resultSet.getInt("invoiceID");
                        LocalDateTime invoiceDate = resultSet.getTimestamp("invoiceDate").toLocalDateTime();
                        int orderID = resultSet.getInt("orderID");
                        int customerID = resultSet.getInt("customerID");
                        float totalGSTAmount = resultSet.getFloat("totalGSTAmount");
                        float totalInvoiceValue = resultSet.getFloat("totalInvoiceValue");
                        int typeOfGST = resultSet.getInt("typeOfGST");

                        invoice = new Invoice(invoiceID, invoiceDate, orderID, customerID, totalGSTAmount, totalInvoiceValue, typeOfGST);
                    } else {
                        throw new InvoiceFetchingFailedException("invoice fetch failed for the invoice : " + id);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoice;
    }

    @Override
    public void changeOrderStatus(int orderId, String status) {
        try (Connection connection = DataSourceConnectionFactory.getConnection()) {
            String sqlUpdate = "UPDATE Order_Status SET Status = ? WHERE OrderID = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate)) {
                preparedStatement.setString(1, status);
                preparedStatement.setInt(2, orderId);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected <= 0) {
                    throw new OrderStatusUpdateFailureException("Failure in updating the status for order id : " + orderId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
