package com.codecrafters.repository;

import com.codecrafters.database.DataSourceConnectionFactory;
import com.codecrafters.exception.InvoiceFetchingFailedException;
import com.codecrafters.exception.OrderLoadingFailureException;
import com.codecrafters.model.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JdbcOrdersRepositoryEmp implements OrdersRepositoryEmp {

    // method to load approved orders
    @Override
    public List<OrderInfoBase> loadApprovedOrders() {
        List<OrderInfoBase> approvedOrders = new ArrayList<>();

        try (Connection connection = DataSourceConnectionFactory.getConnection()) {
            String sqlQuery = "SELECT o.orderID, o.orderDate, o.totalOrderValue, c.name AS customerName, c.city AS customerCity " +
                    "FROM `Orders` o " +
                    "INNER JOIN Customer c ON o.customerID = c.customerID " +
                    "INNER JOIN Order_Status os ON o.orderID = os.orderID " +
                    "WHERE os.status = 'Approved'";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int orderID = resultSet.getInt("orderID");
                        String orderDate = resultSet.getString("orderDate");
                        float totalOrderValue = resultSet.getFloat("totalOrderValue");
                        String customerName = resultSet.getString("customerName");
                        String customerCity = resultSet.getString("customerCity");


                        approvedOrders.add(new OrderInfoEmployee(orderID, orderDate, totalOrderValue, customerName, customerCity, "Approved"));
                    }
                }
            }
        } catch (SQLException e) {
            throw new OrderLoadingFailureException("failed loading orders from DB");
        }

        return approvedOrders;
    }

    // method to load pending orders
    @Override
    public List<OrderInfoBase> loadPendingOrders() {
        List<OrderInfoBase> pendingOrders = new ArrayList<>();

        try (Connection connection = DataSourceConnectionFactory.getConnection()) {
            String sqlQuery = "SELECT o.orderID, o.orderDate, o.totalOrderValue, c.name AS customerName, c.city AS customerCity " +
                    "FROM `Orders` o " +
                    "INNER JOIN Customer c ON o.customerID = c.customerID " +
                    "INNER JOIN Order_Status os ON o.orderID = os.orderID " +
                    "WHERE os.status = 'Pending'";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int orderID = resultSet.getInt("orderID");
                        String orderDate = resultSet.getString("orderDate");
                        float totalOrderValue = resultSet.getFloat("totalOrderValue");
                        String customerName = resultSet.getString("customerName");
                        String customerCity = resultSet.getString("customerCity");


                        pendingOrders.add(new OrderInfoEmployee(orderID, orderDate, totalOrderValue, customerName, customerCity, "Pending"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pendingOrders;
    }

    // method to load completed orders
    @Override
    public List<OrderInfoBase> loadCompletedOrders() {
        List<OrderInfoBase> completedOrders = new ArrayList<>();

        try (Connection connection = DataSourceConnectionFactory.getConnection()) {
            String sqlQuery = "SELECT o.orderID, o.orderDate, o.totalOrderValue, c.name AS customerName, c.city AS customerCity " +
                    "FROM `Orders` o " +
                    "INNER JOIN Customer c ON o.customerID = c.customerID " +
                    "INNER JOIN Order_Status os ON o.orderID = os.orderID " +
                    "WHERE os.status = 'Completed'";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int orderID = resultSet.getInt("orderID");
                        String orderDate = resultSet.getString("orderDate");
                        float totalOrderValue = resultSet.getFloat("totalOrderValue");
                        String customerName = resultSet.getString("customerName");
                        String customerCity = resultSet.getString("customerCity");


                        completedOrders.add(new OrderInfoEmployee(orderID, orderDate, totalOrderValue, customerName, customerCity, "Completed"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return completedOrders;
    }

    // method to load expired orders
    @Override
    public List<OrderInfoBase> loadExpiredOrders() {
        List<OrderInfoBase> expiredOrders = new ArrayList<>();

        try (Connection connection = DataSourceConnectionFactory.getConnection()) {
            String sqlQuery = "SELECT o.orderID, o.orderDate, o.totalOrderValue, c.name AS customerName, c.city AS customerCity " +
                    "FROM `Orders` o " +
                    "INNER JOIN Customer c ON o.customerID = c.customerID " +
                    "INNER JOIN Order_Status os ON o.orderID = os.orderID " +
                    "WHERE os.status = 'Expired'";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int orderID = resultSet.getInt("orderID");
                        String orderDate = resultSet.getString("orderDate");
                        float totalOrderValue = resultSet.getFloat("totalOrderValue");
                        String customerName = resultSet.getString("customerName");
                        String customerCity = resultSet.getString("customerCity");


                        expiredOrders.add(new OrderInfoEmployee(orderID, orderDate, totalOrderValue, customerName, customerCity, "Expired"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return expiredOrders;
    }

    // method to check is order approved or not using orderID
    @Override
    public boolean isOrderApproved(int id) {
        boolean isApproved = false;

        try (Connection connection = DataSourceConnectionFactory.getConnection()) {
            String sqlQuery = "SELECT status FROM Order_Status WHERE orderID = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                preparedStatement.setInt(1, id); // Set the order ID parameter

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String orderStatus = resultSet.getString("status");

                        // Check if the order status is "Approved"
                        if ("Approved".equals(orderStatus)) {
                            isApproved = true;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isApproved;
    }

    // method to load invoice for approved orders for a customer with some customerId
    @Override
    public Invoice loadInvoiceForApprovedOrder(int id) {
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

    // method to create new quote using order, and other fields.
    @Override
    public boolean createNewQuote(Order order, double totalOrderValue, double shippingCost) {

        try (Connection connection = DataSourceConnectionFactory.getConnection()) {
            String sqlQuery = "INSERT INTO `Orders` (orderDate, customerID, customerShippingAddress, totalOrderValue, shippingCost, shippingAgency) VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                preparedStatement.setDate(1, Date.valueOf(order.getOrderDate()));
                preparedStatement.setInt(2, order.getCustomerID());
                preparedStatement.setString(3, order.getCustomerShippingAddress());
                preparedStatement.setFloat(4, (float) totalOrderValue);
                preparedStatement.setFloat(5, (float) shippingCost);
                preparedStatement.setString(6, order.getShippingAgency());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // method to load product category, which is further used to calculate shipping cost
    @Override
    public List<String> loadProductCategory(List<Product> products) {
        List<String> productCategories = new ArrayList<>();

        try (Connection connection = DataSourceConnectionFactory.getConnection()) {
            String sqlQuery = "SELECT pc.Category " +
                    "FROM ProductCategory pc " +
                    "WHERE pc.ProductID = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                for (Product product : products) {
                    preparedStatement.setInt(1, product.getProductID());
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            String category = resultSet.getString("Category");
                            productCategories.add(category);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productCategories;
    }
}
