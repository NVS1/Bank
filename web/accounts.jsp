<%--
  Created by IntelliJ IDEA.
  User: Ваня
  Date: 09.09.2018
  Time: 22:15
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Accounts</title>
</head>
<body>
    <h2>Accounts <c:out value="${name}"/></h2>
    <c:forEach items="${accounts}" var="account">
        Number: <c:out value="${account.number}" /><br>
        Currency: <c:out value="${account.currency}" /><br>
        Money: <c:out value="${account.money/100}" /><br><br>
    </c:forEach>
</body>
</html>
