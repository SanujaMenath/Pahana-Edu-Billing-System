package com.pahanaedu.web.servlet.item;

import com.pahanaedu.model.Item;
import com.pahanaedu.service.ItemService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AddItemServletTest {

    private AddItemServlet servlet;
    private ItemService itemService;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeEach
    void setUp() {
        itemService = mock(ItemService.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        servlet = new AddItemServlet();
        servlet.setItemService(itemService); // inject mock
    }

    @Test
    void testAddItem_Success() throws Exception {
        when(request.getParameter("name")).thenReturn("Math Book");
        when(request.getParameter("description")).thenReturn("Algebra");
        when(request.getParameter("price")).thenReturn("500");
        when(request.getParameter("quantity")).thenReturn("10");
        when(request.getContextPath()).thenReturn("/app");

        servlet.doPost(request, response);

        verify(itemService).addItem(any(Item.class));
        verify(response).sendRedirect("/app/manageItems/add-items.jsp?success=Item added successfully");
    }

    @Test
    void testAddItem_Failure() throws Exception {
        when(request.getParameter("name")).thenReturn("Physics Book");
        when(request.getParameter("description")).thenReturn("Mechanics");
        when(request.getParameter("price")).thenReturn("700");
        when(request.getParameter("quantity")).thenReturn("5");
        when(request.getContextPath()).thenReturn("/app");

        doThrow(new Exception("DB error")).when(itemService).addItem(any(Item.class));

        servlet.doPost(request, response);

        verify(response).sendRedirect("/app/manageItems/add-items.jsp?error=Failed to add item");
    }
}