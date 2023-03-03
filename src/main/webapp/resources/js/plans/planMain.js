window.onhashchange = function () {
    if (location.hash !== "#open") {
        $("#menu,.page_cover,html").removeClass("open");
    }
};

$(function () {
    $(".icon_arrow_left").on('click', function () {
        $("#planMainForm").attr("action", "/plans/myPlan.trip").submit();
    });

    $(".plan_main_plan_edit").on('click', function () {
        $("#planMainForm").attr("method", "post").attr("action", "/plans/editPlan.trip").submit();
    });

    $(".icon_plan_mate").click(function () {
        $("#menu,.page_cover,html").addClass("open");
        window.location.hash = "#open";
    })

    $("#menu ul.sub_mobile").hide();
    $("#menu ul.nav li").click(function () {
        $("ul", this).slideToggle("fast");
    })
});