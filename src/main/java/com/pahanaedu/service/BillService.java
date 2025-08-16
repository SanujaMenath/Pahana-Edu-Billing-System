package com.pahanaedu.service;

import com.pahanaedu.model.Bill;
import dao.BillDAO;
import dao.BillDAOImpl;

import java.util.List;

public class BillService {
    private BillDAO billDAO = new BillDAOImpl();

    public BillService() {
        this.billDAO = new BillDAOImpl();
    }

    // Constructor injection (testing)
    public BillService(BillDAO billDAO) {
        this.billDAO = billDAO;
    }

    public boolean createBill(Bill bill) throws Exception {
        return billDAO.addBill(bill);
    }


    public Bill getBill(int billId) throws Exception {
        return billDAO.getBillById(billId);
    }


    public List<Bill> listBills() throws Exception {
        return billDAO.getAllBills();
    }


    public boolean removeBill(int billId) throws Exception {
        return billDAO.deleteBill(billId);
    }

}
