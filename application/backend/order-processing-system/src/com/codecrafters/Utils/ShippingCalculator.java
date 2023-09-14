package com.codecrafters.Utils;

import com.codecrafters.model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShippingCalculator {
    public static double calculateShippingCost(List<Product> products, double totalOrderValue) {
      // if order value is greater than 1,00,000 there's not shipping cost
        // if order value is less than 1,00,000
        // "Level 1" cost 5% of product price
        // "Level 2" cost 3% of product price
        // "Level 3" cost 2% of product price

        double shippingCost = 0d;
        if(totalOrderValue > 100000) {
            shippingCost = 0;
        } else {
            for(Product product: products) {
                switch (product.getCategory()) {
                    case "Level 1" : shippingCost += (5 * product.getPrice())/100;
                    case "Level 2" : shippingCost += (3 * product.getPrice())/100;
                    case "Level 3" : shippingCost += (2 * product.getPrice())/100;
                }
            }
        }

        return shippingCost;
    }

}
