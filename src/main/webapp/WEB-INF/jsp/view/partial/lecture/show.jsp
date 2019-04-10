<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:master>
    <jsp:body>
        <jsp:include page="../../include/dropzone.jsp">
            <jsp:param name="lectureId" value="${lecture.id}"/>
        </jsp:include>
        <div class="container">
            <div class="row content">
                <div class="col-12 col-sm-7">
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
                            <jsp:include page="./material_table.jsp">
                                <jsp:param name="lecture" value="${lecture}"/>
                            </jsp:include>
                        </div>
                    </div>
                </div>
                <div class="col-12 col-sm-5">
                    <jsp:include page="../comment/index.jsp" />
                </div>
            </div>
        </div>
    </jsp:body>
</t:master>