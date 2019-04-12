<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:master>
    <jsp:body>
        <div class="container">
            <jsp:include page="../../include/flash_message.jsp" />
            <div class="row content">
                <div class="col-12 col-sm-7">
                    <div class="card">
                        <div class="card-header d-flex justify-content-between align-items-center">
                            <span>${question.question}</span>
                            <security:authorize access="hasRole('LECTURER')">
                                <div>
                                    <a href="<c:url value="/question/edit/${question.id}"></c:url>" class="btn btn-outline-info btn-sm">
                                        <i class="fas fa-edit icon"></i>
                                        <span>Edit</span>
                                    </a>
                                    <a href="#" data-id="${question.id}" data-href="<c:url value="/question/delete/${question.id}"></c:url>" class="delete-button btn btn-outline-danger btn-sm">
                                        <i class="fas fa-trash-alt icon"></i>
                                        <span>Delete</span>
                                    </a>
                                    <form style="display: none" id="delete-form-${question.id}" action="<c:url value="/material/delete/${question.id}"></c:url>" method="post">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    </form>
                                </div>
                            </security:authorize>
                        </div>
                        <security:authorize access="hasAnyRole('LECTURER', 'STUDENT')">
                            <div class="d-flex justify-content-between align-items-center" style="margin-top: 20px;padding:0 10px">
                                <button class="btn btn-info" data-toggle="modal" data-target="#result-modal">
                                    <i class="fas fa-poll-h"></i>
                                    <span>Result</span>
                                </button>
                                <security:authorize access="hasRole('LECTURER')">
                                <a class="btn btn-success" href="<c:url value="/response/${question.id}/create"></c:url>">
                                    <i class="fas fa-plus-circle"></i>
                                    <span>Add response</span>
                                </a>
                                </security:authorize>
                            </div>
                        </security:authorize>
                        <div id="wrapper">
                            <div class="card-body" style="margin-top: -20px;">
                                <c:choose>
                                    <c:when test="${fn:length(question.responses) == 0}">
                                        <div class="alert alert-warning" style="padding-top: 10px" role="alert">
                                            No responses in this question.
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach items="${question.responses}" var="response">
                                            <div class="form-group">
                                                <div class="radio d-flex justify-content-between align-items-start">
                                                    <label style="margin-right: 20px;">
                                                        <input type="radio" name="optionsRadios" id="response-${response.id}" value="${response.id}" checked>
                                                        <span style="">${response.response}</span>
                                                    </label>
                                                    <security:authorize access="hasRole('LECTURER')">
                                                        <div class="action-group">
                                                            <a href="<c:url value="/response/${question.id}/edit/${response.id}"></c:url>" class="btn btn-outline-info btn-sm">
                                                                <i class="fas fa-edit icon"></i>
                                                                <span>Edit</span>
                                                            </a>
                                                            <a href="#" data-id="${response.id}" data-href="<c:url value="/response/delete/${response.id}"></c:url>" class="delete-button btn btn-outline-danger btn-sm">
                                                                <i class="fas fa-trash-alt icon"></i>
                                                                <span>Delete</span>
                                                            </a>
                                                            <form style="display: none" id="delete-form-${response.id}" action="<c:url value="/response/delete/${response.id}"></c:url>" method="post">
                                                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                                            </form>
                                                        </div>
                                                    </security:authorize>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-12 col-sm-5">
                    <jsp:include page="../comment/comment.jsp" />
                </div>
            </div>
        </div>

        <div class="modal fade result-modal" tabindex="-1" role="dialog" id="result-modal">
            <div class="modal-dialog modal-lg modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Vote result</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        test
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

    </jsp:body>
</t:master>