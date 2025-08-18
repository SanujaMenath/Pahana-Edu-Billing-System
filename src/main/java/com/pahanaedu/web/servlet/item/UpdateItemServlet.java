package com.pahanaedu.web.servlet.item;

import com.pahanaedu.model.Item;
import dao.ItemDAO;
import dao.ItemDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
@WebServlet("/manageItems/update-item")
public class UpdateItemServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Read form parameters
            int itemId = Integer.parseInt(request.getParameter("itemId"));
            String itemName = request.getParameter("itemName");
            String description = request.getParameter("description");
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            // Create Item object
            Item item = new Item();
            item.setId(itemId);
            item.setName(itemName);
            item.setDescription(description);
            item.setPrice(price);
            item.setQuantity(quantity);

            // DAO call
            ItemDAO dao = new ItemDAOImpl();
            boolean updated = dao.updateItem(item);

            if (updated) {
                // redirect using context path
                response.sendRedirect(request.getContextPath() + "/manageItems/manage-items.jsp?success=Item updated successfully");
            } else {
                response.sendRedirect(request.getContextPath() + "/manageItems/edit-item.jsp?itemId=" + itemId + "&error=1");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}

