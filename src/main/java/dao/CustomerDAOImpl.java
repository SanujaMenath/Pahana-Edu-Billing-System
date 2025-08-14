package dao;

import com.pahanaedu.model.Customer;
import com.pahanaedu.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    private Connection getConnection() throws Exception {
        return DBUtil.getConnection();
    }

    private int generateAccountNumber() throws Exception {
        String sql = "SELECT MAX(account_number) FROM customers";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                int maxAccountNumber = rs.getInt(1);
                return maxAccountNumber + 1;
            } else {
                return 1000;
            }
        }
    }


    @Override
    public void addCustomer(Customer customer) throws Exception {
        // Generate the account number
        int newAccountNumber = generateAccountNumber();
        customer.setAccountNumber(newAccountNumber);

        String sql = "INSERT INTO customers (account_number, name, address, phone, units_consumed) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customer.getAccountNumber());
            stmt.setString(2, customer.getName());
            stmt.setString(3, customer.getAddress());
            stmt.setString(4, customer.getPhone());
            stmt.setInt(5, customer.getUnitsConsumed());

            stmt.executeUpdate();
        }
    }


    @Override
    public void updateCustomer(Customer customer) throws Exception {
        String sql = "UPDATE customers SET name=?, address=?, phone=?, units_consumed=? WHERE account_number=?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getAddress());
            stmt.setString(3, customer.getPhone());
            stmt.setInt(4, customer.getUnitsConsumed());
            stmt.setInt(5, customer.getAccountNumber());

            stmt.executeUpdate();
        }
    }

    @Override
    public boolean deleteCustomer(int accountNumber) {
        String sql = "DELETE FROM customers WHERE account_number = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, accountNumber);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public Customer getCustomerByAccountNumber(int accountNumber) throws Exception {
        String sql = "SELECT * FROM customers WHERE account_number=?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, accountNumber);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Customer customer = new Customer();
                    customer.setAccountNumber(rs.getInt("account_number"));
                    customer.setName(rs.getString("name"));
                    customer.setAddress(rs.getString("address"));
                    customer.setPhone(rs.getString("phone"));
                    customer.setUnitsConsumed(rs.getInt("units_consumed"));
                    return customer;
                }
                return null;
            }
        }
    }

    @Override
    public List<Customer> getAllCustomers() throws Exception {
        String sql = "SELECT * FROM customers";
        List<Customer> customers = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setAccountNumber(rs.getInt("account_number"));
                customer.setName(rs.getString("name"));
                customer.setAddress(rs.getString("address"));
                customer.setPhone(rs.getString("phone"));
                customer.setUnitsConsumed(rs.getInt("units_consumed"));
                customers.add(customer);
            }
        }
        return customers;
    }
}
