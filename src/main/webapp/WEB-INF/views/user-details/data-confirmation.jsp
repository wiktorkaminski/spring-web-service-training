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
        <%@include file="/WEB-INF/jspf/nav-top.jspf" %>
    </nav>

    <div class="slogan container container--90">
        <h2>
                Twoje dane zostały zaktualizowane.
        </h2>
    </div>
</header>

<footer>
    <%@include file="/WEB-INF/jspf/footer-contact-div.jspf" %>
    <%@include file="/WEB-INF/jspf/footer-bottom-line-div.jspf" %>
</footer>

</body>
</html>

