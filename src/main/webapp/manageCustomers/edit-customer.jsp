<%@ page import="com.pahanaedu.model.Customer, dao.CustomerDAO" %>
<%@ page import="dao.CustomerDAOImpl" %>
<%
    int accountNumber = Integer.parseInt(request.getParameter("accountNumber"));
    CustomerDAO dao = new CustomerDAOImpl();
    Customer customer = dao.getCustomerByAccountNumber(accountNumber);
%>

<h2>Edit Customer</h2>
<form action="update-customer" method="post">
    <input type="hidden" name="accountNumber" value="<%= customer.getAccountNumber() %>">

    <label>Name:</label>
    <input type="text" name="name" value="<%= customer.getName() %>"><br>

    <label>Address:</label>
    <input type="text" name="address" value="<%= customer.getAddress() %>"><br>

    <label>Telephone:</label>
    <input type="text" name="telephone" value="<%= customer.getTelephone() %>"><br>

    <label>Units Consumed:</label>
    <input type="number" name="unitsConsumed" value="<%= customer.getUnitsConsumed() %>"><br>

    <button type="submit">Update</button>
</form>
