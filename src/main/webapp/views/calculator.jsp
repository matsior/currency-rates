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
    <h1>Przelicznik walut</h1>

    <form action="calculator" method="post">
        <label for="base">Waluta początkowa</label>
        <select id="base" name="base" required>
            <c:forEach var="currency" items="${requestScope.currencies}">
                <option value="${currency.code}">${currency.currency}</option>
            </c:forEach>
        </select><br>
        <label for="amount">Kwota</label>
        <input type="number" name="amount" id="amount" step="0.01" required><br>
        <label for="target">Waluta końcowa</label>
        <select id="target" name="target" required>
            <c:forEach var="currency" items="${requestScope.currencies}">
                <option value="${currency.code}">${currency.currency}</option>
            </c:forEach>
        </select><br>
        <input type="submit" value="Wyślij">
    </form>

    <c:if test="${not empty requestScope.result}">
        <p><b>${requestScope.result.amount} ${requestScope.result.baseCurrencyName} to ${requestScope.result.result} ${requestScope.result.targetCurrencyName}</b></p>
    </c:if>

    <%@include file="../segments/footer.jspf"%>
</body>
</html>