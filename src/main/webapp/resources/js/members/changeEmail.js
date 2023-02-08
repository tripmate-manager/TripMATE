$(function () {
    let isAjaxProcessing = false;
    let duplicateEmailCheckYn = false;

    const memberId = $("#memberId").val();
    const inputEmail = $("#memberEmail");

    inputEmail.change(function () {
        duplicateEmailCheckYn = false;
    });

    inputEmail.on('keyup', function () {
        const inputEmail = $(this).val();

        if (!emailValidationCheck(inputEmail)) {
            $("#checkEmailMessage").show();
        } else {
            $("#checkEmailMessage").hide();
        }
    });

    $(".change_email_duplicate").on('click', function () {
        if (!blankCheck(inputEmail)) {
            popUpOpen('이메일을 입력해 주세요.');
            return false;
        }
        if (!emailValidationCheck(inputEmail.val())) {
            popUpOpen('이메일이 형식에 맞지 않습니다.');
            return false;
        }

        if (isAjaxProcessing) {
            popUpOpen('이전 요청을 처리중 입니다. 잠시 후 다시 시도하세요.');
            return;
        } else {
            isAjaxProcessing = true;
        }

        $.ajax({
            url: "/members/duplication/email.trip",
            type: "get",
            dataType: 'json',
            data: {
                email: inputEmail.val()
            },
            success: function (result) {
                isAjaxProcessing = false;

                if (result.code !== constCode.global.resultCodeSuccess) {
                    popUpOpen(result.message);
                    return;
                }

                if (result.isDuplicate) {
                    popUpOpen('이미 사용 중인 이메일입니다.')
                    duplicateEmailCheckYn = false;
                } else {
                    popUpOpen('사용 가능한 이메일입니다.')
                    duplicateEmailCheckYn = true;
                }
            },
            error: function (error) {
                popUpOpen("처리 중 오류가 발생하였습니다.");
            }
        })
    });

    $(".change_email_complete").on('click', function () {
        if (!blankCheck(inputEmail)) {
            popUpOpen('이메일을 입력해 주세요.');
            return false;
        }

        if (!spaceCheck(inputEmail)) {
            popUpOpen('이메일에 공백이 입력되었습니다.');
            return false;
        }

        if (!emailValidationCheck(inputEmail.val())) {
            popUpOpen('잘못된 이메일 형식입니다.');
            return false;
        }

        if (!duplicateEmailCheckYn) {
            popUpOpen('이메일 중복확인이 필요합니다.')
            return false;
        }

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
                to: inputEmail.val(),
                mailTypeCode: $("#EMAIL_TYPE_CODE_EMAIL_CHANGE").val()
            },
            success: function (result) {
                isAjaxProcessing = false;

                if (result.code === constCode.global.resultCodeSuccess) {
                    if (result.sendMailSuccess === true) {
                        popUpOpen("인증메일을 전송하였습니다. 인증 메일 확인 시 해당 이메일로 서비스 이용이 가능합니다.");
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
                popUpOpen("처리 중 오류가 발생하였습니다.");
            }
        })
    });
});