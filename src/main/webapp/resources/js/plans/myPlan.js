let isItemMenuExitProcessing = false;

function itemMenuEdit(planNo) {
    if (isItemMenuExitProcessing) {
        return;
    }

    $(".myplanlist_item_plan_no").val(planNo);
    $("#myPlanForm").attr("method", "post").attr("action", "/plans/editPlan.trip").submit();
}

function itemMenuExit(planNo, leadYn) {
    if (isItemMenuExitProcessing) {
        return;
    }
    isItemMenuExitProcessing = true;

    checkPopUpOpen("플랜을 나가시겠습니까?");

    $(".check_popup_btn_cancel").attr("onclick", null).on('click', function () {
        $(".check_popup_wrap").hide();
        isItemMenuExitProcessing = false;
    });

    $(".check_popup_btn_ok").attr("onclick", null).on('click', function () {
        let planMateListLength;

        $.ajax({
            type: 'get',
            url: '/plans/planMate.trip',
            dataType: 'json',
            async: false,
            data: {
                planNo: planNo
            },
            success: function (result) {
                planMateListLength = result.planMateList.length;
            },
            error: function (error) {
                popUpOpen("처리 중 오류가 발생하였습니다.");
            }
        })

        $(".check_popup_wrap").hide();

        if (leadYn === 'Y' && planMateListLength > 1) {
            authorityPopUpOpen(planNo);
        } else {
            $.ajax({
                type: 'post',
                url: '/plans/exitPlan.trip',
                dataType: 'json',
                async: false,
                data: {
                    planNo: planNo,
                    memberNo: $("#memberNo").val()
                },
                success: function (result) {
                    if (result.isExitPlanMate === true) {
                        location.reload();
                    }
                },
                error: function (error) {
                    popUpOpen("처리 중 오류가 발생하였습니다.");
                }
            })
        }

        isItemMenuExitProcessing = false;
    });
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