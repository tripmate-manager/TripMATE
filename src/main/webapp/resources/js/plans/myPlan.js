function itemMenuEdit(planNo) {
    $(".myplanlist_item_plan_no").val(planNo);
    $("#myPlanForm").attr("method", "post").attr("action", "/plans/editPlan.trip").submit();
}

function itemMenuExit(planNo) {
}

$(function () {
    $(".icon_arrow_left").on('click', function () {
        $("#myPlanForm").attr("action", "/main/main.trip").submit();
    });

    $(".icon_plus_rectangle, .myplanlist_empty_item_wrap").on('click', function () {
        $("#myPlanForm").attr("action", "/plans/createPlan.trip").submit();
    });

    $(".myplanlist_item_contents_wrap").on('click', function () {
        const planNo = $(this).find("#myplanList_item_no").attr("value").toString();
        $(".myplanlist_item_plan_no").val(planNo);
        $("#planNoForm").attr("action", "/plans/planMain.trip").submit();
    });
});