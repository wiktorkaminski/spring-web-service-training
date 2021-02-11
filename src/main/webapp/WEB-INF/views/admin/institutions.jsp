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
                            href="${pageContext.request.contextPath}/admin/institutions/dashboard">Dashboard</a></li>
                    <li class="breadcrumb-item active">Institutions</li>
                </ol>
                <h1 class="mt-4">Institution list</h1>
                <div class="card mb-4">
                    <div class="card-body">
                        <div class="input-group-append">
                            <button class="btn btn-primary" type="button">
                                <a class="text-white card-link"
                                   href="${pageContext.request.contextPath}/admin/institutions/form">Add institution</a>
                            </button>
                        </div>
                    </div>
                </div>
                <div class="card mb-4">
                    <div class="card-header">
                        <i class="fas fa-table mr-1"></i>
                        Registered institutions
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Name</th>
                                    <th>Description</th>
                                    <th># donations</th>

                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th>Id</th>
                                    <th>Name</th>
                                    <th>Description</th>
                                    <th># donations</th>
                                </tr>
                                </tfoot>
                                <tbody>
                                <c:forEach items="${institutions}" var="institution">
                                    <tr>
                                        <td>${institution.id}</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/admin/institutions/details-${institution.id}">
                                                    ${institution.name}
                                            </a>
                                        </td>
                                        <td>${institution.description}</td>
                                        <td>${institution.donations}</td>
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
