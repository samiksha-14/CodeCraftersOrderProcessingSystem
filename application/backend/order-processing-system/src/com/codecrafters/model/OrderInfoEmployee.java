package com.codecrafters.model;

public class OrderInfoEmployee extends OrderInfoBase {

    private String customerName;
    private String customerCity;
    private String status;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OrderInfoEmployee(int orderID, String orderDate, float totalOrderValue, String customerName, String customerCity, String status) {
        super(orderID, orderDate, totalOrderValue);
        this.customerName = customerName;
        this.customerCity = customerCity;
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderInfoEmployee{" +
                "customerName='" + customerName + '\'' +
                ", customerCity='" + customerCity + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
