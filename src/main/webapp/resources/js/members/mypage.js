$(function () {

    $(".btn-default").on('click', function () {
        const genderFemaleBtn = $(this).parent().find(".btn-primary");

        if (inputGenderCode.val() !== $("#GENDER_CODE_MALE").val()) {
            $(this).toggleClass('active1');
            genderFemaleBtn.toggleClass('active2');
            inputGenderCode.val($("#GENDER_CODE_MALE").val());
        }
    });

    $(".btn-primary").on('click', function () {
        const genderMaleBtn = $(this).parent().find(".btn-default");

        if (inputGenderCode.val() !== $("#GENDER_CODE_FEMALE").val()) {
            $(this).toggleClass('active2');
            genderMaleBtn.toggleClass('active1');
            inputGenderCode.val($("#GENDER_CODE_FEMALE").val());
        }
    });

    $(".mypage_change_password_wrap").on('click', function () {
        window.location.href = "../mypage/changePassword.trip";
    });

    $(".mypage_change_email_btn").on('click', function () {
        window.location.href = "../changeEmail.trip";
    });
});