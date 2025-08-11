package persistence.dao;

import persistence.model.Customer;

import java.util.List;

public interface CustomerDAO {
    void addCustomer(Customer customer) throws Exception;
    void updateCustomer(Customer customer) throws Exception;
    boolean deleteCustomer(int accountNumber) throws Exception;
    Customer getCustomerByAccountNumber(int accountNumber) throws Exception;
    List<Customer> getAllCustomers() throws Exception;


}
