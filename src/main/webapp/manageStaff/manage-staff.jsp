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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/table.css">
</head>
<body>
<div class="table-container">
    <h2>Manage Staff</h2>

    <a href="<%=request.getContextPath()%>/manageStaff/add-staff.jsp">Add New Staff</a>
    <a href="<%=request.getContextPath()%>/admin-dashboard.jsp">Back to Dashboard</a>
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
            for (User staff : staffList) {
        %>
        <tr>
            <td><%= staff.getId() %></td>
            <td><%= staff.getUsername() %></td>
            <td>
                <form action="<%=request.getContextPath()%>/manageStaff/delete-staff" method="post" style="display:inline;">
                    <input type="hidden" name="id" value="<%= staff.getId() %>">
                    <button type="submit">Delete</button>
                </form>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>

</div>
</body>
</html>
