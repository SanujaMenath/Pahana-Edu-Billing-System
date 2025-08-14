<%@ page import="java.util.List" %>
<%@ page import="com.pahanaedu.model.Customer" %>
<%@ page import="com.pahanaedu.model.Item" %>
<%@ page import="com.pahanaedu.service.CustomerService" %>
<%@ page import="com.pahanaedu.service.ItemService" %>

<%
  // Load customer list and item list for dropdowns
  CustomerService customerService = new CustomerService();
  ItemService itemService = new ItemService();

    List<Customer> customers = null;
    try {
        customers = customerService.getAllCustomers();
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    List<Item> items = null;
    try {
        items = itemService.getAllItems();
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Add New Bill</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f8f9fa;
    }
    .container {
      width: 600px;
      margin: 40px auto;
      background: #fff;
      padding: 20px;
      border-radius: 6px;
      box-shadow: 0 2px 8px rgba(0,0,0,0.1);
    }
    h2 {
      text-align: center;
      margin-bottom: 20px;
    }
    label {
      display: block;
      font-weight: bold;
      margin-top: 10px;
    }
    select, input[type="number"], input[type="text"] {
      width: 100%;
      padding: 8px;
      margin-top: 4px;
      margin-bottom: 15px;
    }
    button {
      background-color: #28a745;
      color: white;
      padding: 10px 15px;
      border: none;
      border-radius: 4px;
      cursor: pointer;
    }
    button:hover {
      background-color: #218838;
    }
  </style>
  <script>
    function calculateTotal() {
      let unitPrice = parseFloat(document.getElementById("unitPrice").value) || 0;
      let units = parseInt(document.getElementById("unitsConsumed").value) || 0;
      document.getElementById("totalAmount").value = (unitPrice * units).toFixed(2);
    }

    function updateUnitPrice() {
      let selected = document.getElementById("itemId");
      let price = selected.options[selected.selectedIndex].getAttribute("data-price");
      document.getElementById("unitPrice").value = price;
      calculateTotal();
    }
  </script>
</head>
<body>
<div class="container">
  <h2>Add New Bill</h2>

  <form action="<%=request.getContextPath()%>/manageBills/create" method="post">
    <label for="customerId">Customer</label>
    <select name="customerId" id="customerId" required>
      <option value="">-- Select Customer --</option>
      <% for (Customer c : customers) { %>
      <option value="<%=c.getId()%>">
        <%=c.getName()%>
      </option>
      <% } %>
    </select>

    <label for="itemId">Item</label>
    <select name="itemId" id="itemId" onchange="updateUnitPrice()" required>
      <option value="">-- Select Item --</option>
      <% for (Item i : items) { %>
      <option value="<%=i.getId()%>" data-price="<%=i.getPrice()%>">
        <%=i.getName()%> (Rs. <%=i.getPrice()%> per unit)
      </option>
      <% } %>
    </select>

    <label for="unitPrice">Unit Price</label>
    <input type="text" id="unitPrice" name="unitPrice" readonly>

    <label for="unitsConsumed">Units Consumed</label>
    <input type="number" id="unitsConsumed" name="unitsConsumed" min="1" onchange="calculateTotal()" required>

    <label for="totalAmount">Total Amount</label>
    <input type="text" id="totalAmount" name="totalAmount" readonly>

    <button type="submit">Save Bill</button>
  </form>
</div>
</body>
</html>
