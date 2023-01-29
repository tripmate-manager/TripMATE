$(function () {
    let isAjaxProcessing = false;
    const memberId = $("#memberId").val();
    const email = $("#email").val();

    $(".temporary_email_resend_button").on('click', function () {
        if(isAjaxProcessing) {
            popUpOpen('이전 요청을 처리중 입니다. 잠시 후 다시 시도하세요.');
            return;
        } else {
            isAjaxProcessing = true;
        }

        $.ajax({
            url: "/members/sendCertificationMail.trip",
            type: "post",
            dataType: 'json',
            data: {
                memberId: memberId,
                to: email,
                mailTypeCode: $("#EMAIL_TYPE_CODE_JOIN").val()
            },
            success: function (result) {
                isAjaxProcessing = false;
                if (result.code === constCode.global.resultCodeSuccess) {
                    popUpOpen("인증메일을 전송하였습니다. 이메일을 확인해주세요.");
                    $(".popup_close_btn").on('click', function () {
                        popUpClose("/forward/members/signIn.trip");
                    });
                } else {
                    popUpOpen(result.message);
                }
            },
            error: function (error) {
                isAjaxProcessing = false;
                popUpOpen("처리 중 오류가 발생하였습니다.");
            }
        })
        return true;
    });
});