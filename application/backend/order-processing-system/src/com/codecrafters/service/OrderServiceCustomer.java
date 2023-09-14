package com.codecrafters.service;

import com.codecrafters.exception.CannotFetchInvoiceForNotApprovedNotCompletedOrdersException;
import com.codecrafters.exception.OrderApprovalFailureException;
import com.codecrafters.exception.OrderDoesNotExistException;
import com.codecrafters.model.Invoice;
import com.codecrafters.model.OrderInfoBase;
import com.codecrafters.repository.OrdersRepositoryCustomer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderServiceCustomer extends BaseOrderServiceCustomer {

    OrdersRepositoryCustomer ordersRepositoryCustomer = null;

    public OrderServiceCustomer(OrdersRepositoryCustomer ordersRepositoryCustomer) {
        this.ordersRepositoryCustomer = ordersRepositoryCustomer;
    }

    @Override
    public List<OrderInfoBase> fetchPendingOrders(int id) {
        return ordersRepositoryCustomer.loadPendingOrders(id);
    }

    @Override
    public void approveOrder(int orderId) {

        if (!ordersRepositoryCustomer.checkIfOrderExists(orderId)) {
            throw new OrderDoesNotExistException("order with orderID : " + orderId + ",doesn't not exist");
        }
        Date retrieveOrderDate = ordersRepositoryCustomer.retrieveOrderDate(orderId);

        // Calculate the current date
        Date currentDate = new Date();
        long daysDifference = calculateDaysDifference(retrieveOrderDate, currentDate);
        if (daysDifference > 30) {
            // set order-status to "Expired"
            ordersRepositoryCustomer.changeOrderStatus(orderId, "Expired");

            // if current date is more than 30 days of the order date approval fails
            throw new OrderApprovalFailureException("Order date exceeded the time limit(i.e. 30 days), order id : " + orderId);

        } else {
            // if current date is less than or equal 30days order is approved
            // set order status to "Approved"
            ordersRepositoryCustomer.changeOrderStatus(orderId, "Approved");
        }

    }

    @Override
    public List<OrderInfoBase> fetchApprovedCompletedOrders(int customerId) {
        List<OrderInfoBase> approvedCompletedOrders = new ArrayList<>();
        approvedCompletedOrders.addAll(ordersRepositoryCustomer.loadApprovedOrders(customerId));
        approvedCompletedOrders.addAll(ordersRepositoryCustomer.loadCompletedOrders(customerId));
        return approvedCompletedOrders;
    }


    // Helper method to calculate the difference in days between two dates
    private long calculateDaysDifference(Date date1, Date date2) {
        long diffInMilliseconds = date2.getTime() - date1.getTime();
        return diffInMilliseconds / (24 * 60 * 60 * 1000); // Convert milliseconds to days
    }

    @Override
    public List<OrderInfoBase> fetchAllOrders() {
        return null;
    }

    @Override
    public Invoice fetchInvoice(int id) {
        // check if the order is approved or completed
        if (!ordersRepositoryCustomer.isOrderApprovedCompleted(id)) {
            throw new CannotFetchInvoiceForNotApprovedNotCompletedOrdersException("order is neither approved nor completed, hence cannot fetch any invoice for the order Id : " + id);
        }

        return ordersRepositoryCustomer.loadInvoiceForApprovedCompletedOrder(id);
    }
}
