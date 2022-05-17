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

    <c:if test="${not empty pageContext.request.userPrincipal}">
        <p>Jesteś zalogowany, możesz zapisać waluty z których najczęściej korzystasz.</p>
        <p>Zapisane waluty znajdziesz w zakładce <a href="${pageContext.request.contextPath}/mycurrencies"><span>Moje waluty</span></a></p>
        <form action="mycurrencies" method="post">
            <label for="name">Wybierz walutę</label>
            <select id="name" name="code" required>
                <c:forEach var="currency" items="${requestScope.rates}">
                    <option value="${currency.code}">${currency.currency}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Zapisz">
        </form>
    </c:if>

        <table border="1">
            <tr>
                <th>Nazwa</th>
                <th>Kod</th>
                <th>Wartość</th>
            </tr>
            <c:forEach var="currency" items="${requestScope.rates}">
                <tr>
                    <td>${currency.currency}</td>
                    <td>${currency.code}</td>
                    <td>${currency.mid}</td>
                </tr>
            </c:forEach>
        </table>
    <%@include file="../segments/footer.jspf"%>
</body>
</html>