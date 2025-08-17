<%@ page import="com.pahanaedu.model.User" %>
<%
  User user = (User) session.getAttribute("user");
  if (user == null) {
    response.sendRedirect(request.getContextPath() + "/login.jsp");
    return;
  }

  if (!"STAFF".equalsIgnoreCase(user.getRole().toString())) {
    response.sendRedirect(request.getContextPath() + "/unauthorized.jsp");
    return;
  }
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Staff Dashboard</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/dashboard.css">
</head>
<body>
<div class="dashboard-container">
  <h2 class="dashboard-title">Welcome, <%= user.getUsername() %>!</h2>
  <p class="role-label">Role: Staff</p>

  <!-- Card Grid -->
  <div class="card-grid">
    <a href="<%=request.getContextPath()%>/manageCustomers/add-customer.jsp" class="dashboard-card">
      <h3>Add Customer</h3>
      <p>Register a new customer into the system.</p>
    </a>

    <a href="<%=request.getContextPath()%>/manageCustomers/manage-customers.jsp" class="dashboard-card">
      <h3>Manage Customers</h3>
      <p>View, edit, and remove customer records.</p>
    </a>

    <a href="<%=request.getContextPath()%>/manageItems/add-items.jsp" class="dashboard-card">
      <h3>Add Items</h3>
      <p>Add new items to the inventory.</p>
    </a>

    <a href="<%=request.getContextPath()%>/manageItems/manage-items.jsp" class="dashboard-card">
      <h3>Manage Items</h3>
      <p>Update or delete existing inventory items.</p>
    </a>

    <a href="<%=request.getContextPath()%>/manageBills/add-bill.jsp" class="dashboard-card">
      <h3>Add Bill</h3>
      <p>Create and process new bills for customers.</p>
    </a>

    <a href="<%=request.getContextPath()%>/help" class="dashboard-card">
      <h3>Help</h3>
      <p>Get assistance with using the system.</p>
    </a>
  </div>

  <!-- Logout -->
  <a href="<%=request.getContextPath()%>/logout" class="logout-btn">Logout</a>
</div>
</body>
</html>
