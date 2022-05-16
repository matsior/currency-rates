<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Kursy 4K - logowanie</title>
</head>
<body>
<h1>Zaloguj się</h1>
<form action="j_security_check" method="post">
    <label for="username">Nick</label>
    <input type="text" name="j_username" id="username"><br>
    <label for="password">Hasło</label>
    <input type="password" name="j_password" id="password"><br>
    <input type="submit" value="Zaloguj">
    <p>Nie masz konta? <a href="${pageContext.request.contextPath}/signup">Zarejestruj się</a></p>
    <a href="${pageContext.request.contextPath}">Powrót do strony głównej</a>
</form>
<%@ include file="../segments/footer.jspf"%>
</body>
</html>
