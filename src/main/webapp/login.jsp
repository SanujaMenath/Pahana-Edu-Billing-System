<html>
<head>
    <title>Pahana Edu Billing System - Login</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <div class="login-container">
        <h2>Login to Pahana Edu Billing System</h2>
        <form action="login" method="post">
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required>
            </div>

            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="form-group">
                <button type="submit">Login</button>
            </div>
            <div class="form-group">
                <p>Don't have an account? <a href="register.jsp">Register here</a></p>
            </div>
        </form>
        <div class="error-message">
            <% if (request.getAttribute("errorMessage") != null) { %>
                <p><%= request.getAttribute("errorMessage") %></p>
            <% } %>
        </div>
    </div>
    <footer>
        <p>&copy; 2023 Pahana Edu Billing System. All rights reserved.</p>
    </footer>
</body>
</html>