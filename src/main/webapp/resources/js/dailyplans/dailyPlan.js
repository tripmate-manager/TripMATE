let isAjaxProcessing = false;

function deleteDailyPlan(dailyPlanNo) {
    checkPopUpOpen("해당 글을 데일리플랜에서 삭제하시겠습니까? (위시리스트에서는 삭제되지 않습니다.)");

    if (isAjaxProcessing) {
        popUpOpen('이전 요청을 처리중 입니다. 잠시 후 다시 시도하세요.');
        return;
    } else {
        isAjaxProcessing = true;
    }

    $(".check_popup_btn_ok").attr("onclick", null).on('click', function () {
        $.ajax({
            url: '/dailyPlans/deleteDailyPlan.trip',
            type: 'post',
            dataType: 'json',
            async: false,
            data: {
                memberNo: document.getElementById("memberNo").value,
                dailyPlanNo: dailyPlanNo
            },
            success: function (result) {
                isAjaxProcessing = false;
                $(".check_popup_wrap").hide();

                if (result.isDeleteDailyPlanSuccess === true) {
                    location.reload();
                } else {
                    popUpOpen(result.message);
                }
            },
            error: function (error) {
                isAjaxProcessing = false;
                popUpOpen("처리 중 오류가 발생하였습니다.");
            }
        })
    });
}