$(function () {
    const memberGenderCode = $("#memberGenderCode");
    const genderFemaleBtn = $(".btn-primary");
    const genderMaleBtn = $(".btn-default");

    if (memberGenderCode.val() === constCode.global.memberGenderCodeMale) {
        $(".btn-default").toggleClass('active1');
    } else {
        $(".btn-primary").toggleClass('active2');
    }

    $(".btn-default").on('click', function () {
        if (memberGenderCode.val() !== constCode.global.memberGenderCodeMale) {
            genderMaleBtn.toggleClass('active1');
            genderFemaleBtn.toggleClass('active2');
            memberGenderCode.val(constCode.global.memberGenderCodeMale);
        }
    });

    $(".btn-primary").on('click', function () {
        if (memberGenderCode.val() !== constCode.global.memberGenderCodeFemale) {
            genderFemaleBtn.toggleClass('active2');
            genderMaleBtn.toggleClass('active1');
            memberGenderCode.val(constCode.global.memberGenderCodeFemale);
        }
    });

    $(".mypage_change_password_wrap").on('click', function () {
        pageLink("/forward/members/mypage/changePassword.trip");
    });

    $(".mypage_change_email_btn").on('click', function () {
        pageLink("/forward/members/changeEmail.trip");
    });
});