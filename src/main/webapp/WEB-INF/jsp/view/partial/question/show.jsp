<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:master>
    <jsp:body>
        <div class="container">
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
                        <div id="wrapper">

                        </div>
                    </div>
                </div>
                <div class="col-12 col-sm-5">
                    <jsp:include page="../comment/comment.jsp" />
                </div>
            </div>
        </div>
    </jsp:body>
</t:master>