window.onload = function () {
    let signInResult = $("#signInResult").val();

    if (signInResult === "N") {
        popUpOpen("등록되지 않은 아이디이거나, 아이디 혹은 비밀번호를 잘못 입력했습니다.");
    }

    signInResult = null;
};

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

        $("#signinForm").attr("action", "/members/signIn.trip").submit();
    });
});