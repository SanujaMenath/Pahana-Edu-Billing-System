package business.service;

import persistence.dao.CustomerDAO;
import persistence.dao.CustomerDAOImpl;
import persistence.model.Customer;

import java.util.List;

public class CustomerService {
    private final CustomerDAO customerDAO;

    public CustomerService() {
        this.customerDAO = new CustomerDAOImpl();
    }

    // Add a new customer
    public void addCustomer(Customer customer) throws Exception {
        if (customer.getName() == null || customer.getName().isEmpty()) {
            throw new Exception("Customer name cannot be empty.");
        }

        if (customer.getTelephone() == null || customer.getTelephone().isEmpty()) {
            throw new Exception("Phone number is required.");
        }

        if (customer.getUnitsConsumed() < 0) {
            throw new Exception("Units consumed must be 0 or more.");
        }

        customerDAO.addCustomer(customer);
    }

    // Get all customers
    public List<Customer> getAllCustomers() throws Exception {
        return customerDAO.getAllCustomers();
    }

    // Get a customer by account number
    public Customer getCustomerById(int accountNumber) throws Exception {
        return customerDAO.getCustomerByAccountNumber(accountNumber);
    }

    // Update customer info
    public void updateCustomer(Customer customer) throws Exception {
        if (customer.getAccountNumber() <= 0) {
            throw new Exception("Invalid account number.");
        }

        customerDAO.updateCustomer(customer);
    }

    // Delete a customer
    public void deleteCustomer(int accountNumber) throws Exception {
        customerDAO.deleteCustomer(accountNumber);
    }
}
