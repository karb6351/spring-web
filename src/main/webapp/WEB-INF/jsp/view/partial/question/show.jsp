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
                        <security:authorize access="hasRole('LECTURER')">
                            <div class="d-flex justify-content-end align-items-center" style="margin-top: 20px;padding:0 10px">
                                <a class="btn btn-success" href="<c:url value="/response/${question.id}/create"></c:url>">
                                    <i class="fas fa-plus-circle"></i>
                                    <span>Add response</span>
                                </a>
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
                                                        <input type="radio"
                                                               class="response"
                                                               name="optionsRadios"
                                                               id="response-${response.id}" value="${response.id}"
                                                               data-href="<c:url value="/vote"></c:url>"
                                                               data-question-id="${question.id}"
                                                                <c:forEach var="vote" items="${response.votes}">
                                                                    <c:if test="${vote.user.id == userId && vote.response.id == response.id}">
                                                                            checked
                                                                    </c:if>
                                                                </c:forEach>
                                                        >
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
                                        <security:authorize access="hasAnyRole('STUDENT', 'LECTURER')">
                                            <div class="d-flex justify-content-between align-items-center">
                                                <button class="btn btn-primary" id="vote">
                                                    <i class="fas fa-thumbs-up icon"></i>
                                                    <span>Vote</span>
                                                </button>
                                                <button class="btn btn-secondary"
                                                        id="vote-result-button"
                                                        <%--data-toggle="modal" --%>
                                                        <%--data-target="#result-modal">--%>
                                                    <i class="fas fa-poll-h"></i>
                                                    <span>Result</span>
                                                </button>
                                            </div>
                                        </security:authorize>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-12 col-sm-5">
                    <div class="card">
                        <div class="card-header d-flex justify-content-between align-items-center">
                            <h6>Comments</h6>
                            <security:authorize access="hasAnyRole('LECTURER', 'STUDENT')">
                                <a href="<c:url value="/question/comment/${question.id}"></c:url>"
                                    <%--data-toggle="modal"--%>
                                    <%--data-target="#comment-modal" --%>
                                   class="btn btn-success"
                                >
                                    <i class="fas fa-plus-circle icon"></i>
                                    <span>Add comment</span>
                                </a>
                            </security:authorize>
                        </div>
                        <div class="card-body">
                            <ul class="list-group">
                                <c:forEach items="${questionComments}" var="comment">
                                    <div class=" d-flex justify-content-between align-items-center">
                                        <a class="list-group-item">

                                            <i class="material-icons">face</i>
                                            <div class="bmd-list-group-col">
                                                <p class="list-group-item-heading">
                                                        ${comment.user.username}
                                                        <%--<span style="color: #e5e5e5; font-size: 12px; margin-left: 5px">--%>
                                                        <%--<fmt:formatDate value="${comment.createdAt}" pattern="yyyy-MM-dd HH:mm:ss" />--%>
                                                        <%--</span>--%>
                                                </p>
                                                <p class="list-group-item-text">${comment.content}</p>
                                            </div>
                                        </a>
                                        <div>
                                            <security:authorize access="hasRole('LECTURER')">
                                                <a href="<c:url value="/question/comment/edit/${comment.id}"></c:url>" class="btn btn-outline-info btn-sm">
                                                    <i class="fas fa-edit"></i>
                                                </a>
                                                <a href="#" data-id="${comment.id}" data-href="<c:url value="/question/comment/delete/${comment.id}"></c:url>" class="delete-button btn btn-outline-danger btn-sm">
                                                    <i class="fas fa-trash-alt"></i>
                                                </a>
                                                <form style="display: none" id="delete-form-${comment.id}" action="<c:url value="/question/comment/delete/${comment.id}"></c:url>" method="post">
                                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                                </form>
                                            </security:authorize>
                                        </div>
                                    </div>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>

                    <div class="modal fade" id="comment-modal" tabindex="-1" role="dialog" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLongTitle">Add Comment</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <textarea name="editor" id="editor"></textarea>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    <button type="button" class="btn btn-primary">Add</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="vote-wrapper" data-href="<c:url value="/vote/result/${question.id}"></c:url>"></div>

    </jsp:body>
</t:master>