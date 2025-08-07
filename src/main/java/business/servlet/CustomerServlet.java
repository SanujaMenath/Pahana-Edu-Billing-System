package business.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.*;
import persistence.dao.CustomerDAO;
import persistence.dao.CustomerDAOImpl;
import persistence.model.Customer;

import java.io.*;

public class CustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        Customer customer = new Customer(name, phone, address);
        CustomerDAO dao = new CustomerDAOImpl();

        try {
            dao.addCustomer(customer);
            response.sendRedirect("dashboard.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error saving customer.");
        }
    }
}
