package com.pahanaedu.web.servlet.customer;

import com.pahanaedu.model.Customer;
import com.pahanaedu.service.CustomerService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AddCustomerServletTest {

    private AddCustomerServlet servlet;
    private CustomerService customerService;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher dispatcher;

    @BeforeEach
    void setUp() {
        customerService = mock(CustomerService.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);

        servlet = new AddCustomerServlet(customerService); // inject mock directly
    }


    @Test
    void testAddCustomer_Success() throws Exception {
        when(request.getParameter("name")).thenReturn("John Doe");
        when(request.getParameter("phone")).thenReturn("1234567890");
        when(request.getParameter("address")).thenReturn("123 Main St");
        when(request.getContextPath()).thenReturn("/app");

        servlet.doPost(request, response);

        verify(customerService).addCustomer(any(Customer.class));
        verify(response).sendRedirect("/app/manageCustomers/manage-customers.jsp");
    }

    @Test
    void testAddCustomer_Failure() throws Exception {
        // Arrange
        when(request.getParameter("name")).thenReturn("Jane Doe");
        when(request.getParameter("phone")).thenReturn("0987654321");
        when(request.getParameter("address")).thenReturn("456 Elm St");
        when(request.getRequestDispatcher("error.jsp")).thenReturn(dispatcher);

        doThrow(new RuntimeException("Database error"))
                .when(customerService).addCustomer(any(Customer.class));

        servlet.doPost(request, response);

        verify(request).setAttribute(eq("error"), eq("Database error"));
        verify(dispatcher).forward(request, response);
    }
}
