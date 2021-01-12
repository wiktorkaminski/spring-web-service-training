<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<head>
    <%@include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<header class="header--form-page">
    <nav class="container container--70">
        <ul class="nav--actions">
            <li class="logged-user">
                Witaj Agata
                <ul class="dropdown">
                    <li><a href="#">Profil</a></li>
                    <li><a href="#">Moje zbiórki</a></li>
                    <li><a href="#">Wyloguj</a></li>
                </ul>
            </li>
        </ul>

        <ul>
            <li><a href="index.html" class="btn btn--without-border active">Start</a></li>
            <li><a href="index.html#steps" class="btn btn--without-border">O co chodzi?</a></li>
            <li><a href="index.html#about-us" class="btn btn--without-border">O nas</a></li>
            <li><a href="index.html#help" class="btn btn--without-border">Fundacje i organizacje</a></li>
            <li><a href="index.html#contact" class="btn btn--without-border">Kontakt</a></li>
        </ul>
    </nav>

    <div class="slogan container container--90">
        <h2>
            <c:if test="${empty errorMsg}">
                Dziękujemy za przesłanie formularza Na maila prześlemy wszelkie
                informacje o odbiorze.
            </c:if>
            <c:if test="${not empty errorMsg}">
                Coś poszło nie tak :( <br/>
                Przekazane z formularza nie zostały zapisane.<br/>
                Zapisz poniższy błąd i skontaktuj się z nami za pomocą formularza kontaktowego.<br>
                ${errorMsg}
            </c:if>

        </h2>
    </div>
</header>

<footer>
    <%@include file="/WEB-INF/jspf/footer-contact-div.jspf" %>
    <%@include file="/WEB-INF/jspf/footer-bottom-line-div.jspf" %>
</footer>

<script src="${pageContext.request.contextPath}/resources/js/app.js"></script>
</body>
</html>

