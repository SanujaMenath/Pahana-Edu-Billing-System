package com.pahanaedu.web.servlet.billing;

import com.pahanaedu.model.Bill;
import com.pahanaedu.model.Customer;
import com.pahanaedu.service.BillService;
import com.pahanaedu.service.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateBillServletTest {

    private CreateBillServlet servlet;
    private BillService billService;
    private CustomerService customerService;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeEach
    void setUp() {
        servlet = new CreateBillServlet();

        // Mocks
        billService = mock(BillService.class);
        customerService = mock(CustomerService.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        // Inject mocks
        servlet.setBillService(billService);
        servlet.setCustomerService(customerService);
    }

    @Test
    void testCreateBill_Success() throws Exception {
        // Arrange
        when(request.getParameter("phone")).thenReturn("0771234567");
        when(request.getParameterValues("item_id")).thenReturn(new String[]{"1", "2"});
        when(request.getParameterValues("quantity")).thenReturn(new String[]{"2", "3"});
        when(request.getParameterValues("price")).thenReturn(new String[]{"100.0", "200.0"});
        when(request.getContextPath()).thenReturn("");

        Customer customer = new Customer();
        customer.setId(10);
        when(customerService.getCustomerByPhone("0771234567")).thenReturn(customer);

        // Act
        servlet.doPost(request, response);

        // Assert
        verify(billService, times(1)).createBill(any(Bill.class));
        verify(response, times(1)).sendRedirect("/staff-dashboard.jsp?message=Bill created successfully");
    }

    @Test
    void testCreateBill_Error() throws Exception {
        // Arrange
        when(request.getParameter("phone")).thenReturn("0779999999");
        when(request.getParameterValues("item_id")).thenReturn(new String[]{"1"});
        when(request.getParameterValues("quantity")).thenReturn(new String[]{"1"});
        when(request.getParameterValues("price")).thenReturn(new String[]{"50.0"});
        when(request.getContextPath()).thenReturn("");

        Customer customer = new Customer();
        customer.setId(5);
        when(customerService.getCustomerByPhone("0779999999")).thenReturn(customer);

        // Simulate exception
        doThrow(new RuntimeException("DB Error")).when(billService).createBill(any(Bill.class));

        PrintWriter writer = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(writer);

        // Act
        servlet.doPost(request, response);

        // Assert
        verify(writer).println("Error creating bill: DB Error");
    }
}
