package business.service;
import persistence.dao.UserDAO;
import persistence.dao.UserDAOImpl;
import persistence.model.User;

public class AuthService {
    private UserDAO userDAO;

    public AuthService() {
        this.userDAO = new UserDAOImpl(); // ✅ This is fine
    }

    public User login(String username, String password) {
        User user = userDAO.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
