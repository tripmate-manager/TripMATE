let isAjaxProcessing = false;

function deleteDailyPlan(dailyPlanNo) {
    checkPopUpOpen("해당 글을 데일리플랜에서 삭제하시겠습니까? (위시리스트에서는 삭제되지 않습니다.)");

    $(".check_popup_btn_ok").attr("onclick", null).on('click', function () {
        if (isAjaxProcessing) {
            popUpOpen('이전 요청을 처리중 입니다. 잠시 후 다시 시도하세요.');
            return;
        } else {
            isAjaxProcessing = true;
        }

        $.ajax({
            url: '/dailyPlans/deleteDailyPlan.trip',
            type: 'post',
            dataType: 'json',
            async: false,
            data: {
                memberNo: document.getElementById("member_no").value,
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

    $(".check_popup_btn_cancel").attr("onclick", null).on('click', function () {
        $(".check_popup_wrap").hide();
    });
}

function createDailyPlanNotification(planNo, dailyPlanNo, dailyPlanDateTime) {
    dailyPlanNotificationPopUpOpen(planNo, dailyPlanNo, document.getElementById("memberNo").value, dailyPlanDateTime, constCode.global.N);
}

function viewReviewList(dailyPlanNo, postTypeCode) {
    document.getElementById("dailyplan_no").value = dailyPlanNo;
    document.getElementById("dailyplan_post_type_code").value = postTypeCode;

    $("#dailyplanForm").attr("action", "/review/reviewList.trip").submit();
}

function deleteDailyPlanNotification(dailyPlanNo) {
    checkPopUpOpen("해당 데일리플랜의 알림을 삭제하시겠습니까?");

    $(".check_popup_btn_ok").attr("onclick", null).on('click', function () {
        if (isAjaxProcessing) {
            popUpOpen('이전 요청을 처리중 입니다. 잠시 후 다시 시도하세요.');
            return;
        } else {
            isAjaxProcessing = true;
        }

        $.ajax({
            url: '/dailyPlans/deleteDailyPlanNotification.trip',
            type: 'post',
            dataType: 'json',
            async: false,
            data: {
                dailyPlanNo: dailyPlanNo,
                memberNo: document.getElementById("member_no").value
            },
            success: function (result) {
                isAjaxProcessing = false;
                $(".check_popup_wrap").hide();

                if (result.isDeleteNotificationSuccess === true) {
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

    $(".check_popup_btn_cancel").attr("onclick", null).on('click', function () {
        $(".check_popup_wrap").hide();
    });
}

function updateDailyPlanNotification(planNo, dailyPlanNo, dailyPlanDateTime) {
    dailyPlanNotificationPopUpOpen(planNo, dailyPlanNo, document.getElementById("member_no").value, dailyPlanDateTime, constCode.global.Y);
}

function createReview(dailyPlanNo, postTypeCode) {
    document.getElementById("dailyplan_no").value = dailyPlanNo;
    document.getElementById("dailyplan_post_type_code").value = postTypeCode;
    $("#dailyplanForm").attr("action", "/review/createReview.trip").submit();
}

$(function () {
    let inputMemberNo = document.getElementById("member_no");

    if (document.getElementById("session_member_no") !== null) {
        inputMemberNo.value = document.getElementById("session_member_no").innerText;
    } else {
        inputMemberNo.value = 0;
    }

    $(".icon_arrow_left").on('click', function () {
        $("#dailyplanForm").attr("action", "/plans/planMain.trip").submit();
    });
});