package com.pahanaedu.service;

import dao.CustomerDAO;
import dao.CustomerDAOImpl;
import com.pahanaedu.model.Customer;

import java.util.List;

public class CustomerService {
    private final CustomerDAO customerDAO;

    public CustomerService() {
        this.customerDAO = new CustomerDAOImpl();
    }

    // constructor for testing
    public CustomerService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public void addCustomer(Customer customer) throws Exception {
        if (customer.getName() == null || customer.getName().isEmpty()) {
            throw new Exception("Customer name cannot be empty.");
        }

        if (customer.getPhone() == null || customer.getPhone().isEmpty()) {
            throw new Exception("Phone number is required.");
        }

        if (customer.getUnitsConsumed() < 0) {
            throw new Exception("Units consumed must be 0 or more.");
        }

        customerDAO.addCustomer(customer);
    }

    public List<Customer> getAllCustomers() throws Exception {
        return customerDAO.getAllCustomers();
    }

    public Customer getCustomerById(int accountNumber) throws Exception {
        return customerDAO.getCustomerByAccountNumber(accountNumber);
    }

    public void updateCustomer(Customer customer) throws Exception {
        if (customer.getAccountNumber() <= 0) {
            throw new Exception("Invalid account number.");
        }

        customerDAO.updateCustomer(customer);
    }

    public void deleteCustomer(int accountNumber) throws Exception {
        customerDAO.deleteCustomer(accountNumber);
    }

    public Customer getCustomerByPhone(String phone) {
        try {
            return customerDAO.getCustomerByPhone(phone);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
