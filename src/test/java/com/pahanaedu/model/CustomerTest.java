package com.pahanaedu.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {
    @Test
    public void testGettersAndSetters() {
        Customer customer = new Customer();

        // Set values
        customer.setId(1);
        customer.setAccountNumber(12345);
        customer.setName("Siril Perera");
        customer.setAddress("1 Main Street");
        customer.setPhone("0771234567");
        customer.setUnitsConsumed(250);

        // Assert values
        assertEquals(1, customer.getId());
        assertEquals(12345, customer.getAccountNumber());
        assertEquals("Siril Perera", customer.getName());
        assertEquals("1 Main Street", customer.getAddress());
        assertEquals("0771234567", customer.getPhone());
        assertEquals(250, customer.getUnitsConsumed());
    }

    @Test
    public void testParameterizedConstructor() {
        Customer customer = new Customer(2, "Saman Kumara", "0779876543", "1st cross Street");

        assertEquals(2, customer.getId());
        assertEquals("Saman Kumara", customer.getName());
        assertEquals("0779876543", customer.getPhone());
        assertEquals("1st cross Street", customer.getAddress());

        // Fields not set in constructor should have default values
        assertEquals(0, customer.getAccountNumber());
        assertEquals(0, customer.getUnitsConsumed());
    }
}
