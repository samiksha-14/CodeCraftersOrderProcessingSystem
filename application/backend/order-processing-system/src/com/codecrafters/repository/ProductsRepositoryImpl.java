package com.codecrafters.repository;

import com.codecrafters.database.DataSourceConnectionFactory;
import com.codecrafters.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ProductsRepositoryImpl implements ProductsRepository{
    @Override
    public double getTotalOrderValue(List<Map<Product, Integer>> products) {
        double totalValue = 0.0;

        try (Connection connection = DataSourceConnectionFactory.getConnection()) {
            for (Map<Product, Integer> productMap : products) {
                for (Map.Entry<Product, Integer> entry : productMap.entrySet()) {
                    Product product = entry.getKey();
                    int quantity = entry.getValue();

                    double productPrice = getProductPrice(connection, product.getProductID());
                    totalValue += productPrice * quantity;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalValue;
    }

    private double getProductPrice(Connection connection, int productID) throws SQLException {

        double productPrice = 0;

        String sqlQuery = "SELECT price FROM Product WHERE productID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, productID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    productPrice = resultSet.getDouble("price");
                }
            }
        }

        return productPrice;
    }
}
