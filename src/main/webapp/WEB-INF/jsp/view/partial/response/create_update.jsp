<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:master>
    <jsp:body>
        <div class="container">
            <div class="row content">
                <div class="col-12 col-sm-6 offset-sm-3">
                    <div class="card">
                        <div class="card-header">
                            <h6>
                                <c:choose>
                                    <c:when test="${isCreate}">
                                        Create
                                    </c:when>
                                    <c:otherwise>
                                        Update
                                    </c:otherwise>
                                </c:choose>
                            </h6>
                        </div>
                        <div class="card-body">
                            <form:form modelAttribute="model" method="POST">
                                <div class="form-group">
                                    <form:label path="response">Name</form:label>
                                    <form:input path="response" cssClass="form-control" />
                                </div>
                                <input type="hidden" name="lectureId" value="${lectureId}">
                                <button class="btn btn-outline-success btn-block">
                                    <c:choose>
                                        <c:when test="${isCreate}">
                                            <i class="fas fa-plus icon"></i>
                                            <span>Create</span>
                                        </c:when>
                                        <c:otherwise>
                                            <i class="fas fa-pencil-alt icon"></i>
                                            <span>Update</span>
                                        </c:otherwise>
                                    </c:choose>
                                </button>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:master>