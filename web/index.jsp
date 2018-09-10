<%--
  Created by IntelliJ IDEA.
  User: Ваня
  Date: 09.09.2018
  Time: 13:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bank</title>
</head>
<body>
    <h2>Exchange rates</h2>
    <h4>USD UAH 28.1</h4>
    <h4>EUR UAH 32.45</h4>
    <h4>USD EUR 0.86</h4><br>
    <h2>Add client</h2>
    <form action="/clients" method="post">
        Name: <input type="text" name="name"><br>
        Phone number: <input type="number" name="phone"><br>
        <input type="submit" value="create"><br>
    </form>
    <a href="http://localhost:8080/clients">view all clients</a><br>
    <h2>Replenish an account</h2>
    <form action="/accounts" method="post">
        Number of account: <input type="text" name="number"><br>
        Amount of money: <input type="number" name="money"><br>
        <input type="submit" value="debit"><br>
    </form>
    <h2>Money transfer</h2>
    <form action="transactions" method="post">
        Phone number: <input type="number" name="phone"><br>
        From account (number) <input type="text" name="from"><br>
        To account (number) <input type="text" name="to"><br>
        Amount of money: <input type="number" name="money"><br>
        <input type="submit" value="send"><br>
    </form>
    <h2>Amount of client's money (UAH)</h2>
    <form action="/money" method="post">
        Phone number: <input type="number" name="phone"><br>
        <input type="submit" value="get"><br>
    </form>
</body>
</html>
