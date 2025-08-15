package dao;

import com.pahanaedu.model.Item;
import com.pahanaedu.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    private Connection getConnection() throws Exception {
        return DBUtil.getConnection();
    }

    @Override
    public void addItem(Item item) throws Exception {
        String sql = "INSERT INTO items (name, description, price, quantity) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, item.getName());
            stmt.setString(2, item.getDescription());
            stmt.setDouble(3, item.getPrice());
            stmt.setInt(4, item.getQuantity());
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Item> getAllItems() throws Exception {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM items";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setDescription(rs.getString("description"));
                item.setPrice(rs.getDouble("price"));
                item.setQuantity(rs.getInt("quantity"));
                items.add(item);
            }
        }
        return items;
    }

    @Override
    public Item getItemById(int id) throws Exception {
        String sql = "SELECT * FROM items WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Item item = new Item();
                    item.setId(rs.getInt("id"));
                    item.setName(rs.getString("name"));
                    item.setDescription(rs.getString("description"));
                    item.setPrice(rs.getDouble("price"));
                    item.setQuantity(rs.getInt("quantity"));
                    return item;
                }
            }
        }
        return null;
    }

    @Override
    public void updateItem(Item item) throws Exception {
        String sql = "UPDATE items SET name=?, description=?, price=?, quantity=? WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, item.getName());
            stmt.setString(2, item.getDescription());
            stmt.setDouble(3, item.getPrice());
            stmt.setInt(4, item.getQuantity());
            stmt.setInt(5, item.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteItem(int id) throws Exception {
        String sql = "DELETE FROM items WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
