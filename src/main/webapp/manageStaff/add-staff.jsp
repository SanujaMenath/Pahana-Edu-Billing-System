<%@ page import="com.pahanaedu.model.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null || !"ADMIN".equalsIgnoreCase(user.getRole().toString())) {
        response.sendRedirect(request.getContextPath() + "/unauthorized.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Staff</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/form.css">
</head>
<body>
<div class="form-container">
    <h2>Add New Staff</h2>

    <form action="<%=request.getContextPath()%>/manageStaff/add-staff" method="post">
        <label for="username">Username:</label>
        <input type="text" name="username" required>

        <label for="password">Password:</label>
        <input type="password" name="password" required>

        <button type="submit">Add Staff</button>
    </form>

    <a href="<%=request.getContextPath()%>/admin-dashboard.jsp">Back to Dashboard</a>
</div>
</body>
</html>
