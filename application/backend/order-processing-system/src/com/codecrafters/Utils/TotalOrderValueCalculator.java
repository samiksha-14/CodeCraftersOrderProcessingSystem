package com.codecrafters.Utils;

import com.codecrafters.model.Product;

import java.util.List;

public class TotalOrderValueCalculator {

    public static double calculateTotalOrderValue(List<Product> products) {
        double totalOrderValue = 0d;
        for (Product product : products) {
            totalOrderValue += product.getPrice();
        }

        return totalOrderValue;
    }
}
