$(function () {
    let isAjaxProcessing = false;
    const inputPresentMemberPassword = $("#presentMemberPassword");
    const inputNewMemberPassword = $("#newMemberPassword");
    const inputCheckNewMemberPassword = $("#checkNewMemberPassword");

    $("#newMemberPassword, #checkNewMemberPassword").on('keyup', function () {
        if (inputNewMemberPassword.val() !== inputCheckNewMemberPassword.val()) {
            $("#checkMemberPasswordMessage").show();
        } else {
            $("#checkMemberPasswordMessage").hide();
        }
    });

    function formBlankCheck() {
        if (!blankCheck(inputPresentMemberPassword) || !blankCheck(inputNewMemberPassword) || !blankCheck(inputCheckNewMemberPassword)) {
            popUpOpen('비밀번호를 입력해 주세요.');
            return false;
        }

        return true;
    }

    function formSpaceCheck() {
        if (!spaceCheck(inputPresentMemberPassword) || !spaceCheck(inputNewMemberPassword) || !spaceCheck(inputCheckNewMemberPassword)) {
            popUpOpen('비밀번호에 공백이 입력되었습니다.');
            return false;
        }

        return true;
    }

    function formValidationCheck() {
        if (!passwordValidationCheck(inputPresentMemberPassword.val()) || !passwordValidationCheck(inputNewMemberPassword.val()) || !passwordValidationCheck(inputCheckNewMemberPassword.val())) {
            popUpOpen('비밀번호는 영문, 숫자, 특수기호가 적어도 1개 이상씩 포함된 8~20자의 비밀번호만 입력 가능합니다.');
            return false;
        }

        return true;
    }

    $("#change_password_complete").on('click', function () {
        console.log(inputPresentMemberPassword.val() + " " + inputNewMemberPassword.val());

        if (!formBlankCheck() || !formSpaceCheck() || !formValidationCheck()) {
            return false;
        }

        if (inputPresentMemberPassword.val() === inputNewMemberPassword.val()) {
            popUpOpen('현재 비밀번호와 새비밀번호가 일치합니다.');
            return false;
        }

        if (isAjaxProcessing) {
            popUpOpen('이전 요청을 처리중 입니다. 잠시 후 다시 시도하세요.');
            return;
        } else {
            isAjaxProcessing = true;
        }

        $.ajax({
            url: "/members/changePassword.trip",
            type: "post",
            dataType: 'json',
            data: {
                memberPassword: inputPresentMemberPassword.val(),
                newMemberPassword: inputNewMemberPassword.val()
            },
            success: function (result) {
                isAjaxProcessing = false;

                if (result.code === constCode.global.resultCodeSuccess) {
                    if (result.changePasswordSuccess === true) {
                        popUpOpen("비밀번호 변경이 완료되었습니다.");
                        $(".popup_close_btn").attr("onclick", null);
                        $(".popup_close_btn").on('click', function () {
                            popUpClose("/forward/main/main.trip");
                        });
                    } else {
                        popUpOpen("처리 중 오류가 발생하였습니다.");
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

    $(".change_password_next").on('click', function () {
        window.location.href = "../../main/main.trip";
    });
});