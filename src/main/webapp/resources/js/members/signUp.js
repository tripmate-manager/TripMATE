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
            popUpOpen('아이디를 입력해 주세요.');
            return false;
        }

        if (!blankCheck(inputMemberPassword) || !blankCheck(inputMemberPasswordCheck)) {
            popUpOpen('비밀번호를 입력해 주세요.');
            return false;
        }

        if (!blankCheck(inputMemberName)) {
            popUpOpen('이름을 입력해 주세요.');
            return false;
        }

        if (!blankCheck(inputNickName)) {
            popUpOpen('닉네임을 입력해 주세요.');
            return false;
        }

        if (!blankCheck(inputEmail)) {
            popUpOpen('이메일을 입력해 주세요.');
            return false;
        }

        if (!blankCheck(inputBirthDay)) {
            popUpOpen('생년월일을 입력해 주세요.');
            return false;
        }

        return true;
    }

    function formSpaceCheck() {
        if (!spaceCheck(inputMemberId)) {
            popUpOpen('아이디에 공백이 입력되었습니다.');
            return false;
        }

        if (!spaceCheck(inputMemberPassword)) {
            popUpOpen('비밀번호에 공백이 입력되었습니다.');
            return false;
        }

        if (!spaceCheck(inputMemberName)) {
            popUpOpen('이름에 공백이 입력되었습니다.');
            return false;
        }

        return true;
    }

    function formValidationCheck() {
        if (!idValidationCheck(inputMemberId.val())) {
            popUpOpen('아이디는 영문, 숫자로 이루어진 5~20자의 아이디만 입력 가능합니다.');
            return false;
        }

        if (!passwordValidationCheck(inputMemberPassword.val())) {
            popUpOpen('비밀번호는 영문, 숫자, 특수기호가 적어도 1개 이상씩 포함된 8~20자의 비밀번호만 입력 가능합니다.');
            return false;
        }

        if (!nameValidationCheck(inputMemberName.val())) {
            popUpOpen('이름은 영문이나 한글로 이루어진 2~20자의 이름만 입력 가능합니다.');
            return false;
        }

        if (!nickNameValidationCheck(inputNickName.val())) {
            popUpOpen('닉네임은 한영자 숫자 기호 입력 가능하며, 1~20자의 닉네임만 입력 가능합니다.');
            return false;
        }

        if (!emailValidationCheck(inputEmail.val())) {
            popUpOpen('이메일이 형식에 맞지 않습니다.');
            return false;
        }

        if (!birthDayValidationCheck(inputBirthDay.val())) {
            popUpOpen('생년월일은 YYYYMMDD 형태만 입력 가능합니다.');
            return false;
        }

        return true;
    }

    $("#signup_duplicate_id").on('click', function () {
        if (!blankCheck(inputMemberId)) {
            popUpOpen('아이디를 입력해 주세요.');
            return false;
        }
        if (!spaceCheck(inputMemberId)) {
            popUpOpen('아이디에 공백이 입력되었습니다.');
            return false;
        }
        if (!idValidationCheck(inputMemberId.val())) {
            popUpOpen('아이디는 영문, 숫자로 이루어진 5~20자의 아이디만 입력 가능합니다.');
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
                    popUpOpen('이미 사용 중인 아이디입니다.')
                    duplicateIdCheckYn = false;
                } else {
                    popUpOpen('사용 가능한 아이디입니다.')
                    duplicateIdCheckYn = true;
                }
            },
            error: function (error) {
                console.log(error);
                popUpOpen("처리 중 오류가 발생하였습니다.");
            }
        })
    });

    $("#signup_duplicate_nick_name").on('click', function () {
        if (!blankCheck($("#nickName"))) {
            popUpOpen('닉네임을 입력해 주세요.');
            return false;
        }
        if (!nickNameValidationCheck(inputNickName.val())) {
            popUpOpen('닉네임은 한영자 숫자 기호 입력 가능하며, 1~20자의 닉네임만 입력 가능합니다.');
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
                    popUpOpen('이미 사용 중인 닉네임입니다.')
                    duplicateNickNmCheckYn = false;
                } else {
                    popUpOpen('사용 가능한 닉네임입니다.')
                    duplicateNickNmCheckYn = true;
                }
            },
            error: function (error) {
                console.log(error);
                popUpOpen("처리 중 오류가 발생하였습니다.");
            }
        })
    });

    $("#signup_duplicate_email").on('click', function () {
        if (!blankCheck(inputEmail)) {
            popUpOpen('이메일을 입력해 주세요.');
            return false;
        }
        if (!emailValidationCheck(inputEmail.val())) {
            popUpOpen('이메일이 형식에 맞지 않습니다.');
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
                    popUpOpen('이미 사용 중인 이메일입니다.')
                    duplicateEmailCheckYn = false;
                } else {
                    popUpOpen('사용 가능한 이메일입니다.')
                    duplicateEmailCheckYn = true;
                }
            },
            error: function (error) {
                console.log(error);
                popUpOpen("처리 중 오류가 발생하였습니다.");
            }
        })
    });

    function duplicationCheck() {
        if (!duplicateIdCheckYn) {
            popUpOpen('아이디 중복확인이 필요합니다.')
            return false;
        }

        if (!duplicateNickNmCheckYn) {
            popUpOpen('닉네임 중복확인이 필요합니다.')
            return false;
        }

        if (!duplicateEmailCheckYn) {
            popUpOpen('이메일 중복확인이 필요합니다.')
            return false;
        }
        return true;
    }

    $(".signup_complete").on('click', function () {
        if (!formBlankCheck() || !formSpaceCheck() || !formValidationCheck() || !duplicationCheck()) {
            return false;
        }

        if (isAjaxProcessing) {
            popUpOpen('이전 요청을 처리중 입니다. 잠시 후 다시 시도하세요.');
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
                    window.location.replace("/forward/members/signUpResult.trip");
                } else {
                    popUpOpen(result.message);
                }
            },
            error: function (error) {
                isAjaxProcessing = false;
                console.log(error);
                popUpOpen("처리 중 오류가 발생하였습니다.");
            }
        })
        return true;
    });
});