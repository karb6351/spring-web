<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

<script>
    $('.delete-button').click(function(){

        var id = $(this).data("id");

        var swalObject = {
            title: 'Are you sure?',
            text: 'Are you sure to delete?',
            icon: 'warning',
            dangerMode: true,
            buttons: true,
        };

        // axios.defaults.headers.common[$("meta[name='_csrf_header']").attr("content")] = $('meta[name="_csrf"]').attr("content");

        swal(swalObject)
            .then(function (willDelete) {
                if (willDelete) {
                    $("#delete-form-" + id).submit();
                };
            })
            .catch(function(error){
                console.error(error);
            });
    });
</script>