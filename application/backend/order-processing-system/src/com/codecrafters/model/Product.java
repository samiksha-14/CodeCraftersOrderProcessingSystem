package com.codecrafters.model;

public class Product {
    // Fields representing the columns in the Product table
    private int productID; // Primary Key
    private String name;
    private float price;

    // Constructors

    // Default constructor
    public Product() {
    }

    // Constructor for creating a Product object with initial values
    public Product(int productID, String name, float price) {
        this.productID = productID;
        this.name = name;
        this.price = price;
    }

    // Getters and Setters for accessing and modifying fields

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category='" +
                '}';
    }

}
