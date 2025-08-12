package com.pahanaedu.web.servlet.customer;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.CustomerDAO;
import dao.CustomerDAOImpl;

import java.io.IOException;

@WebServlet("/manageCustomers/delete-customer")
public class DeleteCustomerServlet extends HttpServlet {

    private CustomerDAO customerDAO;

    @Override
    public void init() throws ServletException {
        customerDAO = new CustomerDAOImpl(); // Instantiate DAO
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accountNumberStr = request.getParameter("accountNumber");

        if (accountNumberStr != null) {
            int accountNumber = Integer.parseInt(accountNumberStr);

            boolean deleted = false;
            try {
                deleted = customerDAO.deleteCustomer(accountNumber);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            if (deleted) {
                response.sendRedirect(request.getContextPath() + "/manageCustomers/manage-customers.jsp?success=Customer deleted successfully");
            } else {
                response.sendRedirect(request.getContextPath() + "/manageCustomers/manage-customers.jsp?error=Failed to delete customer");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/manageCustomers/manage-customers.jsp?error=Invalid customer ID");
        }
    }
}

