package com.pahanaedu.service;

import dao.ItemDAO;
import dao.ItemDAOImpl;
import com.pahanaedu.model.Item;

import java.util.List;

public class ItemService {
    ItemDAO itemDAO = new ItemDAOImpl();

    // default constructor for production
    public ItemService() {
        this.itemDAO = new ItemDAOImpl();
    }

    // constructor for testing
    public ItemService(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }
    public void addItem(Item item) throws Exception {
        if (item.getName() == null || item.getName().isEmpty()) {
            throw new Exception("Customer name cannot be empty.");
        }
        itemDAO.addItem(item);
    }

    public List<Item> getAllItems() throws Exception {
        return itemDAO.getAllItems();
    }

}
