package com.codecrafters.repository;

import com.codecrafters.model.Invoice;
import com.codecrafters.model.OrderInfoBase;

import java.util.Date;
import java.util.List;

public interface OrdersRepositoryCustomer {
    List<OrderInfoBase> loadPendingOrders(int id);

    Date retrieveOrderDate(int orderId);

    void changeOrderStatus(int orderId, String status);

    boolean checkIfOrderExists(int orderId);

    List<OrderInfoBase> loadCompletedOrders(int id);

    List<OrderInfoBase> loadApprovedOrders(int id);

    boolean isOrderApprovedCompleted(int id);

    Invoice loadInvoiceForApprovedCompletedOrder(int id);
}
