package dao;

import com.pahanaedu.model.Bill;
import com.pahanaedu.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillDAOImpl implements BillDAO {
    @Override
    public boolean addBill(Bill bill) throws Exception {
        String sql = "INSERT INTO bills (customer_id, units_consumed, total_amount) VALUES (?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, bill.getCustomerId());
            stmt.setInt(2, bill.getUnitsConsumed());
            stmt.setDouble(3, bill.getTotalAmount());
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    bill.setId(rs.getInt(1));
                }
                return true;
            }
        }
        return false;
    }


    @Override
    public Bill getBillById(int billId) throws Exception {
        String sql = "SELECT * FROM bills WHERE id = ?";
        Bill bill = null;
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, billId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setCustomerId(rs.getInt("customer_id"));
                bill.setUnitsConsumed(rs.getInt("units_consumed"));
                bill.setTotalAmount(rs.getDouble("total_amount"));
                bill.setDateCreated(rs.getTimestamp("date_created"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bill;
    }

    @Override
    public List<Bill> getAllBills() throws Exception {
        List<Bill> bills = new ArrayList<>();
        String sql = "SELECT * FROM bills ORDER BY date_created DESC";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Bill bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setCustomerId(rs.getInt("customer_id"));
                bill.setUnitsConsumed(rs.getInt("units_consumed"));
                bill.setTotalAmount(rs.getDouble("total_amount"));
                bill.setDateCreated(rs.getTimestamp("date_created"));
                bills.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bills;
    }

    @Override
    public boolean deleteBill(int billId) throws Exception {
        String sql = "DELETE FROM bills WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, billId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
