<%@ page import="com.pahanaedu.model.User" %>
<%@ page import="com.pahanaedu.model.HelpTopic" %>
<%@ page import="java.util.List" %>

<%
    // Session check (same as dashboard)
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
    if (!"STAFF".equalsIgnoreCase(user.getRole().toString())) {
        response.sendRedirect(request.getContextPath() + "/unauthorized.jsp");
        return;
    }

    List<HelpTopic> helpTopics = (List<HelpTopic>) request.getAttribute("helpTopics");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Help - System Usage Guide</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/dashboard.css">

    <style>
        .dashboard-container {
            max-width: 800px;
            margin: auto;
            padding: 20px;
        }
        .accordion {
            border: 1px solid #ccc;
            border-radius: 8px;
            margin-bottom: 10px;
            overflow: hidden;
        }
        .accordion-header {
            background: #007bff;
            color: #fff;
            padding: 12px;
            font-size: 16px;
            cursor: pointer;
            transition: background 0.3s ease;
        }
        .accordion-header:hover {
            background: #0056b3;
        }
        .accordion-content {
            display: none;
            padding: 15px;
            background: #f9f9f9;
            line-height: 1.5;
        }
        .logout-btn {
            margin-top: 20px;
            display: inline-block;
        }
    </style>
</head>
<body>

<div class="dashboard-container">
    <h2>Help - System Usage Guidelines</h2>
    <p>Welcome, <strong><%= user.getUsername() %></strong>! Find answers to common staff tasks below.</p>

    <% if (helpTopics != null && !helpTopics.isEmpty()) {
        for (HelpTopic topic : helpTopics) { %>
    <div class="accordion">
        <div class="accordion-header"><%= topic.getTitle() %></div>
        <div class="accordion-content"><%= topic.getDescription() %></div>
    </div>
    <%  }
    } else { %>
    <p>No help topics available.</p>
    <% } %>

    <a href="<%=request.getContextPath()%>/staff-dashboard.jsp" class="dashboard-btn">Back to Dashboard</a>
    <a href="<%=request.getContextPath()%>/logout" class="logout-btn">Logout</a>
</div>

<script>
    document.querySelectorAll('.accordion-header').forEach(header => {
        header.addEventListener('click', () => {
            const content = header.nextElementSibling;
            content.style.display = (content.style.display === 'block') ? 'none' : 'block';
        });
    });
</script>

</body>
</html>
