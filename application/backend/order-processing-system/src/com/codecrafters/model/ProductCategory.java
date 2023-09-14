package com.codecrafters.model;

public class ProductCategory {
    // Fields representing the columns in the ProductCategory table
    private int productID; // Primary Key and Foreign Key
    private String category; // Category can be Level 1, Level 2, or Level 3

    // Constructors

    // Default constructor
    public ProductCategory() {
    }

    // Constructor for creating a ProductCategory object with initial values
    public ProductCategory(int productID, String category) {
        this.productID = productID;
        this.category = category;
    }

    // Getters and Setters for accessing and modifying fields

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // Override the toString() method for debugging purposes
    @Override
    public String toString() {
        return "ProductCategory{" +
                "productID=" + productID +
                ", category='" + category + '\'' +
                '}';
    }
}

