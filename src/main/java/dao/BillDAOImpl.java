package dao;

import com.pahanaedu.model.Bill;
import com.pahanaedu.model.BillItem;
import com.pahanaedu.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillDAOImpl implements BillDAO {
    @Override
    public boolean addBill(Bill bill) throws Exception {
        String billSql = "INSERT INTO bills (customer_id, date_created, units_consumed, total_amount ) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement billStmt = conn.prepareStatement(billSql, Statement.RETURN_GENERATED_KEYS)) {

            // Insert the bill
            billStmt.setInt(1, bill.getCustomerId());
            billStmt.setTimestamp(2, new Timestamp(bill.getDateCreated().getTime()));
            billStmt.setInt(3, bill.getUnitsConsumed());
            billStmt.setDouble(4, bill.getTotalAmount());


            int rowsInserted = billStmt.executeUpdate();
            if (rowsInserted == 0) {
                return false;
            }

            // Get generated bill ID
            try (ResultSet rs = billStmt.getGeneratedKeys()) {
                if (rs.next()) {
                    bill.setId(rs.getInt(1));
                } else {
                    throw new SQLException("Failed to retrieve bill ID.");
                }
            }

            // Insert bill items
            String itemSql = "INSERT INTO bill_items (bill_id, item_id, quantity, price) VALUES (?, ?, ?, ?)";
            try (PreparedStatement itemStmt = conn.prepareStatement(itemSql)) {
                for (BillItem bi : bill.getBillItems()) {
                    itemStmt.setInt(1, bill.getId());
                    itemStmt.setInt(2, bi.getItemId());
                    itemStmt.setInt(3, bi.getQuantity());
                    itemStmt.setDouble(4, bi.getPrice());
                    itemStmt.addBatch();
                }
                itemStmt.executeBatch();
            }

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error adding bill: " + e.getMessage());
        }
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
