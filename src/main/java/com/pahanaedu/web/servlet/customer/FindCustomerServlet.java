package com.pahanaedu.web.servlet.customer;

import com.pahanaedu.model.Customer;
import com.pahanaedu.service.CustomerService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/findCustomer")


    public class FindCustomerServlet extends HttpServlet {
        private CustomerService customerService = new CustomerService();

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            String phone = req.getParameter("phone");
            resp.setContentType("application/json");

            try {
                Customer customer = customerService.getCustomerByPhone(phone);
                if (customer != null) {
                    resp.getWriter().write(
                            String.format("{\"id\":%d, \"name\":\"%s\", \"address\":\"%s\"}",
                                    customer.getAccountNumber(),
                                    customer.getName(),
                                    customer.getAddress())
                    );
                } else {
                    resp.getWriter().write("{}");
                }
            } catch (Exception e) {
                resp.getWriter().write("{}");
            }
        }


}
