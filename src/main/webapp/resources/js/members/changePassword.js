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
});