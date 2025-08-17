package com.pahanaedu.service;

import dao.UserDAO;
import com.pahanaedu.model.User;
import dao.UserDAOImpl;

import java.util.List;

public class UserService {
    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAOImpl();
    }
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User login(String username, String password) {
        User user = userDAO.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public void addUser(User user) {
        userDAO.save(user);
    }

    public List<User> getAllStaff() {
        return userDAO.findByRole("staff");
    }

    public void deleteUser(int userId) {
        userDAO.deleteUser(userId);
    }
}
