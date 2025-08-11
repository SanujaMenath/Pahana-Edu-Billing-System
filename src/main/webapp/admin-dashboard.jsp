<%@ page import="persistence.model.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("/login.jsp");
        return;
    }
%>

<h2>Welcome, <%= user.getUsername() %>!</h2>
<p>Role: <%= user.getRole() %></p>
<a href="logout">Logout</a>

