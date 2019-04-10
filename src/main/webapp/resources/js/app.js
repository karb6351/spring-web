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
            autoProcessQueue: false,
            // addRemoveLinks: true,
            uploadMultiple: true,
            headers: {
                'X-CSRF-TOKEN': $('meta[name="_csrf"]').attr('content')
            },
            params: {
                'lectureId': $('#dropzone').data('lectureId')
            },
            init: function(){
                var _this = this;
                this.on("completemultiple", function(files){
                    // window.location.reload();
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

function initSelected2(){
    $('.selected2').select2();
}

Dropzone.autoDiscover = false;
$(document).ready(function(){
    initDeleteButton();
    initSelected2();
    initDropzone();
});