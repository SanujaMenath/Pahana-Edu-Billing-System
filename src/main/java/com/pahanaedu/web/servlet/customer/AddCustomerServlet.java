package com.pahanaedu.web.servlet.customer;

import com.pahanaedu.service.CustomerService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.*;
import com.pahanaedu.model.Customer;

import java.io.*;

@WebServlet("/manageCustomers/add-customer")
public class AddCustomerServlet extends HttpServlet {
    private CustomerService customerService;

    // default constructor for production
    public AddCustomerServlet() {
        this.customerService = new CustomerService();
    }

    // constructor for testing
    public AddCustomerServlet(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        Customer customer = new Customer();
        customer.setName(name);
        customer.setPhone(phone);
        customer.setAddress(address);

        try {
            customerService.addCustomer(customer);
            response.sendRedirect(request.getContextPath() + "/manageCustomers/manage-customers.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
