window.onhashchange = function () {
    if (location.hash !== "#open") {
        $("#menu,.page_cover,html").removeClass("open");
    }
};

$(function () {
    const sessionMemberInfo = $("#memberInfo").val();

    if (sessionMemberInfo) {
        $(".icon_alarm_wrap").show();
    }

    $(".main_menu_button").click(function () {
        $("#menu,.page_cover,html").addClass("open");
        window.location.hash = "#open";
    })

    $("#menu ul.sub_mobile").hide();
    $("#menu ul.nav li").click(function () {
        $("ul", this).slideToggle("fast");
    })
});
