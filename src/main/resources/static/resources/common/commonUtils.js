


function submitForm(formId, opurl, callback,modal) {
    // if(_ifTimeOut){
    //     return;
    // }
    if(modal === true) {
        showLodding();
    }
    var btn = null;
    var formOptions = {
        error : function(response) {
            if(modal === true) {
                destoryLoadding();
            }
            //handlerException(response);
        },
        success : function(data) {
            if(modal === true) {
                destoryLoadding();
            }
            if (callback != undefined && callback != "") {
                callback(data);
            }
        }, // post-submit callback
        url : opurl,
        type : 'post',
        dataType : 'json',
        async: false
    };
    $("#" + formId).ajaxForm(formOptions);
    $("#" + formId).submit();
}

function showLodding(msg) {
    var m = msg || "正在加载中，请稍后...";
    var str = '<div id="loadding_shade" style="filter:Alpha(opacity=60);display:none;position:fixed;left:0px;top:0px;width:100%;z-index:100;background:#ccc;opacity: 0.5;"></div>'+
        '<div id="loadding"  style="width:100px;height:100px;display:none;font-size:14px;position:fixed;padding:5px 10px;overflow:hidden;z-index:300;position:absolute;top:300px;left:600px;opacity:1;">'+
        '<img  height="93" src="/resources/images/loadding2.gif" /></div>';
    $("body").append(str);
    //$("#msgContext").html(m);
    var wh = $(window).height();
    var ww = $(window).width();
    var lw = $("#loadding").width();
    var lh = $("#loadding").height();
    var top = (wh-lh)/2;
    var left = (ww-lw)/2;
    $("#loadding_shade").css("height",$(window).height()).show();
    $("#loadding").css({
        "top":top,
        "left":left
    }).show();
}

function destoryLoadding() {
    $("#loadding_shade").remove();
    $("#loadding").remove();
}