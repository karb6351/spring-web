<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:master>
    <jsp:body>
        <%--<%@ include file="../../include/dropzone.jsp" %>--%>
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
                    <jsp:include page="../comment/comment.jsp" />
                </div>
            </div>
        </div>
    </jsp:body>
</t:master>