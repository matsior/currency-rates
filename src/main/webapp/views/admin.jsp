<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <title>Kursy walut 4k</title>
    <meta charset=UTF-8">
</head>
<body>
<%@include file="../segments/header.jspf"%>
<h1>Baza użytkowników</h1>



<table border="1">
    <tr>
        <th>Id</th>
        <th>Nick</th>
        <th>Email</th>
    </tr>
    <c:forEach var="user" items="${requestScope.users}">
        <tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.email}</td>
        </tr>
    </c:forEach>
</table>
<%@include file="../segments/footer.jspf"%>
</body>
</html>