package com.pahanaedu.web.servlet.staff;

import com.pahanaedu.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/manageStaff/delete-staff")
public class ManageStaffServlet extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int staffId = Integer.parseInt(request.getParameter("id"));

        try {
            userService.deleteUser(staffId);
            response.sendRedirect(request.getContextPath() + "/manageStaff/manage-staff.jsp?success=Staff deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/manageStaff/manage-staff.jsp?error=Failed to delete staff");
        }
    }
}
