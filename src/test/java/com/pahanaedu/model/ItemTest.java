package com.pahanaedu.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {
    @Test
    public void testGettersAndSetters() {
        Item book = new Item();

        book.setId(1);
        book.setName("Introduction to Algorithms");
        book.setDescription("Comprehensive guide to algorithms, often referred to as CLRS.");
        book.setPrice(89.99);
        book.setQuantity(10);

        assertEquals(1, book.getId());
        assertEquals("Introduction to Algorithms", book.getName());
        assertEquals("Comprehensive guide to algorithms, often referred to as CLRS.", book.getDescription());
        assertEquals(89.99, book.getPrice());
        assertEquals(10, book.getQuantity());
    }

    @Test
    public void testMultipleBooks() {
        Item book1 = new Item();
        book1.setId(2);
        book1.setName("Clean Code");
        book1.setDescription("A Handbook of Agile Software Craftsmanship by Robert C. Martin.");
        book1.setPrice(45.50);
        book1.setQuantity(5);

        Item book2 = new Item();
        book2.setId(3);
        book2.setName("Design Patterns");
        book2.setDescription("Elements of Reusable Object-Oriented Software by the Gang of Four.");
        book2.setPrice(54.99);
        book2.setQuantity(8);

        // Assertions for book1
        assertEquals(2, book1.getId());
        assertEquals("Clean Code", book1.getName());
        assertEquals("A Handbook of Agile Software Craftsmanship by Robert C. Martin.", book1.getDescription());
        assertEquals(45.50, book1.getPrice());
        assertEquals(5, book1.getQuantity());

        // Assertions for book2
        assertEquals(3, book2.getId());
        assertEquals("Design Patterns", book2.getName());
        assertEquals("Elements of Reusable Object-Oriented Software by the Gang of Four.", book2.getDescription());
        assertEquals(54.99, book2.getPrice());
        assertEquals(8, book2.getQuantity());
    }
}
