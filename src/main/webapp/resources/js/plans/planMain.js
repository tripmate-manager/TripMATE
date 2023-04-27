window.onhashchange = function () {
    if (location.hash !== "#open") {
        $("#menu,.page_cover,html").removeClass("open");
    }
};

$(function () {
    $("#icon_menu_home").hide();
    $("#icon_menu_home_choice").show();

    $(".icon_arrow_left").on('click', function () {
        $("#planMainForm").attr("method", "get").attr("action", "/main/main.trip").submit();
    });

    $(".plan_main_plan_edit").on('click', function () {
        $("#planMainForm").attr("action", "/plans/editPlan.trip").submit();
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

    $("#bottom_menu_checklist").on('click', function () {
        $("#planMainForm").attr("action", "/checkList/checkList.trip").submit();
    });

    $("#bottom_menu_account").on('click', function () {
        $("#planMainForm").attr("action", "/accountBook/accountBook.trip").submit();
    });

    $(".icon_arrow_right").on('click', function () {
        $("#dailyplan_day").val($(this).parent().find(".dailyplan_item_day").attr("value"));
        $("#planMainForm").attr("action", "/dailyPlans/dailyPlan.trip").submit();
    });
});