<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <title>Institutions - Charity Admin</title>
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
                    <li class="breadcrumb-item"><a
                            href="${pageContext.request.contextPath}/admin/dashboard">Dashboard</a></li>
                    <li class="breadcrumb-item">Users</li>
                    <li class="breadcrumb-item active">${listType}s</li>
                </ol>
                <h1 class="mt-4">List of ${listType}s</h1>
                <div class="card mb-4">
                    <div class="card-body">
                        <div class="input-group-append">
                            <button class="btn btn-primary" type="button">
                                <a class="small text-white stretched-link"
                                   href="${pageContext.request.contextPath}/admin/${listType}s/add">Add new ${listType}</a>
                            </button>
                        </div>
                    </div>
                </div>
                <div class="card mb-4">
                    <div class="card-header">
                        <i class="fas fa-table mr-1"></i>
                        Registered ${listType}s
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>First name</th>
                                    <th>Last name</th>
                                    <th>E-mail</th>
                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th>Id</th>
                                    <th>First name</th>
                                    <th>Last name</th>
                                    <th>E-mail</th>
                                </tr>
                                </tfoot>
                                <tbody>
                                <c:forEach items="${users}" var="user">
                                    <tr>
                                        <td>${user.id}</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/admin/${listType}s/details-${user.id}">
                                                    ${user.firstName}
                                            </a>
                                        </td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/admin/${listType}s/details-${user.id}">
                                                    ${user.lastName}
                                            </a>
                                        </td>
                                        <td>${user.email}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <%@include file="/WEB-INF/jspf/admin/footer.jspf" %>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
<script src="<c:url value="/resources/js/script_admin.js"/>"></script>
<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js" crossorigin="anonymous"></script>
<script src="<c:url value="/resources/assets/demo/datatables-demo.js"/>"></script>
</body>
</html>
