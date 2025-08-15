package dao;

import com.pahanaedu.model.User;

public interface UserDAO {
    User findByUsername(String username);
}
