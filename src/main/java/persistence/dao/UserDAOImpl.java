package persistence.dao;

import persistence.model.User;
import util.DBUtil;

import java.sql.*;

public class UserDAOImpl implements UserDAO {

    @Override
    public User findByUsername(String username) {
        User user = null;

        try {
            Connection conn = DBUtil.getConnection(); // You must have this helper class
            String sql = "SELECT * FROM users WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(User.Role.valueOf(rs.getString("role"))); // ✅ Role must match enum
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
}
