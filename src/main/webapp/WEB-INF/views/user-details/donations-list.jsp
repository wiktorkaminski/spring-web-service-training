<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <%@include file="/WEB-INF/jspf/head.jspf" %>
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

    <div class="help--slides active">

        <p>Oto lista fundacji, które otrzymały od Ciebie pomoc:</p>

        <ul class="help--slides-items">
            <c:forEach items="${donations}" step="2" varStatus="outerLoop">
                <li>
                    <c:forEach items="${donations}" var="donation" begin="${outerLoop.index}" end="${outerLoop.index +1}">
                        <div class="col">
                            <div class="title">${donation.INSTITUTION_NAME}</div>
                            <div class="subtitle">Przekazane rzeczy:
                                <c:forEach items="${donation.CATEGORIES}" var="category" varStatus="categoryLoop">
                                    ${category}<c:if test="${not categoryLoop.last}">, </c:if>
                                </c:forEach>
                            </div>
                            <div class="subtitle">Iość worków: ${donation.QUANTITY}</div>
                            <div class="subtitle">Data: ${donation.PICK_UP_DATE}</div>
                        </div>
                        <c:if test="${outerLoop.last && donations.size()%2 != 0}">
                            <%@include file="/WEB-INF/jspf/empty-li.jspf"%>
                        </c:if>
                    </c:forEach>
                </li>
            </c:forEach>
        </ul>
    </div>
</section>

<footer>
    <%@include file="/WEB-INF/jspf/footer-contact-div.jspf" %>
    <%@include file="/WEB-INF/jspf/footer-bottom-line-div.jspf" %>
</footer>

</body>
</html>
