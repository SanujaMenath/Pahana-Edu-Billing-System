<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Return proper HTTP status
    response.setStatus(403);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>Unauthorized</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/unauthorized.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>
<body>
<main class="unauth-container">
    <div class="card">
        <div class="icon" aria-hidden="true">🚫</div>
        <h1>Access Denied</h1>
        <p>You don’t have permission to view this page.</p>

        <div class="actions">
            <a class="btn" href="<%=request.getContextPath()%>/login.jsp">Go to Login</a>
            <a class="link" href="javascript:history.back()">Go Back</a>
        </div>

        <p class="hint">
            If this is an error, please contact an administrator.
        </p>
    </div>
</main>
</body>
</html>
