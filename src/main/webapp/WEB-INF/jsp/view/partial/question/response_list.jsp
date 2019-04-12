<div class="card-body">
    <c:choose>
        <c:when test="${fn:length(param.responses) == 0}">
            <div class="card-body">
                <div class="alert alert-warning" role="alert">
                    No responses in this question.
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <c:forEach items="${param.responses}" var="response">
                <div class="form-group">
                    <div class="radio d-flex justify-content-between align-items-center">
                        <label>
                            <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked>
                            Option one is this and that&mdash;be sure to include why it's great
                        </label>
                        <security:authorize access="hasRole('LECTURER')">
                            <div>
                                <a href="<c:url value="/response/edit/${response.id}"></c:url>" class="btn btn-outline-info btn-sm">
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