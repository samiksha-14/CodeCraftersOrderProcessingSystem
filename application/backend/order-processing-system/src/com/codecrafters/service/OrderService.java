package com.codecrafters.service;

import com.codecrafters.model.Invoice;
import com.codecrafters.model.OrderInfoBase;

import java.util.List;

public interface OrderService {
    List<OrderInfoBase> fetchAllOrders();
    Invoice fetchInvoice(int id);
}
