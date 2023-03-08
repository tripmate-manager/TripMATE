$(function () {
    let isAjaxProcessing = false;

    const inputMemberId = $("#memberId");
    const inputMemberPassword = $("#memberPassword");

    $(".signin_menu_join").on('click', function () {
        pageLink("/forward/members/signUp.trip");
    });

    $("#find_id").on('click', function () {
        pageLink("/forward/members/findId.trip");
    });

    $("#find_password").on('click', function () {
        pageLink("/forward/members/findPassword.trip");
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

    function formSpaceCheck() {
        if (!spaceCheck(inputMemberId)) {
            popUpOpen('아이디에 공백이 입력되었습니다.');
            return false;
        }

        if (!spaceCheck(inputMemberPassword)) {
            popUpOpen('비밀번호에 공백이 입력되었습니다.');
            return false;
        }

        return true;
    }

    $(".signin_btn").on('click', function () {
        if (!formBlankCheck() || !formSpaceCheck()) {
            return false;
        }
        if (!idValidationCheck(inputMemberId.val())) {
            popUpOpen('아이디는 영문, 숫자로 이루어진 5~20자의 아이디만 입력 가능합니다.');
            return false;
        }

        if (isAjaxProcessing) {
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
                if (result.code === constCode.global.resultCodeSuccess) {
                    if (result.signInRequestCnt >= $("#SIGNIN_LIMIT_CNT").val()) {
                        popUpOpen("로그인 시도 횟수를 초과하여 지금은 로그인할 수 없습니다.");
                        return false;
                    }

                    if (result.memberStatusCode === constCode.global.memberStatusCodeComplete) {
                        pageReplace("/main/main.trip");
                        $("#signinForm")[0].reset();
                    } else if (result.memberStatusCode === constCode.global.memberStatusCodeTemporary) {
                        $("#email").val(result.email);
                        $("#signinForm").attr("action", "/members/temporarySignInResult.trip").submit();
                    } else if (result.memberStatusCode === constCode.global.memberStatusCodeIssueTemporaryPassword) {
                        pageReplace("/forward/members/changePassword.trip");
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