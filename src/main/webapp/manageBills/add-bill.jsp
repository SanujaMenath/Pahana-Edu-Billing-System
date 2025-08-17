<%@ page import="java.util.List" %>
<%@ page import="com.pahanaedu.model.Item" %>
<%@ page import="com.pahanaedu.service.ItemService" %>
<%@ page import="com.pahanaedu.model.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/unauthorized.jsp");
        return;
    }

    String dashboardPage = "staff-dashboard.jsp";
    if ("ADMIN".equalsIgnoreCase(user.getRole().toString())) {
        dashboardPage = "admin-dashboard.jsp";
    }
%>
<%
    ItemService itemService = new ItemService();
    List<Item> items = itemService.getAllItems();
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Bill</title>
    <link rel="stylesheet" type="text/css" href="../css/bill.css">
</head>
<body>
<div class="bill-container">
    <h2>Add New Bill</h2>

    <a href="<%=request.getContextPath()%>/<%=dashboardPage%>">Back to Dashboard</a>

    <form action="<%=request.getContextPath()%>/manageBills/create" method="post">
        <label for="phone">Customer Phone</label>
        <input type="text" id="phone" name="phone" required>

        <h3>Bill Items</h3>
        <table id="billItems" class="bill-table">
            <thead>
            <tr>
                <th>Item</th>
                <th>Unit Price</th>
                <th>Units</th>
                <th>Total</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <!-- Rows will be added dynamically -->
            </tbody>
        </table>

        <button type="button" class="bill-btn" onclick="addNewRow()">+ Add Item</button>
        <br><br>
        <button type="submit" class="bill-btn">Save Bill</button>
    </form>
</div>

<script>
    // Prebuild the options HTML server-side
    var itemOptions = "";
    <% for (Item i : items) { %>
    itemOptions += "<option value='<%=i.getId()%>' data-unit-price='<%=i.getPrice()%>'><%=i.getName()%> (Rs. <%=i.getPrice()%>)</option>";
    <% } %>

    function addNewRow() {
        const tbody = document.querySelector("#billItems tbody");
        const row = document.createElement("tr");

        row.innerHTML = `
            <td>
                <select name="item_id" onchange="updateUnitPrice(this)">
                    <option value="" selected disabled>-- Select --</option>
                    ${itemOptions}
                </select>
            </td>
            <td><input type="text" name="price" readonly></td>
            <td><input type="number" name="quantity" min="1" value="1" onchange="updateTotal(this)"></td>
            <td><input type="text" name="total_amount" readonly></td>
            <td><button type="button" class="bill-btn-danger" onclick="this.closest('tr').remove()">X</button></td>
        `;
        tbody.appendChild(row);
    }

    function updateUnitPrice(select) {
        const price = parseFloat(select.selectedOptions[0].getAttribute("data-unit-price")) || 0;
        const row = select.closest("tr");
        row.querySelector('input[name="price"]').value = price.toFixed(2);
        updateTotal(row.querySelector('input[name="quantity"]'));
    }

    function updateTotal(input) {
        const row = input.closest("tr");
        const price = parseFloat(row.querySelector('input[name="price"]').value) || 0;
        const units = parseInt(input.value) || 0;
        row.querySelector('input[name="total_amount"]').value = (price * units).toFixed(2);
    }
</script>
</body>
</html>
