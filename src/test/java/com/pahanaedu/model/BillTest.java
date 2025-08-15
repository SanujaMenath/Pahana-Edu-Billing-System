package com.pahanaedu.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
public class BillTest {
    @Test
    public void testSettersAndGetters() {
        Bill bill = new Bill();

        int id = 101;
        int customerId = 5;
        Date date = new Date();
        int unitsConsumed = 150;
        double totalAmount = 2500.75;

        BillItem item1 = new BillItem();
        item1.setId(1);
        item1.setItemId(1001);
        item1.setQuantity(10);
        item1.setPrice(50.0);

        BillItem item2 = new BillItem();
        item2.setId(2);
        item2.setItemId(1002);
        item2.setQuantity(5);
        item2.setPrice(100.0);

        List<BillItem> billItems = Arrays.asList(item1, item2);

        // Set values
        bill.setId(id);
        bill.setCustomerId(customerId);
        bill.setDateCreated(date);
        bill.setUnitsConsumed(unitsConsumed);
        bill.setTotalAmount(totalAmount);
        bill.setBillItems(billItems);

        // Verify values
        assertEquals(id, bill.getId());
        assertEquals(customerId, bill.getCustomerId());
        assertEquals(date, bill.getDateCreated());
        assertEquals(unitsConsumed, bill.getUnitsConsumed());
        assertEquals(totalAmount, bill.getTotalAmount());
        assertEquals(billItems, bill.getBillItems());
    }

    @Test
    public void testEmptyBillItemsList() {
        Bill bill = new Bill();
        assertNull(bill.getBillItems(), "Bill items list should be null by default");

        bill.setBillItems(Arrays.asList());
        assertTrue(bill.getBillItems().isEmpty(), "Bill items list should be empty");
    }
}
