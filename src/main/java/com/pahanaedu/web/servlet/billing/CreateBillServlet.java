package com.pahanaedu.web.servlet.billing;

import com.pahanaedu.model.Bill;
import com.pahanaedu.model.BillItem;
import com.pahanaedu.model.Customer;
import com.pahanaedu.model.User;
import com.pahanaedu.service.BillService;
import com.pahanaedu.service.CustomerService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/manageBills/create")
public class CreateBillServlet extends HttpServlet {

    private BillService billService = new BillService();
    private CustomerService customerService = new CustomerService();

    // For unit testing
    public void setBillService(BillService billService) {
        this.billService = billService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String phone = req.getParameter("phone");
        String[] itemIds = req.getParameterValues("item_id");
        String[] units = req.getParameterValues("quantity");
        String[] unitPrices = req.getParameterValues("price");

        // --- Fetch customer ---
        Customer customer = customerService.getCustomerByPhone(phone);

        if (customer == null) {
            // Customer not found → redirect back with error
            resp.sendRedirect(req.getContextPath() + "/manageBills/add-bill.jsp?error=Customer+not+found");
            return;
        }

        // --- Create Bill ---
        Bill bill = new Bill();
        bill.setCustomerId(customer.getId());
        bill.setDateCreated(new java.util.Date());

        List<BillItem> billItems = new ArrayList<>();
        double totalAmount = 0.0;

        if (itemIds != null) {
            for (int i = 0; i < itemIds.length; i++) {
                if (itemIds[i] == null || itemIds[i].isEmpty()) continue;
                int itemId = Integer.parseInt(itemIds[i]);
                int quantity = Integer.parseInt(units[i] != null ? units[i] : "0");
                double price = Double.parseDouble(unitPrices[i] != null ? unitPrices[i] : "0");

                BillItem bi = new BillItem();
                bi.setItemId(itemId);
                bi.setQuantity(quantity);
                bi.setPrice(price);
                billItems.add(bi);

                totalAmount += price * quantity;
            }
        }

        bill.setBillItems(billItems);
        bill.setTotalAmount(totalAmount);
        bill.setUnitsConsumed(billItems.stream().mapToInt(BillItem::getQuantity).sum());

        try {
            billService.createBill(bill);

            // --- Determine redirect based on user role ---
            HttpSession session = req.getSession(false);
            String redirectPage = "staff-dashboard.jsp"; // default

            if (session != null) {
                Object userObj = session.getAttribute("user");
                if (userObj instanceof User user) {
                    String role = user.getRole().toString();
                    if ("ADMIN".equalsIgnoreCase(role)) {
                        redirectPage = "admin-dashboard.jsp";
                    } else if ("STAFF".equalsIgnoreCase(role)) {
                        redirectPage = "staff-dashboard.jsp";
                    }
                }
            }

            resp.sendRedirect(req.getContextPath() + "/" + redirectPage + "?message=Bill+created+successfully");

        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().println("Error creating bill: " + e.getMessage());
        }
    }
}
