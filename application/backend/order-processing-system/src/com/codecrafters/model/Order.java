package com.codecrafters.model;

import java.time.LocalDate;

public class Order {
    // Fields representing the columns in the Order table
    private int orderID; // Primary Key
    private LocalDate orderDate;
    private int customerID; // Foreign Key
    private String customerShippingAddress;
    private float totalOrderValue;
    private float shippingCost;
    private String shippingAgency;

    // Constructors

    // Default constructor
    public Order() {
    }

    // Constructor for creating an Order object with initial values
    public Order(LocalDate orderDate, int customerID, String customerShippingAddress, float totalOrderValue, float shippingCost, String shippingAgency) {
        this.orderDate = orderDate;
        this.customerID = customerID;
        this.customerShippingAddress = customerShippingAddress;
        this.totalOrderValue = totalOrderValue;
        this.shippingCost = shippingCost;
        this.shippingAgency = shippingAgency;
    }

    // Getters and Setters for accessing and modifying fields

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerShippingAddress() {
        return customerShippingAddress;
    }

    public void setCustomerShippingAddress(String customerShippingAddress) {
        this.customerShippingAddress = customerShippingAddress;
    }

    public float getTotalOrderValue() {
        return totalOrderValue;
    }

    public void setTotalOrderValue(float totalOrderValue) {
        this.totalOrderValue = totalOrderValue;
    }

    public float getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(float shippingCost) {
        this.shippingCost = shippingCost;
    }

    public String getShippingAgency() {
        return shippingAgency;
    }

    public void setShippingAgency(String shippingAgency) {
        this.shippingAgency = shippingAgency;
    }

    // Override the toString() method for debugging purposes
    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", orderDate=" + orderDate +
                ", customerID=" + customerID +
                ", customerShippingAddress='" + customerShippingAddress + '\'' +
                ", totalOrderValue=" + totalOrderValue +
                ", shippingCost=" + shippingCost +
                ", shippingAgency='" + shippingAgency + '\'' +
                '}';
    }
}

