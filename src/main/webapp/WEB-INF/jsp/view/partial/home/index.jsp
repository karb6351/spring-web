<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:master>
    <jsp:body>
        <div class="container">
            <div class="row content">
                <div class="col-sm-12 col-md-7 col-7">
                    <div class="card">
                        <div class="card-header d-flex justify-content-between">
                            <h6>Lectures</h6>
                            <security:authorize access="hasRole('LECTURER')">
                                <a href="<c:url value="/lecture/create"></c:url>" class="btn btn-success">
                                    <i class="fas fa-plus-circle icon"></i>
                                    <span>Create</span>
                                </a>
                            </security:authorize>
                        </div>
                        <%--<div class="card-body">--%>
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Name</th>
                                        <security:authorize access="hasRole('LECTURER')">
                                            <th>Action</th>
                                        </security:authorize>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>test</td>
                                        <security:authorize access="hasRole('LECTURER')">
                                            <td>
                                                <a href="<c:url value="/lecture/edit/"></c:url>" class="btn btn-outline-info btn-sm">
                                                    <i class="fas fa-edit icon"></i>
                                                    <span>Edit</span>
                                                </a>
                                                <a href="#" data-href="<c:url value="/lecture/edelet"></c:url>" class="delete-button btn btn-outline-danger btn-sm">
                                                    <i class="fas fa-trash-alt icon"></i>
                                                    <span>Delete</span>
                                                </a>
                                            </td>
                                        </security:authorize>
                                    </tr>
                                </tbody>
                            </table>
                        <%--</div>--%>
                    </div>
                </div>
                <div class="col-sm-12 col-md-5 col-5">
                    <div class="card">
                        <div class="card-header d-flex justify-content-between">
                            <h6>MC polls</h6>
                            <security:authorize access="hasRole('LECTURER')">
                                <a href="<c:url value="/mc/create"></c:url>" class="btn btn-success">
                                    <i class="fas fa-plus-circle icon"></i>
                                    <span>Create</span>
                                </a>
                            </security:authorize>
                        </div>
                        <%--<div class="card-body">--%>
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th>Question</th>
                                    <security:authorize access="hasRole('LECTURER')">
                                        <th>Action</th>
                                    </security:authorize>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>test</td>
                                    <security:authorize access="hasRole('LECTURER')">
                                        <td>
                                            <a href="<c:url value="/mc/edit/"></c:url>" class="btn btn-outline-info btn-sm">
                                                <i class="fas fa-edit icon"></i>
                                                <span>Edit</span>
                                            </a>
                                            <a href="#" data-href="<c:url value="/mc/delete"></c:url>" class="delete-button btn btn-outline-danger btn-sm">
                                                <i class="fas fa-trash-alt icon"></i>
                                                <span>Delete</span>
                                            </a>
                                        </td>
                                    </security:authorize>
                                </tr>
                                </tbody>
                            </table>
                        <%--</div>--%>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:master>