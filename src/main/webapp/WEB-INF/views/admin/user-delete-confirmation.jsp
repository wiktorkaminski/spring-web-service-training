<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <title>Details - Charity Admin</title>
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
                    <li class="breadcrumb-item">
                        <a href="${pageContext.request.contextPath}/admin/dashboard">Dashboard</a>
                    </li>
                    <li class="breadcrumb-item">Users</li>
                    <li class="breadcrumb-item">
                        <a href="${pageContext.request.contextPath}/admin/${userType.toLowerCase()}s/list">${userType}s</a>
                    </li>
                    <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/${userType.toLowerCase()}s/details-${userdto.id}">Details</a></li>
                    <li class="breadcrumb-item active">Delete confirmation</li>
                </ol>
            </div>

            <div class="container">
                <div class="row">
                    <div class="col-lg-5">
                        <div class="card border-0 rounded-lg mt-5">
                            <div class="card-body align-content-center">
                                <div>
                                    <h3>Delete user ${userdto.firstName} ${userdto.lastName}?</h3>
                                </div>
                                <div>
                                    <form:form action="/admin/admins/delete" modelAttribute="userdto"
                                               method="POST">
                                        <form:hidden path="id"/>
                                        <div class="form-group d-flex align-items-center justify-content-between mt-4 mb-0">
                                            <div class="justify-content-between">
                                                <button class="btn btn-secondary" type="button">
                                                    <a class="text-white card-link"
                                                       href="${pageContext.request.contextPath}/admin/${userType.toLowerCase()}s/list">Cancel</a>
                                                </button>
                                                <input class="btn btn-outline-danger" type="submit" name="update"
                                                       value="Yes, I am sure"/>
                                            </div>
                                        </div>
                                    </form:form>
                                </div>
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
