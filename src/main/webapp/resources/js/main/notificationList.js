$(function () {
    let isAjaxProcessing = false;

    $(".icon_arrow_left").on('click', function () {
        $("#notificationForm").attr("method", "get").attr("action", "/main/main.trip").submit();
    });

    function updateReadTimeAjax(notificationTypeCode, notificationNo, memberNo) {
        if (isAjaxProcessing) {
            popUpOpen('이전 요청을 처리중 입니다. 잠시 후 다시 시도하세요.');
            return;
        } else {
            isAjaxProcessing = true;
        }

        $.ajax({
            type: 'post',
            url: '/main/notification.trip',
            dataType: 'json',
            data: {
                notificationNo: notificationNo,
                memberNo: memberNo
            },
            success: function (result) {
                isAjaxProcessing = false;

                if (result.isUpdateReadDateTime === true) {
                    if (notificationTypeCode === constCode.global.notificationTypeCodeTripSchedule) {
                        const postNo = $(this).find("#planNo").val();
                        // todo: 추후 해당 게시글로 이동하도록 수정
                    } else {
                        $("#notificationForm").attr("action", "/plans/planMain.trip").submit();
                    }
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

    $(".alertlist_item_wrap").on('click', function () {
        const notificationTypeCode = $(this).find("#notificationTypeCode").val();
        const notificationNo = $(this).find("#notificationNo").val();
        const memberNo = $(this).find("#memberNo").val();

        if ($(this).attr('id') !== undefined) {
            if (notificationTypeCode === constCode.global.notificationTypeCodeInvitation) {
                checkPopUpOpen("초대를 수락하시겠습니까?");
                $(".check_popup_btn_ok").attr("onclick", null).on('click', function () {
                    updateReadTimeAjax(notificationTypeCode, notificationNo, memberNo);
                })
            } else {
                updateReadTimeAjax(notificationTypeCode, notificationNo, memberNo);
            }
        } else {
            if (notificationTypeCode === constCode.global.notificationTypeCodeTripSchedule) {
                const postNo = $(this).find("#planNo").val();
                // todo: 추후 해당 게시글로 이동하도록 수정
            } else if (notificationTypeCode === constCode.global.notificationTypeCodeChangeLeader) {
                $("#notificationForm").attr("action", "/plans/planMain.trip").submit();
            } else if (notificationTypeCode === constCode.global.notificationTypeCodeInvitation) {
                $("#notificationForm").attr("action", "/plans/planMain.trip").submit();
            }
        }
    });
});