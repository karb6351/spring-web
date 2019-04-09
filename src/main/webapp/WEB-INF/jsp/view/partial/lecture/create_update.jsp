<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:master>
    <jsp:body>
        <div class="container">
            <div class="card">
                <div class="card-body">
                    <a href="<c:url value="/" ></c:url>" class="btn btn-secondary">
                        <i class="fas fa-arrow-left icon"></i>
                        <span>Back to Index</span>
                    </a>
                </div>
            </div>
            <div class="card content">
                <form:form modelAttribute="form" action="POST">
                    <%--<div class="form-group">--%>
                        <%----%>
                    <%--</div>--%>
                </form:form>
            </div>
        </div>
    </jsp:body>
</t:master>