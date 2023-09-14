package com.codecrafters.repository;

import com.codecrafters.model.Invoice;
import com.codecrafters.model.Order;
import com.codecrafters.model.OrderInfoBase;
import com.codecrafters.model.Product;

import java.util.List;

public interface OrdersRepositoryEmp {
    List<OrderInfoBase> loadApprovedOrders();

    List<OrderInfoBase> loadPendingOrders();

    List<OrderInfoBase> loadCompletedOrders();

    List<OrderInfoBase> loadExpiredOrders();

    boolean isOrderApproved(int id);

    Invoice loadInvoiceForApprovedOrder(int id);

    boolean createNewQuote(Order order, double totalOrderValue, double shippingCost);

    List<String> loadProductCategory(List<Product> products);

}
