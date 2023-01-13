function idValidationCheck(inputId) {
    const regExp = /^[0-9a-zA-Z]{5,20}$/;

    if (!regExp.test(inputId)) {
        return false;
    }
    return true;
}

function passwordValidationCheck(inputPassword) {
    const regExp = /(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\W)(?=\S+$).{8,20}/;

    if (!regExp.test(inputPassword)) {
        return false;
    }
    return true;
}

function nameValidationCheck(inputName) {
    const regExp = /^[가-힣]{2,20}|[a-zA-Z]{2,20}$/;

    return regExp.test(inputName);
}

function emailValidationCheck(inputEmail) {
    const regExp = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.[a-zA-Z]{2,4}$/;

    return regExp.test(inputEmail);
}

function birthDayValidationCheck(inputBirthDay) {
    const regExp = /^\d{4}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])$/;

    return regExp.test(inputBirthDay);
}

function blankCheck(input) {
    if (input.val() === "") {
        input.focus();
        return false;
    }
    return true;
}