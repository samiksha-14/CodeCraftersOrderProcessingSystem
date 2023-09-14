create database OrderManagementCustomerIDNameCustomerID;
USE  OrderManagement;
-- Create the Customer table
CREATE TABLE Customer (
    CustomerID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(255),
    GSTNumber VARCHAR(255),
    Address VARCHAR(255),
    City VARCHAR(255),
    Email VARCHAR(255),
    Phone VARCHAR(255),
    PinCode VARCHAR(255),
    UserName VARCHAR(255),
    Password INT
);

-- Create the Product table
CREATE TABLE Product (
    ProductID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(255),
    Price FLOAT
);

-- Create the Order table
CREATE TABLE Orders (
    OrderID INT AUTO_INCREMENT PRIMARY KEY,
    OrderDate DATE,
    CustomerID INT,
    CustomerShippingAddress VARCHAR(255),
    TotalOrderValue FLOAT,
    ShippingCost FLOAT,
    ShippingAgency VARCHAR(255),
    FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID)
);
-- Create the Cart table
CREATE TABLE Cart (
    CartID INT AUTO_INCREMENT PRIMARY KEY,
    OrderID INT,
    ProductID INT,
    Quantity INT,
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
    FOREIGN KEY (ProductID) REFERENCES Product(ProductID)
);

-- Create the Invoice table
CREATE TABLE Invoice (
    InvoiceID INT AUTO_INCREMENT PRIMARY KEY,
    InvoiceDate DATETIME,
    OrderID INT,
    CustomerID INT,
    TotalGSTAmount FLOAT,
    TotalInvoiceValue FLOAT,
    TypeOfGST INT,
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
    FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID)
);

-- Create the ProductCategory table
CREATE TABLE ProductCategory (
    ProductID INT,
    Category VARCHAR(255),
    PRIMARY KEY (ProductID, Category),
    FOREIGN KEY (ProductID) REFERENCES Product(ProductID)
);

-- Create the Order_Status table
CREATE TABLE Order_Status (
    OrderID INT PRIMARY KEY,
    Status VARCHAR(255),
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID)
);

-- Create the Company table
CREATE TABLE Company (
    CompanyID INT AUTO_INCREMENT PRIMARY KEY,
    CompanyName VARCHAR(255),
    CompanyAddress VARCHAR(255),
    CompanyCity VARCHAR(255),
    GSTNumber VARCHAR(255)
);

-- Create the Employees table
CREATE TABLE Employees (
    EmployeeID INT AUTO_INCREMENT PRIMARY KEY,
    UserName VARCHAR(255),
    Password INT,
	lastLoggedIn DATETIME,
    loggedIn BOOLEAN DEFAULT FALSE
);


-- Create the Invoice_status table
CREATE TABLE Invoice_status (
    InvoiceID INT PRIMARY KEY,
    Status VARCHAR(255)
);
