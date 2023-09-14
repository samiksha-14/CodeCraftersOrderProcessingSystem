package com.codecrafters;

import com.codecrafters.exception.CannotFetchInvoiceForNonApprovedOrderException;
import com.codecrafters.model.Order;
import com.codecrafters.repository.*;
import com.codecrafters.service.*;

import java.util.Date;

public class Application {
    public static void main(String[] args) {
        CustomerRepository customerRepository = new JdbcCustomerRepository();
        BaseCustomerLoginService customerLoginService = new CustomerLoginService(customerRepository);

        System.out.println(customerLoginService.login(1, 12346));
        System.out.println(customerLoginService.login("Jonah", 2849));


        EmployeeRepository employeeRepository = new JdbcEmployeeRepository();
        BaseEmployeeLoginService employeeLoginService = new EmployeeLoginService(employeeRepository);

//        try {
//            employeeLoginService.logout(1, 123456);
//
////            System.out.println(employeeLoginService.login(1, 123456));
////            System.out.println(employeeLoginService.login(1, 123456));
//
//        } catch (EmployeeAlreadyLoggedException eObject) {
//            eObject.printStackTrace();
//        }

        EmployeeDetailsService employeeDetailsService = new EmployeeDetailsServiceImpl(employeeRepository);
        System.out.println(employeeDetailsService.fetchEmployeeDetails(1));

        OrdersRepositoryEmp ordersRepositoryEmp = new JdbcOrdersRepositoryEmp();
        BaseOrderServiceEmployee orderServiceEmp = new OrderServiceEmployee(ordersRepositoryEmp, customerRepository);
//        System.out.println(orderServiceEmp.fetchInvoice(74));
        System.out.println(orderServiceEmp.fetchAllOrders());

        try {
            System.out.println(orderServiceEmp.fetchInvoice(99));
        } catch (CannotFetchInvoiceForNonApprovedOrderException eObject) {
            eObject.printStackTrace();
        }

//        orderService.createNewQuote(new Date(), 1, "GST12345",
//                "123 Main Street", "dummy city", "9999999999",
//                "dummy@example.com", "12345", "dummy shipping agency",
//                1000.00, 50.0);



        OrdersRepositoryCustomer ordersRepositoryCustomer = new JdbcOrdersRepositoryCustomer();
        BaseOrderServiceCustomer orderServiceCustomer = new OrderServiceCustomer(ordersRepositoryCustomer);
        System.out.println(orderServiceCustomer.fetchPendingOrders(3));

        // approve order ->
        // orderId will be required
        orderServiceCustomer.approveOrder(101);

        System.out.println(orderServiceCustomer.fetchApprovedCompletedOrders(1));
    }
}
