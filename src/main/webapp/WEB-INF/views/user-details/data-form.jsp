<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <%@include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<header>
    <nav class="container container--70">
        <%@include file="/WEB-INF/jspf/nav-top.jspf" %>
    </nav>
</header>

<section class="login-page">
    <h2>Twoje dane</h2>
    <form:form method="POST" modelAttribute="user">
        <div class="form-group">
            <form:input type="text" path="firstName" placeholder="Imię" />
            <form:errors path="firstName" cssClass="login-form--error"/>
        </div>
        <div class="form-group">
            <form:input type="text" path="lastName" placeholder="Nazwisko" />
            <form:errors path="lastName" cssClass="login-form--error"/>

        </div>
            <div class="form-group">
                <form:input type="email" path="email" readonly="true"/>
                <form:errors path="email" cssClass="login-form--error"/>
            </div>
        <div class="form-group">
            <form:input type="password" path="password" placeholder="Hasło" />
            <form:errors path="password" cssClass="login-form--error"/>
        </div>
        <div class="form-group">
            <input type="password" name="password2" placeholder="Powtórz hasło" />
        </div>

        <div class="form-group form-group--buttons">
            <a href="${pageContext.request.contextPath}/" class="btn btn--without-border">Wstecz</a>
            <button class="btn" type="submit">Zmień dane</button>
        </div>
    </form:form>
</section>

<footer>
    <%@include file="/WEB-INF/jspf/footer-contact-div.jspf" %>
    <%@include file="/WEB-INF/jspf/footer-bottom-line-div.jspf" %>
</footer>
</body>
</html>
