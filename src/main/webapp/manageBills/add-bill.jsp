<%@ page import="java.util.List" %>
<%@ page import="com.pahanaedu.model.Item" %>
<%@ page import="com.pahanaedu.service.ItemService" %>

<%
    ItemService itemService = new ItemService();
    List<Item> items = itemService.getAllItems();
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Bill</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f8f9fa; }
        .container { width: 700px; margin: 40px auto; background: #fff; padding: 20px; border-radius: 6px; box-shadow: 0 2px 8px rgba(0,0,0,0.1);}
        h2, h3 { text-align: center; }
        label { display: block; font-weight: bold; margin-top: 10px; }
        input, select { width: 100%; padding: 8px; margin-top: 4px; margin-bottom: 15px; }
        table { width: 100%; border-collapse: collapse; margin-top: 10px; }
        th, td { padding: 8px; border: 1px solid #ccc; text-align: left; }
        button { background-color: #28a745; color: white; padding: 8px 12px; border: none; border-radius: 4px; cursor: pointer; }
        button:hover { background-color: #218838; }
        .btn-danger { background-color: #dc3545; }
        .btn-danger:hover { background-color: #c82333; }
    </style>
</head>
<body>
<div class="container">
    <h2>Add New Bill</h2>

    <form action="<%=request.getContextPath()%>/manageBills/create" method="post">
        <label for="phone">Customer Phone</label>
        <input type="text" id="phone" name="phone" required>

        <h3>Bill Items</h3>
        <table id="billItems">
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
            <!-- initial row can be empty -->
            </tbody>
        </table>

        <button type="button" onclick="addNewRow()">+ Add Item</button>
        <br><br>
        <button type="submit">Save Bill</button>
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
    <td><button type="button" class="btn-danger" onclick="this.closest('tr').remove()">X</button></td>
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
