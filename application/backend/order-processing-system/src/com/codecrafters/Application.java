package com.codecrafters;

import com.codecrafters.exception.*;
import com.codecrafters.model.OrderInfoBase;
import com.codecrafters.model.Product;
import com.codecrafters.repository.*;
import com.codecrafters.service.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {


        CustomerRepository customerRepository = new JdbcCustomerRepository();
        BaseCustomerLoginService customerLoginService = new CustomerLoginService(customerRepository);

        // log-in the customer either using (id, password) or (name, password) :
        // returns true on successful login...
        int customerId = 1;
        int password_1 = 12345;

        try {
            if (customerLoginService.login(customerId, password_1)) {
                System.out.println("successful customer log-in");
            } else {
                throw new WrongCredentialsException("wrong credentials, id : " + customerId + " password : " + password_1);
            }
        } catch (WrongCredentialsException eObject) {
            eObject.printStackTrace();
        }

        String customerName = "Jonah";
        int password_2 = 2849;
        try {
            if (customerLoginService.login(customerName, password_2)) {
                System.out.println("successful customer log-in");
            } else {
                throw new WrongCredentialsException("wrong credentials, name : " + customerName + " password : " + password_2);
            }
        } catch (WrongCredentialsException eObject) {
            eObject.printStackTrace();
        }


        EmployeeRepository employeeRepository = new JdbcEmployeeRepository();
        BaseEmployeeLoginService employeeLoginService = new EmployeeLoginService(employeeRepository);

        // log-in the employee using (id, password) :
        // returns true on successful log-in
        // if the Employee is already logged-in throw EmployeeAlreadyLoggedException.
        try {
            if (employeeLoginService.login(1, 4146)) {
                System.out.println("successful employee log-in");
            }

            // added feature to log out an employee using (id, password) :
            // returns true on successful logout
            if (employeeLoginService.logout(1, 4146)) {
                System.out.println("employee log-out successful");
            }

        } catch (EmployeeAlreadyLoggedException | CannotLogoutBeforeLoginEmployeeException eObject) {
            eObject.printStackTrace();
        }

        EmployeeDetailsService employeeDetailsService = new EmployeeDetailsServiceImpl(employeeRepository);
        int employeeId_1 = 1;
        // to show employee information on the top right we can fetch it from the database using, fetchEmployeeDetails(employee id)
        try {
            System.out.println("-".repeat(100));
            System.out.println("EMPLOYEE DETAILS FOR EMPLOYEE ID " + employeeId_1 + " ->>>");
            System.out.println(employeeDetailsService.fetchEmployeeDetails(employeeId_1));
        } catch (EmployeeDetailsLoadingFailureException | EmployeeNotFoundException eObject) {
            eObject.printStackTrace();
        }

        OrdersRepositoryEmp ordersRepositoryEmp = new JdbcOrdersRepositoryEmp();
        BaseOrderServiceEmployee orderServiceEmp = new OrderServiceEmployee(ordersRepositoryEmp, customerRepository);

        // to fetch all orders to display an employee, we can use fetchAllOrders() this returns all the placed orders
        // 1. Approved, 2. Pending, 3. Completed, 4. Expired orders.
        System.out.println("-".repeat(100));
        List<OrderInfoBase> allOrders = orderServiceEmp.fetchAllOrders();

        for (OrderInfoBase order : allOrders) {
            System.out.println(order);
        }

        System.out.println("-".repeat(100));

        // to fetch invoice associated to a particular orderId we can use this method :
        try {
            int orderId1 = 75;
            System.out.println("Fetching invoice for order Id " + orderId1 + " ->>>");
            System.out.println(orderServiceEmp.fetchInvoice(orderId1));

            int orderId2 = 99;
            System.out.println("Fetching invoice for order Id " + orderId2 + " ->>>");
            System.out.println(orderServiceEmp.fetchInvoice(orderId2));
        } catch (CannotFetchInvoiceForNonApprovedOrderException eObject) {
            eObject.printStackTrace();
        }

        System.out.println("-".repeat(100));


        // for creating a new quote we use the following method ->
        // createNewQuote(date, products, customerID, GSTNumber, shippingAddress, customerCity, customerMobileNumber,
        // customerEmail, customerPinCode, shippingAgency);

        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "Boxes", 9034.5f));
        products.add(new Product(1, "Boxes", 9034.5f));
        products.add(new Product(1, "Boxes", 9034.5f));
        products.add(new Product(1, "Boxes", 9034.5f));
        LocalDate currentDate = LocalDate.now();
        LocalDate advancedDate = currentDate.plus(3, ChronoUnit.DAYS);

        orderServiceEmp.createNewQuote(advancedDate, products, 1, "GST12345",
                "123 Main Street", "dummy city", "9999999999",
                "dummy@example.com", "12345", "dummy shipping agency");


        OrdersRepositoryCustomer ordersRepositoryCustomer = new JdbcOrdersRepositoryCustomer();
        BaseOrderServiceCustomer orderServiceCustomer = new OrderServiceCustomer(ordersRepositoryCustomer);

        // These are orders waiting for approval made for a particular customer, with customerId one can get these orders.

        List<OrderInfoBase> pendingOrders = orderServiceCustomer.fetchPendingOrders(100);
        for (OrderInfoBase order : pendingOrders) {
            System.out.println(order);
        }
        System.out.println("-".repeat(100));


        // the user can also approve orders which are waiting for approval ->
        // business logic runs on the background :
        // business logic
        // 1. current date is more than 30 days the approval fails and the orders status is set to expired.
        // 2. current date is less than 30 days of the order date the order is approved.
        try {
            // because the order 61 has been there for more than 30 days this will be instantly moved to expired
            orderServiceCustomer.approveOrder(61);
        } catch (OrderApprovalFailureException eObject) {
            eObject.printStackTrace();
        }

        int customerId_3 = 3;
        System.out.println("-".repeat(150));
        System.out.println("approved and completed orders for customerId : " + customerId_3);
        // the user is also displayed Approved and Completed orders
        System.out.println(orderServiceCustomer.fetchApprovedCompletedOrders(customerId_3));
        System.out.println("-".repeat(150));

        int orderId_4 = 3;
        System.out.println("Invoice for orderId : " + orderId_4);

        // for a particular orderId, and if the order is approved or completed the user can also fetch invoice
        try {
            System.out.println(orderServiceCustomer.fetchInvoice(orderId_4));
        } catch (InvoiceFetchingFailedException | CannotFetchInvoiceForNotApprovedNotCompletedOrdersException eObject) {
            eObject.printStackTrace();
        }

    }
}
