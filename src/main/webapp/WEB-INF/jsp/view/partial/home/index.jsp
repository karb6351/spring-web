<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:master>
    <jsp:body>
        <div class="container">
            <div class="row content">
                <div class="col-sm-12 col-md-7 col-7">
                    <div class="card">
                        <div class="card-header d-flex justify-content-between align-items-center">
                            <h6>Lectures</h6>
                            <security:authorize access="hasRole('LECTURER')">
                                <a href="<c:url value="/lecture/create"></c:url>" class="btn btn-success">
                                    <i class="fas fa-plus-circle icon"></i>
                                    <span>Create</span>
                                </a>
                            </security:authorize>
                        </div>
                        <c:choose>
                            <c:when test="${fn:length(lectures) == 0}">
                                <div class="card-body">
                                    <div class="alert alert-warning" role="alert">
                                        No lecture in system.
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <table class="table table-hover">
                                    <thead>
                                    <tr>
                                        <th>Title</th>
                                        <security:authorize access="hasRole('LECTURER')">
                                            <th>Action</th>
                                        </security:authorize>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="lecture" items="${lectures}">
                                        <tr>
                                            <td>
                                                <a href="<c:url value="/lecture/${lecture.id}"></c:url>">${lecture.name}</a>
                                            </td>
                                            <security:authorize access="hasRole('LECTURER')">

                                                <td>
                                                    <a href="<c:url value="/lecture/edit/${lecture.id}"></c:url>" class="btn btn-outline-info btn-sm">
                                                        <i class="fas fa-edit icon"></i>
                                                        <span>Edit</span>
                                                    </a>
                                                    <a href="#" data-id="${lecture.id}" data-href="<c:url value="/lecture/delete/${lecture.id}"></c:url>" class="delete-button btn btn-outline-danger btn-sm">
                                                        <i class="fas fa-trash-alt icon"></i>
                                                        <span>Delete</span>
                                                    </a>
                                                    <form style="display: none" id="delete-form-${lecture.id}" action="<c:url value="/lecture/delete/${lecture.id}"></c:url>" method="post">
                                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                                    </form>
                                                </td>
                                            </security:authorize>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="col-sm-12 col-md-5 col-5">
                    <div class="card">
                        <div class="card-header d-flex justify-content-between align-items-center">
                            <h6>MC polls</h6>
                            <security:authorize access="hasRole('LECTURER')">
                                <a href="<c:url value="/question/create"></c:url>" class="btn btn-success">
                                    <i class="fas fa-plus-circle icon"></i>
                                    <span>Create</span>
                                </a>
                            </security:authorize>
                        </div>
                        <c:choose>
                            <c:when test="${fn:length(questions) == 0}">
                                <div class="card-body">
                                    <div class="alert alert-warning" role="alert">
                                        No question in system.
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <table class="table table-hover">
                                    <thead>
                                    <tr>
                                        <th width="50%">Question</th>
                                        <security:authorize access="hasRole('LECTURER')">
                                            <th>Action</th>
                                        </security:authorize>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="question" items="${questions}">
                                        <tr>
                                            <td><a href="<c:url value="/question/${question.id}"></c:url>">${question.question}</a></td>
                                            <security:authorize access="hasRole('LECTURER')">
                                                <td>
                                                    <a href="<c:url value="/question/edit/${question.id}"></c:url>" class="btn btn-outline-info btn-sm">
                                                        <i class="fas fa-edit icon"></i>
                                                        <span>Edit</span>
                                                    </a>
                                                    <a href="#" data-id="${question.id}" data-href="<c:url value="/question/delete/${question.id}"></c:url>" class="delete-button btn btn-outline-danger btn-sm">
                                                        <i class="fas fa-trash-alt icon"></i>
                                                        <span>Delete</span>
                                                    </a>
                                                    <form style="display: none" id="delete-form-${material.id}" action="<c:url value="/question/delete/${question.id}"></c:url>" method="post">
                                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                                    </form>
                                                </td>
                                            </security:authorize>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:master>