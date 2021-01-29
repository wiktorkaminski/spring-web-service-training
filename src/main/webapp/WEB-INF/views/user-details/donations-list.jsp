<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <%@include file="/WEB-INF/jspf/head.jspf"%>
</head>
<body>
<header class="header--main-page">

    <nav class="container container--70">
        <%@include file="/WEB-INF/jspf/nav-top.jspf" %>
    </nav>

    <div class="slogan container container--90">
        <div class="slogan--item">
            <h1>
                Dziękujemy, że pomagasz innym!<br/>
                Poniżej możesz sprawdzić przekazane przez Ciebie dary.
            </h1>
        </div>
    </div>
</header>


<section id="supported-institutions" class="help">
    <h2>Twoje zbiórki</h2>

        <div class="help--slides" data-id="${outerLoopStatus.count}">

            <p>Oto lista fundacji, które otrzymały od Ciebie pomoc.</p>

            <ul class="help--slides-items">
                <li>
                    <c:forEach items="${donations}" var="donation">
                        <div class="col">
<%--                            <div class="title"><c:out value="${institution.name}"/></div>--%>
<%--                            <div class="subtitle"><c:out value="${institution.description}"/></div>--%>
                        </div>
                    </c:forEach>
                </li>
            </ul>
        </div>
<%--    <div class="help--slides-pagination">--%>
<%--        <button type="button" class="btn prev-step">&#x3C;</button>--%>
<%--        <button type="button" class="btn next-step">&#x3E;</button>--%>
<%--    </div>--%>
</section>

<footer>
    <%@include file="/WEB-INF/jspf/footer-contact-div.jspf" %>
    <%@include file="/WEB-INF/jspf/footer-bottom-line-div.jspf" %>
</footer>

<script src="<c:url value="resources/js/app.js"/>"></script>
</body>
</html>
