<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:master>
    <jsp:body>
        <div class="container">
            <div class="container">
                <div class="register-login-container">
                    <div class="card">
                        <div class="card-header form-header">User</div>
                        <div class="card-body">
                            <form:form modelAttribute="model" method="post">
                                <div class="form-group">
                                    <form:label path="username">Username</form:label>
                                    <form:input cssClass="form-control" path="username" />
                                </div>
                                <c:if test="${isCreate}">
                                    <div class="form-group">
                                        <form:label path="password">Password</form:label>
                                        <form:input cssClass="form-control" path="password" />
                                    </div>
                                </c:if>
                                <div class="form-group">
                                    <label>Roles</label>
                                    <select name="roles[]" multiple="true" class="form-control selected2">
                                        <c:forEach var="roleListItem" items="${roleList}">
                                            <option value="${roleListItem.role}"
                                                    <c:forEach items="${userRoles}" var="userRole">
                                                        <c:if test="${roleListItem.role.equals(userRole.role)}" >selected</c:if>
                                                    </c:forEach>
                                            >${roleListItem.role}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <button type="submit" class="btn btn-outline-success btn-block">Edit</button>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </jsp:body>
</t:master>