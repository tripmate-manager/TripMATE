function inviteCodePopUpOpen() {
    const popupWrap = $(".invitecode_popup_wrap");

    popupWrap.css("position", "absolute");
    popupWrap.css("top", (($(window).height() - popupWrap.outerHeight()) / 3) + $(window).scrollTop());
    popupWrap.css("left", (($(window).width() - popupWrap.outerWidth()) / 2) + $(window).scrollLeft());
    popupWrap.show();
}

function removeInviteCodeSession() {
    $.ajax({
        url: "/plans/removeInviteCodeSession.trip",
        type: "post",
        dataType: 'json',
        success: function (result) {
            $(".invitecode_popup_wrap").hide();
        },
        error: function (error) {
            popUpOpen("처리 중 오류가 발생하였습니다.");
        }
    })
}

function inviteCodePopUpOk() {
    const inputInviteCode = $("#inputInviteCode").val();
    const inviteCode = $("#inviteCode").val();
    const inviteCodeExpireDateTime = $("#inviteCodeExpireDateTime").val();

    if (inputInviteCode !== inviteCode) {
        popUpOpen("초대코드를 잘못 입력하였습니다.");
        return;
    }

    const now = new Date();
    const inviteCodeExpireDate = new Date(inviteCodeExpireDateTime);

    if (now > inviteCodeExpireDate) {
        popUpOpen("만료된 초대코드입니다.");
        $(".check_popup_wrap").hide();
        return;
    }

    $.ajax({
        url: "/plans/insertPlanMate.trip",
        type: "post",
        dataType: 'json',
        data: {
            planNo: $("#invitePlanNo").val(),
            memberNo: $("#memberNo").val(),
            leadYn: constCode.global.N
        },
        async: false,
        success: function (result) {
            $("#inviteCodePopUpForm").attr("action", "/plans/planMain.trip").submit();
            $(".check_popup_wrap").hide();
        },
        error: function (error) {
            popUpOpen("처리 중 오류가 발생하였습니다.");
        }
    })

    removeInviteCodeSession();
}