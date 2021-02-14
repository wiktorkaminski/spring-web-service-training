<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <title>New Admin - Charity Admin</title>
    <%@include file="/WEB-INF/jspf/admin/head.jspf" %>
</head>
<body class="sb-nav-fixed">
<%@include file="/WEB-INF/jspf/admin/top-nav.jspf" %>
<div id="layoutSidenav">
    <div id="layoutSidenav_nav">
        <%@include file="/WEB-INF/jspf/admin/side-nav.jspf" %>
    </div>
    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid">
                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/dashboard">Dashboard</a></li>
                    <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/${userType}s/list">${userType}s</a></li>
                    <li class="breadcrumb-item active">add new admin</li>
                </ol>
            </div>

            <div class="container">
                <div class="row">
                    <div class="col-lg-5">
                        <div class="card border-0 rounded-lg mt-5">
                            <div class="card-body">
                                <form:form action="/admin/admins/form" modelAttribute="userdto"
                                           method="POST">
                                    <div class="form-group">
                                        <label class="small mb-1" for="firstName">Name</label>
                                        <form:input class="form-control py-4" path="firstName" id="firstName" type="text"
                                                    maxlength="255"/>
                                        <form:errors path="firstName" cssClass="alert-danger"/>
                                    </div>
                                    <div class="form-group">
                                        <label class="small mb-1" for="lastName">Last name</label>
                                        <form:input class="form-control py-4" path="lastName" id="lastName" type="text"
                                                    maxlength="255"/>
                                        <form:errors path="lastName" cssClass="alert-danger"/>
                                    </div>
                                    <div class="form-group">
                                        <label class="small mb-1" for="email">E-mail</label>
                                        <form:input class="form-control py-4" path="email" id="email" type="email"
                                                    maxlength="255"/>
                                        <form:errors path="email" cssClass="alert-danger"/>

                                    </div>
                                    <div class="form-group">
                                        <label class="small mb-1" for="password">Password</label>
                                        <form:input class="form-control py-4" path="password" id="password" type="password"
                                                    maxlength="255" placeholder="Type in new password"/>
                                        <form:errors path="password" cssClass="alert-danger"/>
                                    </div>
                                    <div class="form-group">
                                        <label class="small mb-1" for="password2">Repeat password</label>
                                        <input class="form-control py-4" id="password2" name="password2" type="password"
                                                    maxlength="255" placeholder="Retype password"/>
                                    </div>

                                    <div class="form-group d-flex align-items-center justify-content-between mt-4 mb-0">
                                        <div class="justify-content-between">
                                            <button class="btn btn-secondary" type="button">
                                               <a class="text-white card-link"
                                                   href="${pageContext.request.contextPath}/admin/${userType}s/list">Back</a>
                                            </button>
                                            <input class="btn btn-primary" type="submit" value="Add"/>
                                        </div>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div style="margin: 7%"></div>
        </main>
        <%@include file="/WEB-INF/jspf/admin/footer.jspf" %>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
<script src="<c:url value="/resources/js/script_admin.js"/>"></script>
</body>
</html>
