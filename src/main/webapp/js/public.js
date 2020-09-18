$(function () {
    var $main_content = $("#fill-main-content");
    $("#main-menu-list a").on('click',function (e) {
        var url = $(this).attr('href');
        var href = url.substring(1);
        if(href.length!=""){
            $main_content.html(loadHtmlPage(href));
            $(".active").removeClass("active");
            $(this).parent().addClass("active");
        }
    });
})

function loadHtmlPage(path) {

    path = "/" + path;
    var result;
    $.ajax({
        url: path,
        dataType: "text",
        async: false,
        success: function(data) {
            result = data;
        }
    });
    return result;
};