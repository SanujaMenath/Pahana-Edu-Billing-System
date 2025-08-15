<%@ page import="java.util.List" %>
<%@ page import="com.pahanaedu.model.Customer" %>
<%@ page import="dao.CustomerDAO" %>
<%@ page import="dao.CustomerDAOImpl" %>
<%@ page import="com.pahanaedu.model.User" %>

<%
  User loggedUser = (User) session.getAttribute("user");
  if (loggedUser == null) {
    response.sendRedirect(request.getContextPath() + "/login.jsp");
    return;
  }

  CustomerDAO customerDAO = new CustomerDAOImpl();
  List<Customer> customers = null;

  try {
    customers = customerDAO.getAllCustomers();
  } catch (Exception e) {
    e.printStackTrace();
  }
%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Manage Customers</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/dashboard.css">
</head>
<body>
<div class="dashboard-container">
  <h2>Customer Management</h2>
  <table border="1" cellpadding="10" cellspacing="0">
    <tr>
      <th>Account Number</th>
      <th>Name</th>
      <th>Phone</th>
      <th>Address</th>
      <th>Units Consumed</th>
      <th>Actions</th>
    </tr>
    <%
      if (customers != null) {
        for (Customer c : customers) {
    %>
    <tr>
      <td><%= c.getAccountNumber() %></td>
      <td><%= c.getName() %></td>
      <td><%= c.getPhone() %></td>
      <td><%= c.getAddress() %></td>
      <td><%= c.getUnitsConsumed() %></td>
      <td>
        <a href="edit-customer.jsp?accountNumber=<%= c.getAccountNumber() %>">Edit</a>
        <a href="delete-customer?accountNumber=<%= c.getAccountNumber() %>"
           onclick="return confirm('Are you sure you want to delete this customer?');">Delete</a>
      </td>

    </tr>
    <%
        }
      }
    %>
  </table>
  <br>
</div>
</body>
</html>
