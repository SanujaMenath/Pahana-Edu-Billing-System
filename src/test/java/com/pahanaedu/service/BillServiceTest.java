package com.pahanaedu.service;

import com.pahanaedu.model.Bill;
import dao.BillDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BillServiceTest {

    private BillDAO billDAOMock;
    private BillService billService;

    @BeforeEach
    void setUp() {
        billDAOMock = Mockito.mock(BillDAO.class);
        billService = new BillService(billDAOMock);
    }

    @Test
    void createBill_validBill_shouldReturnTrue() throws Exception {
        Bill bill = new Bill();
        when(billDAOMock.addBill(bill)).thenReturn(true);

        boolean result = billService.createBill(bill);

        assertTrue(result);
        verify(billDAOMock, times(1)).addBill(bill);
    }

    @Test
    void createBill_invalidBill_shouldReturnFalse() throws Exception {
        Bill bill = new Bill();
        when(billDAOMock.addBill(bill)).thenReturn(false);

        boolean result = billService.createBill(bill);

        assertFalse(result);
        verify(billDAOMock, times(1)).addBill(bill);
    }

    @Test
    void listBills_shouldReturnListFromDAO() throws Exception {
        Bill b1 = new Bill();
        Bill b2 = new Bill();

        when(billDAOMock.getAllBills()).thenReturn(Arrays.asList(b1, b2));

        List<Bill> bills = billService.listBills();

        assertEquals(2, bills.size());
        verify(billDAOMock, times(1)).getAllBills();
    }

    @Test
    void listBills_emptyList_shouldReturnEmpty() throws Exception {
        when(billDAOMock.getAllBills()).thenReturn(Arrays.asList());

        List<Bill> bills = billService.listBills();

        assertTrue(bills.isEmpty());
        verify(billDAOMock, times(1)).getAllBills();
    }
}
