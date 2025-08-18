package com.pahanaedu.web.servlet.item;

import dao.ItemDAO;
import dao.ItemDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/manageItems/delete-item")
public class DeleteItemServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int itemId = Integer.parseInt(request.getParameter("id"));

            ItemDAO dao = new ItemDAOImpl();
            boolean deleted = dao.deleteItem(itemId);

            if (deleted) {
                response.sendRedirect(request.getContextPath() + "/manageItems/manage-items.jsp?success=Item deleted successfully");
            } else {
                response.sendRedirect(request.getContextPath() + "/manageItems/manage-items.jsp?error=Unable to delete item");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}
