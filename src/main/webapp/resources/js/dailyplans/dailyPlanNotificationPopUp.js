let popUpPlanNo, popUpDailyPlanNo, popUpMemberNo, popUpDailyPlanDateTime;

function dailyPlanNotificationPopUpOpen(planNo, dailyPlanNo, memberNo, dailyPlanDateTime) {
    popUpPlanNo = planNo;
    popUpDailyPlanNo = dailyPlanNo;
    popUpMemberNo = memberNo;
    popUpDailyPlanDateTime = new Date(dailyPlanDateTime);

    const popupWrap = $("#notification_popup_wrap");
    popupWrap.css("position", "absolute");
    popupWrap.css("top", (($(window).height() - popupWrap.outerHeight()) / 3) + $(window).scrollTop());
    popupWrap.css("left", (($(window).width() - popupWrap.outerWidth()) / 2) + $(window).scrollLeft());
    popupWrap.show();
}

function popUpCancel() {
    $("#notification_popup_wrap").hide();
}

function popUpOk() {
    const optionTime = $("#select_option_time option:selected").val();

    switch (optionTime) {
        case constCode.global.notification15Minutes:
            popUpDailyPlanDateTime.setMinutes(popUpDailyPlanDateTime.getMinutes() - 15);
            break;
        case constCode.global.notification30Minutes:
            popUpDailyPlanDateTime.setMinutes(popUpDailyPlanDateTime.getMinutes() - 30);
            break;
        case constCode.global.notification1Hours:
            popUpDailyPlanDateTime.setHours(popUpDailyPlanDateTime.getHours() - 1);
            break;
        case constCode.global.notification2Hours:
            popUpDailyPlanDateTime.setHours(popUpDailyPlanDateTime.getHours() - 2);
            break;
        case constCode.global.notification3Hours:
            popUpDailyPlanDateTime.setHours(popUpDailyPlanDateTime.getHours() - 3);
            break;
    }

    $.ajax({
        url: "/plans/createNotification.trip",
        type: "post",
        traditional: true,
        dataType: 'json',
        data: {
            planNo: popUpPlanNo,
            dailyPlanNo: popUpDailyPlanNo,
            notificationTypeCode: constCode.global.notificationTypeCodeTripSchedule,
            senderNo: popUpMemberNo,
            receiverNo: popUpMemberNo,
            notificationDateTime: dateToString(popUpDailyPlanDateTime)
        },
        success: function (result) {
            isAjaxProcessing = false;

            if (result.code === constCode.global.resultCodeSuccess) {
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
}