

<html>
<head>
    <title>Pahana Edu Billing System - Login</title>
    <link rel="stylesheet" type="text/css" href="css/style.css" >
</head>
<body>
    <div class="login-container">
        <h2>Login to Pahana Edu Billing System</h2>
        <form action="login" method="post">
            <label for="username">Username:</label>
            <input id="username" name="username" type="text" />

            <br/>
            <label for="password">Password:</label>
            <input id="password" type="password" name="password" required />
            <br/>
            <button type="submit">Login</button>
        </form>

        <%
            String error = (String) request.getAttribute("error");
            if (error != null) {
        %>
        <div style="color:red;"><%= error %></div>
        <%
            }
        %>


    </div>
    <footer>
        <p>&copy; 2023 Pahana Edu Billing System. All rights reserved.</p>
    </footer>
</body>
</html>