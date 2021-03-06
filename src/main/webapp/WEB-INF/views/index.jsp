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
                Zacznij pomagać!<br/>
                Oddaj niechciane rzeczy w zaufane ręce
            </h1>
        </div>
    </div>
</header>

<section class="stats">
    <div class="container container--85">
        <div class="stats--item">
            <em><c:out value="${donatedBags}"/></em>

            <h3>Oddanych worków</h3>
            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Eius est beatae, quod accusamus illum
                tempora!</p>
        </div>

        <div class="stats--item">
            <em><c:out value="${countedDonations}"/></em>
            <h3>Przekazanych darów</h3>
            <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Laboriosam magnam, sint nihil cupiditate quas
                quam.</p>
        </div>

    </div>
</section>

<section id="whats-about" class="steps">
    <h2>Wystarczą 4 proste kroki</h2>

    <div class="steps--container">
        <div class="steps--item">
            <span class="icon icon--hands"></span>
            <h3>Wybierz rzeczy</h3>
            <p>ubrania, zabawki, sprzęt i inne</p>
        </div>
        <div class="steps--item">
            <span class="icon icon--arrow"></span>
            <h3>Spakuj je</h3>
            <p>skorzystaj z worków na śmieci</p>
        </div>
        <div class="steps--item">
            <span class="icon icon--glasses"></span>
            <h3>Zdecyduj komu chcesz pomóc</h3>
            <p>wybierz zaufane miejsce</p>
        </div>
        <div class="steps--item">
            <span class="icon icon--courier"></span>
            <h3>Zamów kuriera</h3>
            <p>kurier przyjedzie w dogodnym terminie</p>
        </div>
    </div>

    <a href="${pageContext.request.contextPath}/register/form" class="btn btn--large">Załóż konto</a>
</section>

<section id="about-us" class="about-us">
    <div class="about-us--text">
        <h2>O nas</h2>
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Voluptas vitae animi rem pariatur incidunt libero
            optio esse quisquam illo omnis.</p>
        <img src="<c:url value="resources/images/signature.svg"/>" class="about-us--text-signature" alt="Signature"/>
    </div>
    <div class="about-us--image"><img src="<c:url value="resources/images/about-us.jpg"/>" alt="People in circle"/>
    </div>
</section>

<section id="supported-institutions" class="help">
    <h2>Komu pomagamy?</h2>

    <!-- SLIDE 1 -->
    <c:forEach items="${institutions}" step="4" varStatus="outerLoopStatus">
        <div class="help--slides" data-id="${outerLoopStatus.count}">

            <p>W naszej bazie znajdziesz listę zweryfikowanych Fundacji, z którymi współpracujemy.
                Możesz sprawdzić czym się zajmują.</p>

            <ul class="help--slides-items">
                <li>
                    <c:forEach items="${institutions}" var="institution" begin="${outerLoopStatus.index}" end="${outerLoopStatus.index + 1}">
                        <div class="col">
                            <div class="title"><c:out value="${institution.name}"/></div>
                            <div class="subtitle"><c:out value="${institution.description}"/></div>
                        </div>
                    </c:forEach>
                </li>
                <li>
                    <c:forEach items="${institutions}" var="institution" begin="${outerLoopStatus.index + 2}" end="${outerLoopStatus.index + 3}">
                        <div class="col">
                            <div class="title"><c:out value="${institution.name}"/></div>
                            <div class="subtitle"><c:out value="${institution.description}"/></div>
                        </div>
                    </c:forEach>
                </li>
            </ul>

        </div>
    </c:forEach>
    <div class="help--slides-pagination">
        <button type="button" class="btn prev-step">&#x3C;</button>
        <button type="button" class="btn next-step">&#x3E;</button>
    </div>
</section>

<footer>
    <%@include file="/WEB-INF/jspf/footer-contact-div.jspf" %>
    <%@include file="/WEB-INF/jspf/footer-bottom-line-div.jspf" %>
</footer>

<script src="<c:url value="resources/js/app.js"/>"></script>
</body>
</html>
