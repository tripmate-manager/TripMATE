window.onhashchange = function () {
    if (location.hash !== "#open") {
        $("#menu,.page_cover,html").removeClass("open");
    }
};

$(function () {
    $("#icon_menu_home").hide();
    $("#icon_menu_home_choice").show();

    $(".icon_arrow_left").on('click', function () {
        if (document.referrer === '') {
            history.back();
        } else {
            location.replace(document.referrer);
        }
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

    $("#bottom_menu_wishlist").on('click', function () {
        $("#planMainForm").attr("action", "/wishlist/wishlist.trip").submit();
    });
});