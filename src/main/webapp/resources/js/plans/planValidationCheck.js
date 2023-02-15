function blankCheck(input) {
    if (input.val() === "") {
        input.focus();
        return false;
    }
    return true;
}

function planTitleValidationCheck(input) {
    if (input.val().length > 20) {
        return false;
    }
    return true;
}

function planDescriptionValidationCheck(input) {
    if (input.val().length > 100) {
        return false;
    }
    return true;
}

function hashtagValidationCheck(input) {
    if (input.val().length > 10) {
        return false;
    }

    return true;
}

function planDateStartEndCheck(start, end) {
    if (start.val() > end.val()) {
        return false;
    }

    return true;
}