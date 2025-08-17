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
  String msg = request.getParameter("success");
  if (msg != null) {
%>
<script>
  alert("<%= msg %>");
</script>
<%
  }
%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Add New Item</title>
  <link rel="stylesheet" type="text/css" href="../css/form.css">
</head>
<body>
<div class="form-container">
  <h2>Add New Item</h2>
  <form action="<%= request.getContextPath() %>/manageItems/add-item" method="post">
    <label for="name">Item Name:</label>
    <input id="name" type="text" name="name" required><br>

    <label for="description">Description:</label>
    <textarea id="description" name="description"></textarea><br>

    <label for="price">Price:</label>
    <input id="price" type="number" step="0.01" name="price" required><br>

    <label for="quantity">Quantity:</label>
    <input id="quantity" type="number" name="quantity" required><br>

    <button class="" type="submit">Add Item</button>
  </form>
  <a href="<%=request.getContextPath()%>/<%=dashboardPage%>">Back to Dashboard</a>
</div>
</body>
</html>
