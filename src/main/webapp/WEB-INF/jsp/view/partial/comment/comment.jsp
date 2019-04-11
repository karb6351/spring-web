<div class="card">
    <div class="card-header d-flex justify-content-between align-items-center">
        <h6>Comments</h6>
        <security:authorize access="hasAnyRole('LECTURER', 'STUDENT')">
            <button data-toggle="modal" data-target="#comment-modal" class="btn btn-success">
                <i class="fas fa-plus-circle icon"></i>
                <span>Add comment</span>
            </button>
        </security:authorize>
    </div>
    <div class="card-body">
        <ul class="list-group">
            <a class="list-group-item">
                <i class="material-icons">face</i>
                <div class="bmd-list-group-col">
                    <p class="list-group-item-heading">Username</p>
                    <p class="list-group-item-text">comment...</p>
                </div>
            </a>

            <a class="list-group-item">
                <i class="material-icons">face</i>
                <div class="bmd-list-group-col">
                    <p class="list-group-item-heading">Username</p>
                    <p class="list-group-item-text">comment...</p>
                </div>
            </a>
            <a class="list-group-item">
                <i class="material-icons">face</i>
                <div class="bmd-list-group-col">
                    <p class="list-group-item-heading">Username</p>
                    <p class="list-group-item-text">comment...</p>
                </div>
            </a>
        </ul>
    </div>
</div>

<div class="modal fade" id="comment-modal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Add Comment</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <textarea name="editor" id="editor"></textarea>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Add</button>
            </div>
        </div>
    </div>
</div>