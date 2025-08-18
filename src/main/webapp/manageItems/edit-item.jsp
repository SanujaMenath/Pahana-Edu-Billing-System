<%@ page import="com.pahanaedu.model.Item, dao.ItemDAO" %>
<%@ page import="dao.ItemDAOImpl" %>
<%@ page import="com.pahanaedu.model.User" %>
<%
    int itemId = Integer.parseInt(request.getParameter("itemId"));
    ItemDAO dao = new ItemDAOImpl();
    Item item = dao.getItemById(itemId);

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
<html>
<head>
    <title>Pahana Edu - Edit Item</title>
    <link rel="stylesheet" type="text/css" href="../css/form.css">
</head>
<body>
<div class="form-container">
    <h2>Edit Item</h2>
    <form action="update-item" method="post">
        <input type="hidden" name="itemId" value="<%= item.getId() %>">

        <label>Item Name:</label>
        <input type="text" name="itemName" value="<%= item.getName() %>"><br>

        <label>Description:</label>
        <input type="text" name="description" value="<%= item.getDescription() %>"><br>

        <label>Price:</label>
        <input type="number" step="0.01" name="price" value="<%= item.getPrice() %>"><br>

        <label>Quantity:</label>
        <input type="number" name="quantity" value="<%= item.getQuantity() %>"><br>

        <button type="submit">Update</button>
    </form>

    <a class="btn-secondary" href="<%=request.getContextPath()%>/<%=dashboardPage%>">Back to Dashboard</a>
</div>
</body>
</html>
