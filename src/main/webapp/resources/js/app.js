function initDeleteButton(){
    $('.delete-button').click(function(){
        var path = $(this).data("href");
        var domain = window.location.hostname;
        var protocol = window.location.protocol;
        var port = window.location.port;

        var url = protocol + '//' + domain + ((typeof port !== "undefined") ? ":" + port : '') +  path;

        console.log(url);

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

function initSelected2(){
    $('.selected2').select2();
}
$(document).ready(function(){
    initDeleteButton();
    initSelected2();
});