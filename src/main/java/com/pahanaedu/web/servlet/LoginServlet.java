package com.pahanaedu.web.servlet;

import com.pahanaedu.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.*;
import dao.UserDAOImpl;
import com.pahanaedu.model.User;

import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() {
        userService = new UserService(new UserDAOImpl());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = userService.login(username, password);
        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            String role = user.getRole().toString().toUpperCase();

            switch (role) {
                case "ADMIN":
                    resp.sendRedirect(req.getContextPath() + "/admin-dashboard.jsp");
                    break;
                case "STAFF":
                    resp.sendRedirect(req.getContextPath() + "/staff-dashboard.jsp");
                    break;
                default:
                    resp.sendRedirect(req.getContextPath() + "/unauthorized.jsp");
                    break;
            }
        } else {
            req.setAttribute("error", "Invalid username or password");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

}
