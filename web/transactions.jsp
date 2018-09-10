<%--
  Created by IntelliJ IDEA.
  User: Ваня
  Date: 10.09.2018
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Transactions</title>
</head>
<body>
    <h2>Transactions <c:out value="${name}"/></h2>

    <c:forEach items="${transactions}" var="t">
        From account: <c:out value="${t.from.number}" /><br>
        To account: <c:out value="${t.to.number}" /><br>
        Money: <c:out value="${t.money}" /><br>
        Date: <c:out value="${t.date}" /><br><br>
    </c:forEach>
</body>
</html>
