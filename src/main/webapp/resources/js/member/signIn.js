$(function () {
    const inputMemberId = $("#memberId");
    const inputMemberPassword = $("#memberPassword");

    $("#find_id").on('click', function () {
        window.location.href = "/forward/members/findId.trip";
    });

    $("#find_password").on('click', function () {
        window.location.href = "/forward/members/findPassword.trip";
    });

    $(".signin__menu_join").on('click', function () {
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

        $.ajax({
            url: "/members/signIn.trip",
            type: "post",
            dataType: 'json',
            data: $("#signinForm").serialize(),
            success: function (result) {
                if (result) {
                    // TODO: 로그인 성공 시 main으로 이동하도록 수정
                    window.location.href = "/forward/member/signUp.trip";
                } else {
                    popUpOpen("등록되지 않은 아이디이거나, 아이디 혹은 비밀번호를 잘못 입력했습니다.");
                }
            },
            error: function (error) {
                console.log(error);
                popUpOpen("처리 중 오류가 발생하였습니다.");
            }
        })
    });
});