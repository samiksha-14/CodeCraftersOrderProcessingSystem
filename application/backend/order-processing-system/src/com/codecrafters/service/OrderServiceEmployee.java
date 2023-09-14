package com.codecrafters.service;

import com.codecrafters.Utils.ShippingCalculator;
import com.codecrafters.Utils.TotalOrderValueCalculator;
import com.codecrafters.exception.CannotFetchInvoiceForNonApprovedOrderException;
import com.codecrafters.exception.CustomerNotFoundException;
import com.codecrafters.exception.OrderDateBeyondThreeWorkingDaysException;
import com.codecrafters.exception.OrderDateCannotBePreviousDateException;
import com.codecrafters.model.Invoice;
import com.codecrafters.model.Order;
import com.codecrafters.model.OrderInfoBase;
import com.codecrafters.model.Product;
import com.codecrafters.repository.CustomerRepository;
import com.codecrafters.repository.OrdersRepositoryEmp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceEmployee extends BaseOrderServiceEmployee {
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
    public void createNewQuote(LocalDate orderDate, List<Product> products, int customerID, String customerGSTNo, String customerShippingAddress, String customerCity, String customerMobileNo, String customerEmail, String customerPinCode, String shippingAgency) {
        // check if the customer already exists
        if (customerRepository.checkIfCustomerExists(customerID)) {
            // check and throw errors
            // order date cannot be previous date
            LocalDate currentDate = LocalDate.now();
            if (orderDate.isBefore(currentDate)) {
                throw new OrderDateCannotBePreviousDateException("Order date cannot be a previous date");
            }

            // Check if order date is within 3 working days (Mon to Fri)
            LocalDate maxOrderDate = currentDate.plusDays(3); // Assuming 3 working days
            if (orderDate.isAfter(maxOrderDate)) {
                throw new OrderDateBeyondThreeWorkingDaysException("Order date must be within 3 working days (Mon to Fri)");
            }

            double totalOrderValue = TotalOrderValueCalculator.calculateTotalOrderValue(products);
            List<String> productCategories = ordersRepositoryEmp.loadProductCategory(products);
            double shippingCost = ShippingCalculator.calculateShippingCost(products, productCategories, totalOrderValue);

            Order newOrder = new Order(orderDate, customerID, customerShippingAddress, (float) totalOrderValue, (float) shippingCost, shippingAgency);
            ordersRepositoryEmp.createNewQuote(newOrder, totalOrderValue, shippingCost);
        } else {
            throw new CustomerNotFoundException("Customer not found : " + customerID);
        }

    }

    @Override
    public void createNewQuote(LocalDate orderDate, List<Product> products, String customerName, String customerGSTNo, String customerShippingAddress, String customerCity, String customerMobileNo, String customerEmail, String customerPinCode, String shippingAgency) {
        if (customerRepository.checkIfCustomerExists(customerName)) {
            int id = customerRepository.getCustomerId(customerName);// 1. Check if the Order Date is in the future (not a previous date)

            double totalOrderValue = TotalOrderValueCalculator.calculateTotalOrderValue(products);
            List<String> productCategories = ordersRepositoryEmp.loadProductCategory(products);
            double shippingCost = ShippingCalculator.calculateShippingCost(products, productCategories, totalOrderValue);

            Order newOrder = new Order(orderDate, id, customerShippingAddress, (float) totalOrderValue, (float) shippingCost, shippingAgency);
            ordersRepositoryEmp.createNewQuote(newOrder, totalOrderValue, shippingCost);
        } else {
            throw new CustomerNotFoundException("Customer not found : " + customerName);
        }
    }
}
