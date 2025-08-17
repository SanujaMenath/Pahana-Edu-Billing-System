package com.pahanaedu.web.servlet.staff;

import com.pahanaedu.model.User;
import com.pahanaedu.service.UserService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/manageStaff/add-staff")
public class AddStaffServlet extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User staff = new User();
        staff.setUsername(username);
        staff.setPassword(password);
        staff.setRole(User.Role.staff);


        try {
            userService.addUser(staff);
            response.sendRedirect(request.getContextPath() + "/manageStaff/manage-staff.jsp?success=Staff added successfully");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/manageStaff/add-staff.jsp?error=Failed to add staff");
        }
    }
}
