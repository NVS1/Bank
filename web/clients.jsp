<%--
  Created by IntelliJ IDEA.
  User: Ваня
  Date: 09.09.2018
  Time: 21:42
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>clients</title>
</head>
<body>
    <c:forEach items="${clients}" var="client">
        <tr>
            <td> Name: <c:out value="${client.name}" /><br></td>
            <td> Phone: <c:out value="${client.phone}" /><br></td>
            <form action="/accounts" method="post">
                <input type="text" hidden name="phone" value="${client.phone}"><br>
                <input type="submit">
            </form>
        </tr><br>
    </c:forEach>
</body>
</html>
