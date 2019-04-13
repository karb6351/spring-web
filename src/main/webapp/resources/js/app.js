function initDeleteButton(){
    $('.delete-button').click(function(){

        var id = $(this).data("id");

        var swalObject = {
            title: 'Are you sure?',
            text: 'Are you sure to delete?',
            icon: 'warning',
            dangerMode: true,
            buttons: true,
        };

        var _this = this;

        swal(swalObject)
            .then(function (willDelete) {
                if (willDelete) {
                    $(_this).next("form#delete-form-" + id).submit();
                    // $("#delete-form-" + id).submit();
                };
            })
            .catch(function(error){
                console.error(error);
            });
    });
}

function getHostMeta(){
    var path = $(this).data("href");
    var domain = window.location.hostname;
    var protocol = window.location.protocol;
    var port = window.location.port;
    return {
        path: path,
        domain: domain,
        protocol: protocol,
        port: port
    }
}

function urlBuilder(hostObj, path){
    return hostObj.protocol + '//' + hostObj.domain + ((typeof hostObj.port !== "undefined") ? ":" + hostObj.port : '') +  path;
}

function initDropzone(){
    if ($('#dropzone').length !== 0){
        var dz = $('#dropzone').dropzone({
            url: urlBuilder(getHostMeta(), $('#dropzone').data("href")),
            paramName: 'file',
            method: 'post',
            maxFiles: 100,
            autoProcessQueue: false,
            // addRemoveLinks: true,
            uploadMultiple: true,
            parallelUploads: 100,
            headers: {
                'X-CSRF-TOKEN': $('meta[name="_csrf"]').attr('content')
            },
            params: {
                'lectureId': $('#dropzone').data('lectureId')
            },
            init: function(){
                var _this = this;
                this.on("completemultiple", function(files){
                    $("#wrapper").load($('#dropzone').data("partialViewUrl"), function() {
                        $('#dropzone-modal').modal('hide');
                    });
                });
                $('#upload-button').click(function(){
                    _this.processQueue();
                })
            }
        });

    }
}

function initEditor(){
    if(document.querySelector( '#editor' )){
        ClassicEditor
            .create( document.querySelector( '#editor' ), {
                toolbar: [ 'heading', '|', 'bold', 'italic', 'link', 'bulletedList', 'numberedList', 'blockQuote' ],
                heading: {
                    options: [
                        { model: 'paragraph', title: 'Paragraph', class: 'ck-heading_paragraph' },
                        { model: 'heading1', view: 'h1', title: 'Heading 1', class: 'ck-heading_heading1' },
                        { model: 'heading2', view: 'h2', title: 'Heading 2', class: 'ck-heading_heading2' }
                    ]
                }
            });
    }
}

function voteHandler(){
    $('#vote').click(function(){
        var target = $('input:checked.response');
        if (target.length !== 0){
            var responseId = target.val();
            var url = target.data("href");
            var questionId = target.data("questionId");
            axios.post(url, {
                responseId: responseId,
                questionId: questionId
            })
                .then(function(response){
                    console.log(response);
                })
                .catch(function(error){
                    console.log(error);
                })

        }
    });
}

function fetchVoteResult(){
    $('#vote-result-button').on('click', function(){
        var target = $('#vote-wrapper');
        var url = target.data("href");
        if (target){
            target.load(url, function() {
                $('#result-modal').modal('show');
            });
        }
    });
}

function initSelected2(){
    $('.selected2').select2();
}

Dropzone.autoDiscover = false;
$(document).ready(function(){
    axios.defaults.headers.common[$("meta[name='_csrf_header']").attr("content")] = $('meta[name="_csrf"]').attr("content");
    initDeleteButton();
    initSelected2();
    initDropzone();
    initEditor();
    voteHandler();
    fetchVoteResult()
});