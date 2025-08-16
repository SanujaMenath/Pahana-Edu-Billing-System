package com.pahanaedu.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BillItemTest {
    @Test
    public void testBillItemGettersAndSetters() {
        BillItem billItem = new BillItem();

        // Set values
        billItem.setId(1);
        billItem.setBillId(101);
        billItem.setItemId(202);
        billItem.setQuantity(5);
        billItem.setPrice(150.75);

        // Assert values
        assertEquals(1, billItem.getId());
        assertEquals(101, billItem.getBillId());
        assertEquals(202, billItem.getItemId());
        assertEquals(5, billItem.getQuantity());
        assertEquals(150.75, billItem.getPrice());
    }
}
