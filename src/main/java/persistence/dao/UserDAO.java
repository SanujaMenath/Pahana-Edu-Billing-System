package persistence.dao;

import persistence.model.User;

public interface UserDAO {
    User findByUsername(String username);
}
