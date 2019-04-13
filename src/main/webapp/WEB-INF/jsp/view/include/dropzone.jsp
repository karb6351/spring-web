        <div class="modal fade" id="dropzone-modal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Materials upload</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div id="dropzone" class="dropzone"
                     data-href="<c:url value="/material/create"></c:url>"
                     data-lecture-id="${param.lectureId}"
                     data-partial-view-url="<c:url value="/material/resource/${param.lectureId}"></c:url>"
                >
                    <div class="dz-preview dz-file-preview">
                        <div class="dz-details">
                            <div class="dz-filename"><span data-dz-name></span></div>
                            <div class="dz-size" data-dz-size></div>
                            <img data-dz-thumbnail />
                        </div>
                        <%--<div class="dz-progress"><span class="dz-upload" data-dz-uploadprogress></span></div>--%>
                        <div class="dz-success-mark"><span>✔</span></div>
                        <div class="dz-error-mark"><span>✘</span></div>
                        <div class="dz-error-message"><span data-dz-errormessage></span></div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">

                <button type="button" class="btn btn-secondary"  data-dismiss="modal">
                    <i class="fas fa-times icon"></i>
                    <span>Close</span>
                </button>
                <button type="button" class="btn btn-primary" id="upload-button">
                    <i class="fas fa-upload icon"></i>
                    <span>Upload</span>
                </button>
            </div>
        </div>
    </div>
</div>