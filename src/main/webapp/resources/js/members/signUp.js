$(function () {
    let duplicateIdCheckYn = false;
    let duplicateNickNmCheckYn = false;
    let duplicateEmailCheckYn = false;
    let isAjaxProcessing = false;
    let inputGenderCode = $("#genderCode");

    const inputMemberId = $("#memberId");
    const inputMemberPassword = $("#memberPassword");
    const inputMemberPasswordCheck = $("#checkMemberPassword");
    const inputMemberName = $("#memberName");
    const inputNickName = $("#nickName");
    const inputEmail = $("#email");
    const inputBirthDay = $("#birthDay");

    inputMemberId.change(function () {
        duplicateIdCheckYn = false;
    });

    inputMemberName.change(function () {
        duplicateNickNmCheckYn = false;
    });

    inputEmail.change(function () {
        duplicateEmailCheckYn = false;
    });

    inputEmail.on('keyup', function () {
        const inputEmail = $(this).val();

        if (!emailValidationCheck(inputEmail)) {
            $("#checkEmailMessage").show();
        } else {
            $("#checkEmailMessage").hide();
        }
    });

    $("#memberPassword, #checkMemberPassword").on('keyup', function () {
        if ($("#checkMemberPassword").val() !== $("#memberPassword").val()) {
            $("#checkMemberPasswordMessage").show();
        } else {
            $("#checkMemberPasswordMessage").hide();
        }
    });

    $(".btn-default").on('click', function () {
        const genderFemaleBtn = $(this).parent().find(".btn-primary");

        if (inputGenderCode.val() !== $("#GENDER_CODE_MALE").val()) {
            $(this).toggleClass('active');
            genderFemaleBtn.toggleClass('active');
            inputGenderCode.val($("#GENDER_CODE_MALE").val());
        }
    });

    $(".btn-primary").on('click', function () {
        const genderMaleBtn = $(this).parent().find(".btn-default");

        if (inputGenderCode.val() !== $("#GENDER_CODE_FEMALE").val()) {
            $(this).toggleClass('active');
            genderMaleBtn.toggleClass('active');
            inputGenderCode.val($("#GENDER_CODE_FEMALE").val());
        }
    });

    function formBlankCheck() {
        if (!blankCheck(inputMemberId)) {
            popUpOpen('???????????? ????????? ?????????.');
            return false;
        }

        if (!blankCheck(inputMemberPassword) || !blankCheck(inputMemberPasswordCheck)) {
            popUpOpen('??????????????? ????????? ?????????.');
            return false;
        }

        if (!blankCheck(inputMemberName)) {
            popUpOpen('????????? ????????? ?????????.');
            return false;
        }

        if (!blankCheck(inputNickName)) {
            popUpOpen('???????????? ????????? ?????????.');
            return false;
        }

        if (!blankCheck(inputEmail)) {
            popUpOpen('???????????? ????????? ?????????.');
            return false;
        }

        if (!blankCheck(inputBirthDay)) {
            popUpOpen('??????????????? ????????? ?????????.');
            return false;
        }

        return true;
    }

    function formSpaceCheck() {
        if (!spaceCheck(inputMemberId)) {
            popUpOpen('???????????? ????????? ?????????????????????.');
            return false;
        }

        if (!spaceCheck(inputMemberPassword)) {
            popUpOpen('??????????????? ????????? ?????????????????????.');
            return false;
        }

        if (!spaceCheck(inputMemberName)) {
            popUpOpen('????????? ????????? ?????????????????????.');
            return false;
        }

        return true;
    }

    function formValidationCheck() {
        if (!idValidationCheck(inputMemberId.val())) {
            popUpOpen('???????????? ??????, ????????? ???????????? 5~20?????? ???????????? ?????? ???????????????.');
            return false;
        }

        if (!passwordValidationCheck(inputMemberPassword.val())) {
            popUpOpen('??????????????? ??????, ??????, ??????????????? ????????? 1??? ????????? ????????? 8~20?????? ??????????????? ?????? ???????????????.');
            return false;
        }

        if (!nameValidationCheck(inputMemberName.val())) {
            popUpOpen('????????? ???????????? ????????? ???????????? 2~20?????? ????????? ?????? ???????????????.');
            return false;
        }

        if (!nickNameValidationCheck(inputNickName.val())) {
            popUpOpen('???????????? ????????? ?????? ?????? ?????? ????????????, 1~20?????? ???????????? ?????? ???????????????.');
            return false;
        }

        if (!emailValidationCheck(inputEmail.val())) {
            popUpOpen('???????????? ????????? ?????? ????????????.');
            return false;
        }

        if (!birthDayValidationCheck(inputBirthDay.val())) {
            popUpOpen('??????????????? YYYYMMDD ????????? ?????? ???????????????.');
            return false;
        }

        return true;
    }

    $("#signup_duplicate_id").on('click', function () {
        if (!blankCheck(inputMemberId)) {
            popUpOpen('???????????? ????????? ?????????.');
            return false;
        }
        if (!spaceCheck(inputMemberId)) {
            popUpOpen('???????????? ????????? ?????????????????????.');
            return false;
        }
        if (!idValidationCheck(inputMemberId.val())) {
            popUpOpen('???????????? ??????, ????????? ???????????? 5~20?????? ???????????? ?????? ???????????????.');
            return false;
        }

        $.ajax({
            url: "/members/duplication/memberId.trip",
            type: "get",
            dataType: 'json',
            data: {
                memberId: $('#memberId').val()
            },
            success: function (result) {
                if (result.code !== constCode.global.resultCodeSuccess) {
                    popUpOpen(result.message);
                    return;
                }

                if (result.isDuplicate) {
                    popUpOpen('?????? ?????? ?????? ??????????????????.')
                    duplicateIdCheckYn = false;
                } else {
                    popUpOpen('?????? ????????? ??????????????????.')
                    duplicateIdCheckYn = true;
                }
            },
            error: function (error) {
                popUpOpen("?????? ??? ????????? ?????????????????????.");
            }
        })
    });

    $("#signup_duplicate_nick_name").on('click', function () {
        if (!blankCheck($("#nickName"))) {
            popUpOpen('???????????? ????????? ?????????.');
            return false;
        }
        if (!nickNameValidationCheck(inputNickName.val())) {
            popUpOpen('???????????? ????????? ?????? ?????? ?????? ????????????, 1~20?????? ???????????? ?????? ???????????????.');
            return false;
        }

        $.ajax({
            url: "/members/duplication/nickName.trip",
            type: "get",
            dataType: 'json',
            data: {
                nickName: $('#nickName').val()
            },
            success: function (result) {
                if (result.code !== constCode.global.resultCodeSuccess) {
                    popUpOpen(result.message);
                    return;
                }

                if (result.isDuplicate) {
                    popUpOpen('?????? ?????? ?????? ??????????????????.')
                    duplicateNickNmCheckYn = false;
                } else {
                    popUpOpen('?????? ????????? ??????????????????.')
                    duplicateNickNmCheckYn = true;
                }
            },
            error: function (error) {
                popUpOpen("?????? ??? ????????? ?????????????????????.");
            }
        })
    });

    $("#signup_duplicate_email").on('click', function () {
        if (!blankCheck(inputEmail)) {
            popUpOpen('???????????? ????????? ?????????.');
            return false;
        }
        if (!emailValidationCheck(inputEmail.val())) {
            popUpOpen('???????????? ????????? ?????? ????????????.');
            return false;
        }

        $.ajax({
            url: "/members/duplication/email.trip",
            type: "get",
            dataType: 'json',
            data: {
                email: $('#email').val()
            },
            success: function (result) {
                if (result.code !== constCode.global.resultCodeSuccess) {
                    popUpOpen(result.message);
                    return;
                }

                if (result.isDuplicate) {
                    popUpOpen('?????? ?????? ?????? ??????????????????.')
                    duplicateEmailCheckYn = false;
                } else {
                    popUpOpen('?????? ????????? ??????????????????.')
                    duplicateEmailCheckYn = true;
                }
            },
            error: function (error) {
                popUpOpen("?????? ??? ????????? ?????????????????????.");
            }
        })
    });

    function duplicationCheck() {
        if (!duplicateIdCheckYn) {
            popUpOpen('????????? ??????????????? ???????????????.')
            return false;
        }

        if (!duplicateNickNmCheckYn) {
            popUpOpen('????????? ??????????????? ???????????????.')
            return false;
        }

        if (!duplicateEmailCheckYn) {
            popUpOpen('????????? ??????????????? ???????????????.')
            return false;
        }
        return true;
    }

    $(".signup_complete").on('click', function () {
        if (!formBlankCheck() || !formSpaceCheck() || !formValidationCheck() || !duplicationCheck()) {
            return false;
        }

        if (isAjaxProcessing) {
            popUpOpen('?????? ????????? ????????? ?????????. ?????? ??? ?????? ???????????????.');
            return;
        } else {
            isAjaxProcessing = true;
        }

        $.ajax({
            url: "/members/signUp.trip",
            type: "post",
            dataType: 'json',
            data: $("#signupForm").serialize(),
            success: function (result) {
                isAjaxProcessing = false;
                if (result.code === constCode.global.resultCodeSuccess) {
                    pageReplace("/forward/members/signUpResult.trip");
                } else {
                    popUpOpen(result.message);
                }
            },
            error: function (error) {
                isAjaxProcessing = false;
                popUpOpen("?????? ??? ????????? ?????????????????????.");
            }
        })
        return true;
    });
});