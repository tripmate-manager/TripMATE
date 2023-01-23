function idValidationCheck(inputId) {
    const regExp = /^[0-9a-zA-Z]{5,20}$/;

    return regExp.test(inputId);
}

function passwordValidationCheck(inputPassword) {
    const regExp = /(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\W)(?=\S+$).{8,20}/;

    return regExp.test(inputPassword);
}

function nameValidationCheck(inputName) {
    const regExp = /^[가-힣]{2,20}|[a-zA-Z]{2,20}$/;

    return regExp.test(inputName);
}

function nickNameValidationCheck(inputNickName) {
    const regExp = /^[가-힣0-9a-zA-Z~!@#$%^&*()_+|<>?:{}]{1,20}$/;

    return regExp.test(inputNickName);
}

function emailValidationCheck(inputEmail) {
    const regExp = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.[a-zA-Z]{2,4}$/;

    return regExp.test(inputEmail);
}

function birthDayValidationCheck(inputBirthDay) {
    const regExp = /^(19|20)\d{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])$/;

    return regExp.test(inputBirthDay);
}

function blankCheck(input) {
    if (input.val() === "") {
        input.focus();
        return false;
    }
    return true;
}