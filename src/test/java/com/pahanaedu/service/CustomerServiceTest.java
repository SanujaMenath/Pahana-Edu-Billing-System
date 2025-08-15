package com.pahanaedu.service;

import com.pahanaedu.model.Customer;
import dao.CustomerDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    private CustomerDAO customerDAOMock;
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        // Create mock DAO
        customerDAOMock = Mockito.mock(CustomerDAO.class);

        // Inject mock DAO into service
        customerService = new CustomerService(customerDAOMock);
    }

    @Test
    void addCustomer_validCustomer_shouldCallDAO() throws Exception {
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setPhone("0771234567");
        customer.setUnitsConsumed(5);

        customerService.addCustomer(customer);

        verify(customerDAOMock, times(1)).addCustomer(customer);
    }

    @Test
    void addCustomer_missingName_shouldThrowException() {
        Customer customer = new Customer();
        customer.setPhone("0771234567");
        customer.setUnitsConsumed(5);

        Exception exception = assertThrows(Exception.class, () -> customerService.addCustomer(customer));
        assertEquals("Customer name cannot be empty.", exception.getMessage());
    }

    @Test
    void getAllCustomers_shouldReturnListFromDAO() throws Exception {
        Customer c1 = new Customer();
        Customer c2 = new Customer();
        when(customerDAOMock.getAllCustomers()).thenReturn(Arrays.asList(c1, c2));

        List<Customer> customers = customerService.getAllCustomers();

        assertEquals(2, customers.size());
        verify(customerDAOMock, times(1)).getAllCustomers();
    }

    @Test
    void getCustomerByPhone_existingPhone_shouldReturnCustomer() throws Exception {
        Customer c = new Customer();
        c.setPhone("0771234567");

        when(customerDAOMock.getCustomerByPhone("0771234567")).thenReturn(c);

        Customer result = customerService.getCustomerByPhone("0771234567");
        assertNotNull(result);
        assertEquals("0771234567", result.getPhone());
    }

    @Test
    void updateCustomer_invalidAccountNumber_shouldThrowException() {
        Customer c = new Customer();
        c.setAccountNumber(0);

        Exception exception = assertThrows(Exception.class, () -> customerService.updateCustomer(c));
        assertEquals("Invalid account number.", exception.getMessage());
    }
}
