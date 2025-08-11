
<html>
<head>
    <title>Pahan Edu - Add Customer</title>
    <link rel="stylesheet" type="text/css" href="../css/add-customer.css">
</head>
<body>
<div class="form-container">
    <h2>Add New Customer</h2>
    <form action="add-customer" method="post">
        <div class="form-group">
            <label for="name">Name:</label><input id="name" type="text" name="name" required/><br/>
        </div>
        <div class="form-group">
            <label for="phone">Phone:</label><input id="phone" type="text" name="phone" required/><br/>
        </div>
        <div class="form-group">
            <label for="address">Address:</label><textarea id="address" name="address" required></textarea><br/>
        </div>
        <div class="form-group">
            <button type="submit">Add Customer</button>
        </div>
    </form>
</div>
</body>
</html>
