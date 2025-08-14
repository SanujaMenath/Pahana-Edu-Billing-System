package dao;

import com.pahanaedu.model.Bill;
import java.util.List;

public interface BillDAO {
    boolean addBill(Bill bill) throws Exception;
    Bill getBillById(int billId) throws Exception;
    List<Bill> getAllBills() throws Exception;
    boolean deleteBill(int billId) throws Exception;
}
