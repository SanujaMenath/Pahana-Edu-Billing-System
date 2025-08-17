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
<html>
<head>
    <title>Pahan Edu - Add Customer</title>
    <link rel="stylesheet" type="text/css" href="../css/form.css">
</head>
<body>
<div class="form-container">
    <h2>Add New Customer</h2>
    <form action="add-customer" method="post">
        <div class="form-group">
            <label for="name">Name:</label><input id="name" type="text" name="name" required/><br/>
        </div>
        <div class="form-group">
            <label for="phone">Phone:</label><input id="phone" type="text" name="phone" required/><br/>
        </div>
        <div class="form-group">
            <label for="address">Address:</label><textarea id="address" name="address" required></textarea><br/>
        </div>
        <div class="form-group">
            <button type="submit">Add Customer</button>
        </div>
    </form>

    <a href="<%=request.getContextPath()%>/<%=dashboardPage%>">Back to Dashboard</a>
</div>
</body>
</html>
