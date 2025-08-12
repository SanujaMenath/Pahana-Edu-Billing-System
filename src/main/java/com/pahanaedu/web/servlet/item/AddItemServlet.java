package com.pahanaedu.web.servlet.item;

import com.pahanaedu.service.ItemService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.pahanaedu.model.Item;

import java.io.*;

@WebServlet("/manageItems/add-item")
public class AddItemServlet extends HttpServlet {
    private ItemService itemService = new ItemService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        Item item = new Item();
        item.setName(name);
        item.setDescription(description);
        item.setPrice(price);
        item.setQuantity(quantity);
        try {


            itemService.addItem(item);
            response.sendRedirect(request.getContextPath() + "/manageItems/add-items.jsp?success=Item added successfully");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/manageItems/add-items.jsp?error=Failed to add item");
        }
    }
}
