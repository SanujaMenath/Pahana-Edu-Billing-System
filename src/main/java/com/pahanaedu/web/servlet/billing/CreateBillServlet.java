package com.pahanaedu.web.servlet.billing;

import com.pahanaedu.model.Bill;
import com.pahanaedu.service.BillService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/manageBills/create")
public class CreateBillServlet extends HttpServlet{
    private BillService billService;

    @Override
    public void init() throws ServletException {
        billService = new BillService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int customerId = Integer.parseInt(request.getParameter("customerId"));
            int unitsConsumed = Integer.parseInt(request.getParameter("unitsConsumed"));
            double totalAmount = Double.parseDouble(request.getParameter("totalAmount"));

            Bill bill = new Bill();
            bill.setCustomerId(customerId);
            bill.setUnitsConsumed(unitsConsumed);
            bill.setTotalAmount(totalAmount);

            if (billService.createBill(bill)) {
                response.sendRedirect(request.getContextPath() + "/manageBills/bills.jsp?success=Bill created successfully");
            } else {
                response.sendRedirect(request.getContextPath() + "/manageBills/bills.jsp?error=Failed to create bill");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/manageBills/bills.jsp?error=Invalid data");
        }
    }

}
