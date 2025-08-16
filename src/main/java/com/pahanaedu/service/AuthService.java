package com.pahanaedu.service;
import dao.UserDAO;
import dao.UserDAOImpl;
import com.pahanaedu.model.User;

public class AuthService {
    private UserDAO userDAO;

    public AuthService() {
        this.userDAO = new UserDAOImpl();
    }

    // Constructor injection for testing
    public AuthService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User login(String username, String password) {
        User user = userDAO.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
