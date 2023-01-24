$(function () {
    let isAjaxProcessing = false;

    const inputMemberId = $("#memberId");
    const inputMemberPassword = $("#memberPassword");

    $(".signin_menu_join").on('click', function () {
        window.location.href = "/forward/members/signUp.trip";
    });

    function formBlankCheck() {
        if (!blankCheck(inputMemberId)) {
            popUpOpen('아이디를 입력해 주세요.');
            return false;
        }

        if (!blankCheck(inputMemberPassword)) {
            popUpOpen('비밀번호를 입력해 주세요.');
            return false;
        }

        return true;
    }

    $(".signin_btn").on('click', function () {
        if (!formBlankCheck()) {
            return false;
        }

        if(isAjaxProcessing) {
            popUpOpen('이전 요청을 처리중 입니다. 잠시 후 다시 시도하세요.');
            return;
        } else {
            isAjaxProcessing = true;
        }

        $.ajax({
            url: "/members/signIn.trip",
            type: "post",
            dataType: 'json',
            data: $("#signinForm").serialize(),
            success: function (result) {
                isAjaxProcessing = false;
                if (result.code === "0000") {
                    if (result.logInRequestCnt >= 5) {
                        popUpOpen("로그인 시도 횟수를 초과하여 지금은 로그인할 수 없습니다.");
                        return false;
                    }

                    if (result.memberStatusCode === "10") {
                        //TODO: 메인으로 이동하도록 수정
                        window.location.href = "/forward/members/signUpResult.trip";
                    } else if (result.memberStatusCode === "20"){
                        window.location.href = "/forward/members/temporarySignInResult.trip";
                    }
                } else {
                    popUpOpen("등록되지 않은 아이디이거나, 아이디 혹은 비밀번호를 잘못 입력했습니다.");
                }
            },
            error: function (error) {
                isAjaxProcessing = false;
                popUpOpen("처리 중 오류가 발생하였습니다.");
            }
        })
    });
});