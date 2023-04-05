$(function () {
    let isAjaxProcessing = false;
    let notificationTypeCode = $(this).find("#notificationTypeCode").val();
    let notificationNo = $(this).find("#notificationNo").val();
    let memberNo = $(this).find("#memberNo").val();
    let useYn = $(this).find("#useYn").val();
    let planNo =  $("#formPlanNo").val();

    $(".icon_arrow_left").on('click', function () {
        $("#notificationForm").attr("method", "get").attr("action", "/main/main.trip").submit();
    });

    function updateReadTimeAjax() {
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
                            const postNo = $(this).find("#postNo").val();
                            $("#notificationForm").attr("action", "/wishlist/postMain.trip").submit();
                        } else if (notificationTypeCode === constCode.global.notificationTypeCodeInvitation) {
                            if (useYn === 'N') {
                                popUpOpen("존재하지 않는 플랜입니다.");
                                $(".popup_close_btn").attr("onclick", null).on('click', function () {
                                    location.reload();
                                });
                            } else {
                                insertPlanMateAjax();
                            }
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
            }
        )
    }

    function insertPlanMateAjax() {
        $.ajax({
            url: "/plans/insertPlanMate.trip",
            type: "post",
            dataType: 'json',
            data: {
                planNo: planNo,
                memberNo: memberNo,
                leadYn: constCode.global.N
            },
            async: false,
            success: function (result) {
                $("#notificationForm").attr("action", "/plans/planMain.trip").submit();
            },
            error: function (error) {
                popUpOpen("처리 중 오류가 발생하였습니다.");
            }
        })
    }

    $(".alertlist_item_wrap").on('click', function () {
        notificationTypeCode = $(this).find("#notificationTypeCode").val();
        notificationNo = $(this).find("#notificationNo").val();
        memberNo = $(this).find("#memberNo").val();
        useYn = $(this).find("#useYn").val();
        planNo = $(this).find("#planNo").val();

        $("#formPlanNo").val(planNo);

        if ($(this).attr('id') !== undefined) {
            if (notificationTypeCode === constCode.global.notificationTypeCodeInvitation) {
                if (useYn === 'N') {
                    updateReadTimeAjax();
                } else {
                    checkPopUpOpen("초대를 수락하시겠습니까?");
                    $(".check_popup_btn_ok").attr("onclick", null).on('click', function () {
                        updateReadTimeAjax();
                    })
                }
            } else {
                updateReadTimeAjax();
            }
        } else {
            if (useYn === 'N') {
                popUpOpen("존재하지 않는 플랜입니다.");
                return;
            }
            if (notificationTypeCode === constCode.global.notificationTypeCodeTripSchedule) {
                const postNo = $(this).find("#postNo").val();
                $("#notificationForm").attr("action", "/wishlist/postMain.trip").submit();
            } else if (notificationTypeCode === constCode.global.notificationTypeCodeChangeLeader) {
                $("#notificationForm").attr("action", "/plans/planMain.trip").submit();
            } else if (notificationTypeCode === constCode.global.notificationTypeCodeInvitation) {
                $("#notificationForm").attr("action", "/plans/planMain.trip").submit();
            }
        }
    });
})
;