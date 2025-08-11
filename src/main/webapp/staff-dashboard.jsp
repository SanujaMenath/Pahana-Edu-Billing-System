<%@ page import="persistence.model.User" %>
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
  <h2>Welcome, <%= user.getUsername() %>!</h2>
  <p>Role: Staff</p>

  <div class="button-group">
    <a href="<%=request.getContextPath()%>/manageCustomers/add-customer.jsp" class="dashboard-btn">Add New Customer</a>
    <a href="<%=request.getContextPath()%>/manageCustomers/manage-customers.jsp" class="dashboard-btn">Manage Customers</a>
    <a href="<%=request.getContextPath()%>/manageItems.jsp" class="dashboard-btn">Manage Items</a>
  </div>

  <a href="<%=request.getContextPath()%>/logout" class="logout-btn">Logout</a>
</div>
</body>
</html>
