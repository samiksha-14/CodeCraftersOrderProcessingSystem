package com.codecrafters.service;

import com.codecrafters.Utils.ShippingCalculator;
import com.codecrafters.Utils.TotalOrderValueCalculator;
import com.codecrafters.exception.CannotFetchInvoiceForNonApprovedOrderException;
import com.codecrafters.exception.CustomerNotFoundException;
import com.codecrafters.model.Invoice;
import com.codecrafters.model.Order;
import com.codecrafters.model.OrderInfoBase;
import com.codecrafters.model.Product;
import com.codecrafters.repository.CustomerRepository;
import com.codecrafters.repository.OrdersRepositoryEmp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class OrderServiceEmployee extends BaseOrderServiceEmployee{
    OrdersRepositoryEmp ordersRepositoryEmp = null;
    CustomerRepository customerRepository = null;

    public OrderServiceEmployee(OrdersRepositoryEmp ordersRepositoryEmp, CustomerRepository customerRepository) {
        this.ordersRepositoryEmp = ordersRepositoryEmp;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<OrderInfoBase> fetchAllOrders() {

        List<OrderInfoBase> allOrders = new ArrayList<>();
        allOrders.addAll(ordersRepositoryEmp.loadApprovedOrders());
        allOrders.addAll(ordersRepositoryEmp.loadCompletedOrders());
        allOrders.addAll(ordersRepositoryEmp.loadExpiredOrders());
        allOrders.addAll(ordersRepositoryEmp.loadPendingOrders());

        return allOrders;
    }

    @Override
    public List<OrderInfoBase> fetchCompletedOrder() {
        return ordersRepositoryEmp.loadCompletedOrders();
    }

    @Override
    public List<OrderInfoBase> fetchApprovedOrders() {
        return ordersRepositoryEmp.loadApprovedOrders();
    }

    @Override
    public List<OrderInfoBase> fetchPendingOrders() {
        return ordersRepositoryEmp.loadPendingOrders();
    }

    @Override
    public List<OrderInfoBase> fetchExpiredOrders() {
        return ordersRepositoryEmp.loadExpiredOrders();
    }

    @Override
    public Invoice fetchInvoice(int id) {
        if (!ordersRepositoryEmp.isOrderApproved(id)) {
            throw new CannotFetchInvoiceForNonApprovedOrderException("cannot fetch invoice for non-approved order : " + id);
        }
        return ordersRepositoryEmp.loadInvoiceForApprovedOrder(id);
    }

    @Override
    public void createNewQuote(Date orderDate, List<Product> products, int customerID, String customerGSTNo, String customerShippingAddress, String customerCity, String customerMobileNo, String customerEmail, String customerPinCode, String shippingAgency) {
        // check if the customer already exists
        if(customerRepository.checkIfCustomerExists(customerID)) {
            // 1. Check if the Order Date is in the future (not a previous date)
            Date currentDate = new Date();
            if (orderDate.before(currentDate)) {
                throw new IllegalArgumentException("Order Date cannot be a previous date.");
            }

            // 2. Check if the Order Date is not the current date or within 3 working days (Monday to Friday)
            Calendar cal = Calendar.getInstance();
            cal.setTime(orderDate);
            int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

            // Check if the Order Date falls on a Saturday (Calendar.SATURDAY) or Sunday (Calendar.SUNDAY)
            if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
                throw new IllegalArgumentException("Order Date cannot be on a weekend (Saturday or Sunday).");
            }

            // Check if the Order Date is on the current date or within 3 working days
            cal.setTime(currentDate);
            int workingDayCount = 0;
            while (!cal.getTime().after(orderDate)) {
                dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

                // Check if the day is a working day (Monday to Friday)
                if (dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY) {
                    workingDayCount++;
                }

                // Move to the next day
                cal.add(Calendar.DAY_OF_MONTH, 1);
            }

            if (workingDayCount <= 3) {
                throw new IllegalArgumentException("Order Date cannot be the current date or within 3 working days.");
            }

            double totalOrderValue = TotalOrderValueCalculator.calculateTotalOrderValue(products);
            double shippingCost = ShippingCalculator.calculateShippingCost(products, totalOrderValue);

            Order newOrder = new Order(orderDate, customerID, customerShippingAddress, (float)totalOrderValue, (float)shippingCost, shippingAgency);
            ordersRepositoryEmp.createNewQuote(newOrder, totalOrderValue, shippingCost);
        } else {
            throw new CustomerNotFoundException("Customer not found : " + customerID);
        }

    }

    @Override
    public void createNewQuote(Date orderDate, List<Product> products, String customerName, String customerGSTNo, String customerShippingAddress, String customerCity, String customerMobileNo, String customerEmail, String customerPinCode, String shippingAgency) {
        if(customerRepository.checkIfCustomerExists(customerName)) {
            int id = customerRepository.getCustomerId(customerName);// 1. Check if the Order Date is in the future (not a previous date)
            Date currentDate = new Date();
            if (orderDate.before(currentDate)) {
                throw new IllegalArgumentException("Order Date cannot be a previous date.");
            }

            // 2. Check if the Order Date is not the current date or within 3 working days (Monday to Friday)
            Calendar cal = Calendar.getInstance();
            cal.setTime(orderDate);
            int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

            // Check if the Order Date falls on a Saturday (Calendar.SATURDAY) or Sunday (Calendar.SUNDAY)
            if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
                throw new IllegalArgumentException("Order Date cannot be on a weekend (Saturday or Sunday).");
            }

            // Check if the Order Date is on the current date or within 3 working days
            cal.setTime(currentDate);
            int workingDayCount = 0;
            while (!cal.getTime().after(orderDate)) {
                dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

                // Check if the day is a working day (Monday to Friday)
                if (dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY) {
                    workingDayCount++;
                }

                // Move to the next day
                cal.add(Calendar.DAY_OF_MONTH, 1);
            }

            if (workingDayCount <= 3) {
                throw new IllegalArgumentException("Order Date cannot be the current date or within 3 working days.");
            }

            double totalOrderValue = TotalOrderValueCalculator.calculateTotalOrderValue(products);
            double shippingCost = ShippingCalculator.calculateShippingCost(products, totalOrderValue);

            Order newOrder = new Order(orderDate, id, customerShippingAddress, (float)totalOrderValue, (float)shippingCost, shippingAgency);
            ordersRepositoryEmp.createNewQuote(newOrder, totalOrderValue, shippingCost);
        } else {
            throw new CustomerNotFoundException("Customer not found : " + customerName);
        }
    }
}
