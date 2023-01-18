$(function () {
    $("#email").on('keyup', function () {
        const inputEmail = $(this).val();

        if (!emailValidationCheck(inputEmail)) {
            $(".email_error_message").show();
        } else {
            $(".email_error_message").hide();
        }
    });
});