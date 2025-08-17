package dao;

import com.pahanaedu.model.User;

import java.util.List;

public interface UserDAO {
    User findByUsername(String username);
    void save(User user);
    List findByRole(String role);
    void deleteUser(int userId);

}
