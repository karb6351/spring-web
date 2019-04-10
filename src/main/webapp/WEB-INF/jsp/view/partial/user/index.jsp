<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:master>
    <jsp:body>
        <div class="container">
            <div class="row content">
                <c:choose>
                    <c:when test="${fn:length(users) == 0}">
                        <div class="alert alert-secondary" role="alert">
                            There are no users in the system.
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="col-sm-12">
                            <div class="card">
                                <div class="card-header d-flex justify-content-between">
                                    <h6>User</h6>
                                </div>
                                <table class="table table-hover">
                                    <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th>Role</th>
                                        <security:authorize access="hasRole('LECTURER')">
                                            <th>Action</th>
                                        </security:authorize>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${users}" var="user">
                                        <tr>
                                            <td>${user.username}</td>
                                            <td>
                                                <c:forEach items="${user.userRoles}" var="role">
                                                    <span class="badge badge-pill badge-dark" style="margin: 0 5px">${fn:toLowerCase(fn:replace(role.role, "ROLE_", ""))}</span>
                                                </c:forEach>
                                            </td>
                                            <security:authorize access="hasRole('LECTURER')">
                                                <td>
                                                    <a href="<c:url value="/user/edit/${user.id}"></c:url>" class="btn btn-outline-info btn-sm">
                                                        <i class="fas fa-edit icon"></i>
                                                        <span>Edit</span>
                                                    </a>
                                                    <a href="#" data-id="${user.id}" data-href="<c:url value="/user/delete/${user.id}"></c:url>" class="delete-button btn btn-outline-danger btn-sm">
                                                        <i class="fas fa-trash-alt icon"></i>
                                                        <span>Delete</span>
                                                    </a>
                                                    <form style="display: none" id="delete-form-${user.id}" action="<c:url value="/user/delete/${user.id}"></c:url>" method="post">
                                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                                    </form>
                                                </td>
                                            </security:authorize>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </jsp:body>
</t:master>