package com.codecrafters.repository;

import com.codecrafters.model.Product;

import java.util.List;
import java.util.Map;

public interface ProductsRepository {
    double getTotalOrderValue(List<Map<Product, Integer>> products);
}
