$(function () {
    let isAjaxProcessing = false;

    const memberId = $("#memberId");
    const inputMemberPassword = $("#memberPassword");
    const inputMemberPasswordCheck = $("#checkMemberPassword");

    $(".withdraw_btn").on('click', function () {
        if (!blankCheck(inputMemberPassword) || !blankCheck(inputMemberPasswordCheck)) {
            popUpOpen('비밀번호를 입력해 주세요.');
            return false;
        }

        if ($("#checkMemberPassword").val() !== $("#memberPassword").val()) {
            popUpOpen("비밀번호 값이 일치하지 않습니다.");
            return false;
        }

        if (!passwordValidationCheck(inputMemberPassword.val())) {
            popUpOpen('비밀번호는 영문, 숫자, 특수기호가 적어도 1개 이상씩 포함된 8~20자의 비밀번호만 입력 가능합니다.');
            return false;
        }

        if (isAjaxProcessing) {
            popUpOpen('이전 요청을 처리중 입니다. 잠시 후 다시 시도하세요.');
            return;
        } else {
            isAjaxProcessing = true;
        }

        $.ajax({
            url: "/members/withdraw.trip",
            type: "post",
            dataType: 'json',
            data: {
                memberId: memberId.val(),
                memberPassword: inputMemberPassword.val()
            },
            success: function (result) {
                isAjaxProcessing = false;

                if (result.code === constCode.global.resultCodeSuccess) {
                    if (result.withDrawSuccess === true) {
                        popUpOpen("회원 탈퇴가 완료되었습니다.");
                        $(".popup_close_btn").attr("onclick", null).on('click', function () {
                            popUpClose("/forward/main/main.trip");
                        });
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