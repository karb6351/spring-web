<c:if test="${error != null}">
    <div class="alert alert-danger" style="margin-top: 30px" role="alert">
        ${error}
    </div>
</c:if>

<c:if test="${success != null}">
    <div class="alert alert-success" style="margin-top: 30px" role="alert">
            ${success}
    </div>
</c:if>