<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Kursy 4K - rejestracja</title>
</head>
<body>
    <h1>Utwórz konto</h1>
    <form action="signup" method="post">
        <label for="username">Nick</label>
        <input type="text" name="username" id="username" required><br>
        <label for="email">Adres email</label>
        <input type="email" name="email" id="email" required><br>
        <label for="password">Hasło</label>
        <input type="password" name="password" id="password" required><br>
        <p>(Minimum 8 znaków, duża litera, mała litera, cyfra, znak specjalny)</p>
        <input type="submit" value="Wyślij">
    </form>
    <a href="${pageContext.request.contextPath}">Powrót do strony głównej</a>
    <%@ include file="../segments/footer.jspf"%>
</body>
</html>
