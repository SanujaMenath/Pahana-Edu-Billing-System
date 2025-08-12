package com.pahanaedu.web.servlet.customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.CustomerDAO;
import dao.CustomerDAOImpl;
import com.pahanaedu.model.Customer;

import java.io.IOException;

@WebServlet("/manageCustomers/update-customer")
public class UpdateCustomerServlet extends HttpServlet {
    private CustomerDAO customerDAO = new CustomerDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Customer customer = new Customer();
            customer.setAccountNumber(Integer.parseInt(request.getParameter("accountNumber")));
            customer.setName(request.getParameter("name"));
            customer.setAddress(request.getParameter("address"));
            customer.setTelephone(request.getParameter("telephone"));
            customer.setUnitsConsumed(Integer.parseInt(request.getParameter("unitsConsumed")));

            customerDAO.updateCustomer(customer);
            response.sendRedirect(request.getContextPath() + "/manageCustomers/manage-customers.jsp?success=Customer updated successfully");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/manage-customers.jsp?error=Update failed");
        }
    }
}

