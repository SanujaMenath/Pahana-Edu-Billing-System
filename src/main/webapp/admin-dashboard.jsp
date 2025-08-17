<%@ page import="com.pahanaedu.model.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }

    if (!"ADMIN".equalsIgnoreCase(user.getRole().toString())) {
        response.sendRedirect(request.getContextPath() + "/unauthorized.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/dashboard.css">
</head>
<body>
<div class="dashboard-container">
    <h2 class="dashboard-title">Welcome, <%= user.getUsername() %>!</h2>
    <p class="role-label">Role: Admin</p>

    <div class="card-grid">
        <!-- Staff Management -->
        <a href="<%=request.getContextPath()%>/manageStaff/add-staff.jsp" class="dashboard-card">
            <h3>Add Staff</h3>
            <p>Create a new staff account</p>
        </a>
        <a href="<%=request.getContextPath()%>/manageStaff/manage-staff.jsp" class="dashboard-card">
            <h3>Manage Staff</h3>
            <p>View, edit or remove staff</p>
        </a>

        <!-- Customer Management -->
        <a href="<%=request.getContextPath()%>/manageCustomers/add-customer.jsp" class="dashboard-card">
            <h3>Add Customer</h3>
            <p>Register new customers</p>
        </a>
        <a href="<%=request.getContextPath()%>/manageCustomers/manage-customers.jsp" class="dashboard-card">
            <h3>Manage Customers</h3>
            <p>View, edit or remove customers</p>
        </a>

        <!-- Item Management -->
        <a href="<%=request.getContextPath()%>/manageItems/add-items.jsp" class="dashboard-card">
            <h3>Add Items</h3>
            <p>Add new products to inventory</p>
        </a>
        <a href="<%=request.getContextPath()%>/manageItems/manage-items.jsp" class="dashboard-card">
            <h3>Manage Items</h3>
            <p>View, edit or delete items</p>
        </a>

        <!-- Billing -->
        <a href="<%=request.getContextPath()%>/manageBills/add-bill.jsp" class="dashboard-card">
            <h3>Add Bill</h3>
            <p>Create new billing records</p>
        </a>
    </div>

    <a href="<%=request.getContextPath()%>/logout" class="logout-btn">Logout</a>
</div>
</body>
</html>
