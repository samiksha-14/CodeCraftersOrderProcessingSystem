package com.codecrafters.model;

public class Customer {
    // Fields representing the columns in the Customer table
    private int customerID; // Primary Key
    private String name;
    private String gstNumber;
    private String address;
    private String city;
    private String email;
    private String phone;
    private String pinCode;
    private int password;

    // Constructors

    // Default constructor
    public Customer() {
    }

    // Constructor for creating a Customer object with initial values
    public Customer(String name, String gstNumber, String address, String city, String email, String phone, String pinCode, int password) {
        this.name = name;
        this.gstNumber = gstNumber;
        this.address = address;
        this.city = city;
        this.email = email;
        this.phone = phone;
        this.pinCode = pinCode;
        this.password = password;
    }

    public Customer(int id, String name, String gstNumber, String address, String city, String email, String phone, String pinCode, int password) {
        this.customerID = id;
        this.name = name;
        this.gstNumber = gstNumber;
        this.address = address;
        this.city = city;
        this.email = email;
        this.phone = phone;
        this.pinCode = pinCode;
        this.password = password;
    }

    // Getters and Setters for accessing and modifying fields

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGstNumber() {
        return gstNumber;
    }

    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    // Override the toString() method for debugging purposes
    @Override
    public String toString() {
        return "Customer{" +
                "customerID=" + customerID +
                ", name='" + name + '\'' +
                ", gstNumber='" + gstNumber + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", pinCode='" + pinCode + '\'' +
                '}';
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }
}
