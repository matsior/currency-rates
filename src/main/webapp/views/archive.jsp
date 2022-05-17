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
<h1>Kursy archiwalne</h1>

<form action="archive" method="get">
    <label for="code">Kod waluty</label>
    <select id="code" name="code" required>
        <c:forEach var="currency" items="${requestScope.currencies}">
            <option value="${currency.code}">${currency.currency}</option>
        </c:forEach>
    </select>
    <label for="date">Data</label>
    <input type="date" id="date" name="date" required><br>
    <input type="submit" value="Sprawdź">
</form>

<c:if test="${not empty requestScope.currency}">
    <table border="1">
        <tr>
            <th>Nazwa</th>
            <th>Kod</th>
            <th>Wartość</th>
        </tr>
        <tr>
            <td>${requestScope.currency.currency}</td>
            <td>${requestScope.currency.code}</td>
            <td>${requestScope.currency.mid}</td>
        </tr>
    </table>
</c:if>

<%@include file="../segments/footer.jspf"%>
</body>
</html>