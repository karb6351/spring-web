<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:master>
    <jsp:body>
        <div class="container register-login-container">
            <div class="container">
                <div class="register-login-container">
                    <c:if test="${param.error != null}">
                        <div class="alert alert-danger" style="margin-top: 30px" role="alert">
                            Login failed.
                        </div>
                    </c:if>
                    <div class="card">
                        <div class="card-header form-header">Login</div>
                        <div class="card-body">
                            <form:form modelAttribute="model" method="post">
                                <div class="form-group">
                                    <form:label path="username">Username</form:label>
                                    <form:input cssClass="form-control" path="username"></form:input>
                                </div>
                                <div class="form-group">
                                    <form:label path="password">Password</form:label>
                                    <form:password cssClass="form-control" path="password"></form:password>
                                </div>
                                <button type="submit" class="btn btn-outline-success btn-block">Login</button>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:master>