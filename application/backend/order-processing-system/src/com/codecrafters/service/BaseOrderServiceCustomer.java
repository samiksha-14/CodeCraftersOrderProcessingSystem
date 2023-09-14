package com.codecrafters.service;

import com.codecrafters.model.OrderInfoBase;

import java.util.List;

public abstract class BaseOrderServiceCustomer implements OrderService {
    public abstract List<OrderInfoBase> fetchPendingOrders(int id);

    public abstract void approveOrder(int orderId);

    public abstract List<OrderInfoBase> fetchApprovedCompletedOrders(int customerId);

}