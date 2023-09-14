package com.codecrafters.model;

public class Company {
    // Fields representing the columns in the Company table
    private int companyID; // Primary Key and Auto-Incremented
    private String companyName;
    private String companyAddress;
    private String companyCity;
    private String gstNumber;

    // Constructors

    // Default constructor
    public Company() {
    }

    // Constructor for creating a Company object with initial values
    public Company(String companyName, String companyAddress, String companyCity, String gstNumber) {
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.companyCity = companyCity;
        this.gstNumber = gstNumber;
    }

    // Getters and Setters

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyCity() {
        return companyCity;
    }

    public void setCompanyCity(String companyCity) {
        this.companyCity = companyCity;
    }

    public String getGstNumber() {
        return gstNumber;
    }

    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }

    // Override the toString() method for debugging purposes
    @Override
    public String toString() {
        return "Company{" +
                "companyID=" + companyID +
                ", companyName='" + companyName + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", companyCity='" + companyCity + '\'' +
                ", gstNumber='" + gstNumber + '\'' +
                '}';
    }
}
