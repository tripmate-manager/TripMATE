$(function () {
    $(".icon_plus_rectangle, .myplanlist_empty_item_wrap").on('click', function () {
        $("#myPlanForm").attr("action", "/plans/createPlan.trip").submit();
    });
});