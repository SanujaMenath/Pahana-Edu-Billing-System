<%@ page import="com.pahanaedu.model.Item" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.ItemDAO" %>
<%@ page import="dao.ItemDAOImpl" %>
<%
    String msg = request.getParameter("success");
    if (msg != null) {
%>
<script>alert("<%= msg %>");</script>
<%
    }
    ItemDAO itemDAO = new ItemDAOImpl();
    List<Item> items = null;

    try {
        items = itemDAO.getAllItems();
    } catch (Exception e) {
        e.printStackTrace();
    }

%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Items</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/dashboard.css">
</head>
<body>
<div class="dashboard-container">
    <h2>Manage Items</h2>
    <table border="1" cellpadding="10" cellspacing="0" >
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            if (items != null && !items.isEmpty()) {
                for (Item item : items) {
        %>
        <tr>
            <td><%= item.getId() %></td>
            <td><%= item.getName() %></td>
            <td><%= item.getDescription() %></td>
            <td><%= item.getPrice() %></td>
            <td><%= item.getQuantity() %></td>
            <td>
                <a href="<%= request.getContextPath() %>/manageItems/edit-item.jsp?id=<%= item.getId() %>">Edit</a> |
                <a href="<%= request.getContextPath() %>/manageItems/delete-item?id=<%= item.getId() %>"
                   onclick="return confirm('Are you sure you want to delete this item?')">Delete</a>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="6">No items found.</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>
</body>
</html>
