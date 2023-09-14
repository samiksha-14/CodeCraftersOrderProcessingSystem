package com.codecrafters.service;


import com.codecrafters.model.OrderInfoBase;
import com.codecrafters.model.Product;

import java.util.Date;
import java.util.List;

public abstract class BaseOrderServiceEmployee implements OrderService {
    public abstract void createNewQuote(Date orderDate, List<Product> products, int customerID, String customerGSTNo, String customerShippingAddress, String customerCity, String customerMobileNo, String customerEmail, String customerPinCode, String shippingAgency);

    public abstract void createNewQuote(Date orderDate, List<Product> products, String customerName, String customerGSTNo, String customerShippingAddress, String customerCity, String customerMobileNo, String customerEmail, String customerPinCode, String shippingAgency);

    public abstract List<OrderInfoBase> fetchCompletedOrder();

    public abstract List<OrderInfoBase> fetchApprovedOrders();

    public abstract List<OrderInfoBase> fetchPendingOrders();

    public abstract List<OrderInfoBase> fetchExpiredOrders();
}
