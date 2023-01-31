$(function () {
    let isAjaxProcessing = false;
    const inputMemberId = $("#memberId");
    const inputEmail = $("#email");

    inputEmail.on('keyup', function () {
        const inputEmail = $(this).val();

        if (!emailValidationCheck(inputEmail)) {
            $("#checkEmailMessage").show();
        } else {
            $("#checkEmailMessage").hide();
        }
    });

    function formBlankCheck() {
        if (!blankCheck(inputMemberId)) {
            popUpOpen('아이디를 입력해 주세요.');
            return false;
        }

        if (!blankCheck(inputEmail)) {
            popUpOpen('이메일을 입력해 주세요.');
            return false;
        }

        return true;
    }

    function formSpaceCheck() {
        if (!spaceCheck(inputMemberId)) {
            popUpOpen('아이디에 공백이 입력되었습니다.');
            return false;
        }

        if (!spaceCheck(inputEmail)) {
            popUpOpen('이메일에 공백이 입력되었습니다.');
            return false;
        }

        return true;
    }

    function formValidationCheck() {
        if (!idValidationCheck(inputMemberId.val())) {
            popUpOpen('아이디는 영문, 숫자로 이루어진 5~20자의 아이디만 입력 가능합니다.');
            return false;
        }

        if (!emailValidationCheck(inputEmail.val())) {
            popUpOpen('이메일이 형식에 맞지 않습니다.');
            return false;
        }

        return true;
    }

    $(".find_password_button").on('click', function () {
        if (!formBlankCheck()) {
            return false;
        }
        if (!formSpaceCheck()) {
            return false;
        }
        if (!formValidationCheck()) {
            return false;
        }

        if (isAjaxProcessing) {
            popUpOpen('이전 요청을 처리중 입니다. 잠시 후 다시 시도하세요.');
            return;
        } else {
            isAjaxProcessing = true;
        }

        $.ajax({
            url: "/members/sendPasswordMail.trip",
            type: "post",
            dataType: 'json',
            data: {
                memberId: inputMemberId.val(),
                to: inputEmail.val()
            },
            success: function (result) {
                isAjaxProcessing = false;

                if (result.code === constCode.global.resultCodeSuccess) {
                    if (result.sendMailSuccess === true) {
                        popUpOpen("인증메일을 전송하였습니다. 이메일을 확인해주세요.");
                        $(".popup_close_btn").attr("onclick", null);
                        $(".popup_close_btn").on('click', function () {
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
                console.log(error);
                popUpOpen("처리 중 오류가 발생하였습니다.");
            }
        })
    });
});