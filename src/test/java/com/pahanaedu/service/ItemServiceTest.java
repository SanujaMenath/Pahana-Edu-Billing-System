package com.pahanaedu.service;

import com.pahanaedu.model.Item;
import dao.ItemDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ItemServiceTest {

    private ItemDAO itemDAO;
    private ItemService itemService;

    @BeforeEach
    void setUp() {
        itemDAO = mock(ItemDAO.class);
        itemService = new ItemService(itemDAO); //inject mock DAO
    }

    @Test
    void testAddItem_Success() throws Exception {
        Item item = new Item();
        item.setName("Laptop");

        itemService.addItem(item);

        verify(itemDAO, times(1)).addItem(item);
    }

    @Test
    void testGetAllItems() throws Exception {
        Item item1 = new Item();
        item1.setName("Book");

        Item item2 = new Item();
        item2.setName("Pen");

        when(itemDAO.getAllItems()).thenReturn(Arrays.asList(item1, item2));

        List<Item> items = itemService.getAllItems();

        assertEquals(2, items.size());
        assertEquals("Book", items.get(0).getName());
        assertEquals("Pen", items.get(1).getName());
    }

}
