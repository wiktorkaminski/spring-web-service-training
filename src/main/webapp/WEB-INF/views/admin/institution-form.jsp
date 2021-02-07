<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>New Institution - SB Admin</title>
        <%@include file="/WEB-INF/jspf/admin/head.jspf"%>
    </head>
    <body>
    <%@include file="/WEB-INF/jspf/admin/top-nav.jspf"%>
    <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <%@include file="/WEB-INF/jspf/admin/side-nav.jspf"%>
            </div>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid">
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item"><a href="#">Dashboard</a></li>
                            <li class="breadcrumb-item"><a href="#">Institutions</a></li>
                            <li class="breadcrumb-item active">Form</li>
                        </ol>
                    </div>

                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-5">
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-header"><h3 class="text-center font-weight-light my-4">New institution</h3></div>
                                    <div class="card-body">
                                        <form:form modelAttribute="institutionDTO">
                                            <div class="form-group">
                                                <label class="small mb-1" for="name">Name</label>
                                                <form:input class="form-control py-4" path="name" id="name" type="text" maxlength="255" />
                                            </div>
                                            <div class="form-group">
                                                <label class="small mb-1" for="description">Description</label>
                                                <form:textarea class="form-control" path="description" id="description" maxlength="255" rows="2"/>
                                            </div>
                                            <div class="form-group d-flex align-items-center justify-content-between mt-4 mb-0">
                                                <div class="input-group-append">
                                                    <button class="btn btn-primary" type="submit">
                                                        Add
                                                    </button>
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
                <%@include file="/WEB-INF/jspf/admin/footer.jspf"%>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="<c:url value="/resources/js/script_admin.js"/>"></script>
    </body>
</html>
