$(function () {
    const inputMemberName = $("#memberName");
    const inputEmail = $("#email");

    function findIdValidationCheck() {
        if (!nameValidationCheck(inputMemberName.val())) {
            popUpOpen('이름은 영문이나 한글로 이루어진 2~20자의 이름만 입력 가능합니다.');
            return false;
        }

        if (!emailValidationCheck(inputEmail.val())) {
            popUpOpen('이메일이 형식에 맞지 않습니다.');
            return false;
        }

        return true;
    }

    function inputBlankCheck() {
        if (!blankCheck(inputMemberName)) {
            popUpOpen('이름을 입력해 주세요.');
            return false;
        }

        if (!blankCheck(inputEmail)) {
            popUpOpen('이메일을 입력해 주세요.');
            return false;
        }

        return true;
    }

    $(".find_id_button").on('click', function () {
        if (!inputBlankCheck()) {
            return false;
        }

        if (!findIdValidationCheck()) {
            return false;
        }
    });
});