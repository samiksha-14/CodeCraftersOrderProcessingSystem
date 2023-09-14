package com.codecrafters.model;

public class OrderInfoBase {
    private int orderID;

    private String orderDate;
    private float totalOrderValue;

    public OrderInfoBase(int orderID, String orderDate, float totalOrderValue) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.totalOrderValue = totalOrderValue;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public float getTotalOrderValue() {
        return totalOrderValue;
    }

    public void setTotalOrderValue(float totalOrderValue) {
        this.totalOrderValue = totalOrderValue;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "orderID=" + orderID + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", totalOrderValue=" + totalOrderValue +
                '\'' +
                '}';
    }
}
