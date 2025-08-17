<%@ page import="java.util.List" %>
<%@ page import="com.pahanaedu.model.User" %>
<%@ page import="com.pahanaedu.service.UserService" %>
<%
    User currentUser = (User) session.getAttribute("user");
    if (currentUser == null || !"ADMIN".equalsIgnoreCase(currentUser.getRole().toString())) {
        response.sendRedirect(request.getContextPath() + "/unauthorized.jsp");
        return;
    }

    UserService userService = new UserService();
    List<User> staffList = userService.getAllStaff();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Staff</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/management.css">
</head>
<body>
<div class="management-container">
    <h2>Manage Staff</h2>

    <div class="management-actions">
        <a href="<%=request.getContextPath()%>/manageStaff/add-staff.jsp" class="btn-primary">Add New Staff</a>
        <a href="<%=request.getContextPath()%>/admin-dashboard.jsp" class="btn-secondary">Back to Dashboard</a>
    </div>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            if (staffList != null && !staffList.isEmpty()) {
                for (User staff : staffList) {
        %>
        <tr>
            <td><%= staff.getId() %></td>
            <td><%= staff.getUsername() %></td>
            <td>
                <form action="<%=request.getContextPath()%>/manageStaff/delete-staff" method="post" style="display:inline;">
                    <input type="hidden" name="id" value="<%= staff.getId() %>">
                    <button type="submit" class="btn-danger">Delete</button>
                </form>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="3" style="text-align:center;">No staff found</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>
</body>
</html>
