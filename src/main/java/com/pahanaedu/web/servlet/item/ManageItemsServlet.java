package com.pahanaedu.web.servlet.item;

import com.pahanaedu.service.ItemService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.pahanaedu.model.Item;

import java.io.IOException;
import java.util.List;

@WebServlet("/manageItems/manage-items")
public class ManageItemsServlet extends HttpServlet {
    private ItemService itemService = new ItemService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Item> items = itemService.getAllItems();
            request.setAttribute("items", items);
            request.getRequestDispatcher("/manageItems/manage-items.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}
