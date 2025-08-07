package persistence.dao;

import persistence.model.Customer;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    private Connection getConnection() throws Exception {
        return DBUtil.getConnection();
    }

    @Override
    public void addCustomer(Customer customer) throws Exception {
        String sql = "INSERT INTO customers (name, address, phone, units_consumed) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getAddress());
            stmt.setString(3, customer.getTelephone());
            stmt.setInt(4, customer.getUnitsConsumed());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating customer failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    customer.setAccountNumber(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating customer failed, no ID obtained.");
                }
            }
        }
    }

    @Override
    public void updateCustomer(Customer customer) throws Exception {
        String sql = "UPDATE customers SET name=?, address=?, phone=?, units_consumed=? WHERE account_number=?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getAddress());
            stmt.setString(3, customer.getTelephone());
            stmt.setInt(4, customer.getUnitsConsumed());
            stmt.setInt(5, customer.getAccountNumber());

            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteCustomer(int accountNumber) throws Exception {
        String sql = "DELETE FROM customers WHERE account_number=?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, accountNumber);
            stmt.executeUpdate();
        }
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
                    customer.setTelephone(rs.getString("phone"));
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
                customer.setTelephone(rs.getString("phone"));
                customer.setUnitsConsumed(rs.getInt("units_consumed"));
                customers.add(customer);
            }
        }
        return customers;
    }
}
