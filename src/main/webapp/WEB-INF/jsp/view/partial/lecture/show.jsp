<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:master>
    <jsp:body>
        <jsp:include page="../../include/dropzone.jsp">
            <jsp:param name="lectureId" value="${lecture.id}"/>
        </jsp:include>
        <div class="container">
            <div class="row content">
                <div class="col-sm-7 col-12">
                    <div class="card">
                        <div class="card-header d-flex justify-content-between align-items-center">
                            <span>${lecture.name}</span>
                            <security:authorize access="hasRole('LECTURER')">
                                <button data-toggle="modal" data-target="#dropzone-modal" class="btn btn-success">
                                    <i class="fas fa-plus-circle icon"></i>
                                    <span>Upload material</span>
                                </button>
                            </security:authorize>
                        </div>
                        <div id="wrapper">
                            <c:choose>
                                <c:when test="${fn:length(lecture.materials) <= 0}">
                                    <div class="card-body">
                                        <div class="alert alert-secondary" role="alert">No material in this lecture</div>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <table class="table table-hover">
                                        <thead>
                                        <tr>
                                            <th>Filename</th>
                                            <th>Action</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${lecture.materials}" var="material">
                                            <tr>
                                                <td>${material.filename}</td>
                                                <td>
                                                    <a target="_blank" href="<c:url value="/material/download/${material.id}"></c:url>" class="btn btn-outline-secondary  btn-sm">
                                                        <i class="fas fa-download icon"></i>
                                                        <span>Download</span>
                                                    </a>
                                                    <security:authorize access="hasRole('LECTURER')">
                                                        <a href="#" data-id="${material.id}" data-href="<c:url value="/material/delete/${material.id}"></c:url>" class="delete-button btn btn-outline-danger btn-sm">
                                                            <i class="fas fa-trash-alt icon"></i>
                                                            <span>Delete</span>
                                                        </a>
                                                        <form style="display: none" id="delete-form-${material.id}" action="<c:url value="/material/delete/${material.id}"></c:url>" method="post">
                                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                                        </form>
                                                    </security:authorize>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
                <div class="col-sm-5 col-12">
                    <div class="card">
                        <div class="card-header d-flex justify-content-between align-items-center">
                            <h6>Comments</h6>
                            <security:authorize access="hasAnyRole('LECTURER', 'STUDENT')">
                                <a href="<c:url value="/lecture/comment/${lecture.id}"></c:url>"
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
                                <c:forEach items="${lectureComments}" var="comment">
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
                                                <a href="<c:url value="/lecture/comment/edit/${comment.id}"></c:url>" class="btn btn-outline-info btn-sm">
                                                    <i class="fas fa-edit"></i>
                                                </a>
                                                <a href="#" data-id="${comment.id}" data-href="<c:url value="/lecture/comment/delete/${comment.id}"></c:url>" class="delete-button btn btn-outline-danger btn-sm">
                                                    <i class="fas fa-trash-alt"></i>
                                                </a>
                                                <form style="display: none" id="delete-form-${comment.id}" action="<c:url value="/lecture/comment/delete/${comment.id}"></c:url>" method="post">
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
    </jsp:body>
</t:master>