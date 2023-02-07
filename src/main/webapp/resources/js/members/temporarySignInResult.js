$(function () {
    let isAjaxProcessing = false;
    const memberId = $("#signInInfo").val();
    const email = $("#email").val();

    $(".temporary_email_resend_button").on('click', function () {
        if (isAjaxProcessing) {
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
                        if (result.sendMailSuccess === true) {
                            popUpOpen("인증메일을 전송하였습니다. 이메일을 확인해주세요.");
                            $(".popup_close_btn").attr("onclick", null).on('click', function () {
                                popUpClose("/forward/members/signIn.trip");
                            });
                        } else {
                            popUpOpen("메일 전송 중 오류가 발생하였습니다.");
                        }
                    } else {
                        popUpOpen(result.message);
                    }
                },
                error: function (error) {
                    isAjaxProcessing = false;
                    console.error(error);
                    popUpOpen("처리 중 오류가 발생하였습니다.");
                }
            }
        )
    });

    $(".temporary_email_change_button").on('click', function () {
        $("#temporaryForm").attr("action", "/members/changeEmail.trip").submit();
    });
});