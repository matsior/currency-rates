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
        <h1>Kursy średnie</h1>
        <table border="1">
            <tr>
                <th>Nazwa</th>
                <th>Kod</th>
                <th>Wartość</th>
            </tr>
            <c:forEach var="currency" items="${rates}">
                <tr>
                    <td>${currency.currency}</td>
                    <td>${currency.code}</td>
                    <td>${currency.mid}</td>
                </tr>
            </c:forEach>
            <tr>
                <td>ŚĄśłżłęó</td>
                <td>ŚĄśłżłęó</td>
                <td>ŚĄśłżłęó</td>
            </tr>
        </table>
    <%@include file="../segments/footer.jspf"%>
</body>
</html>