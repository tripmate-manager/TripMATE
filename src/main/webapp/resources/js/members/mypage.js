$(function () {
    let isAjaxProcessing = false;
    let duplicateNickNmCheckYn = true;

    const memberGenderCode = $("#memberGenderCode");
    const genderFemaleBtn = $(".btn-primary");
    const genderMaleBtn = $(".btn-default");
    const inputNickName = $("#nickName");
    const inputBirthDay = $("#birthDay");
    const sessionNickName = $("#memberNickName");
    const sessionBirthDay = $("#memberBirthDay");
    const sessionGenderCode = $("#memberGenderCode");

    inputNickName.change(function () {
        duplicateNickNmCheckYn = false;
    });

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

    function formBlankCheck() {
        if (!blankCheck(inputNickName)) {
            popUpOpen('닉네임을 입력해 주세요.');
            return false;
        }

        if (!blankCheck(inputBirthDay)) {
            popUpOpen('생년월일을 입력해 주세요.');
            return false;
        }

        return true;
    }

    function formValidationCheck() {
        if (!nickNameValidationCheck(inputNickName.val())) {
            popUpOpen('닉네임은 한영자 숫자 기호 입력 가능하며, 1~20자의 닉네임만 입력 가능합니다.');
            return false;
        }

        if (!birthDayValidationCheck(inputBirthDay.val())) {
            popUpOpen('생년월일은 YYYYMMDD 형태만 입력 가능합니다.');
            return false;
        }

        return true;
    }

    $(".mypage_nick_name_duplicate").on('click', function () {
        if (inputNickName.val() === sessionNickName.val()) {
            popUpOpen('새로운 닉네임을 입력해주세요.');
            return false;
        }

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
                nickName: inputNickName.val()
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
                popUpOpen("처리 중 오류가 발생하였습니다.");
            }
        })
    });

    function formChangeCheck() {
        if (inputNickName.val() === sessionNickName.val()
            && inputBirthDay.val() === sessionBirthDay.val()
            && memberGenderCode.val() === sessionGenderCode.val()) {
            popUpOpen("수정된 항목이 없습니다.");
            return false;
        }
        return true;
    }

    $(".mypage_edit_save").on('click', function () {
        if (!formChangeCheck()) {
            return false;
        }

        if (!formBlankCheck() || !formValidationCheck()) {
            return false;
        }

        if (!duplicateNickNmCheckYn) {
            popUpOpen('닉네임 중복확인이 필요합니다.')
            return false;
        }

        if (isAjaxProcessing) {
            popUpOpen('이전 요청을 처리중 입니다. 잠시 후 다시 시도하세요.');
            return;
        } else {
            isAjaxProcessing = true;
        }

        $.ajax({
            url: "/members/editMemberInfo.trip",
            type: "post",
            dataType: 'json',
            data: {
                nickName: inputNickName.val(),
                birthDay: inputBirthDay.val(),
                genderCode: memberGenderCode.val()
            },
            success: function (result) {
                isAjaxProcessing = false;
                if (result.code === constCode.global.resultCodeSuccess) {
                    popUpOpen('회원 정보 수정이 완료되었습니다.');
                } else {
                    popUpOpen(result.message);
                }
            },
            error: function (error) {
                isAjaxProcessing = false;
                popUpOpen("처리 중 오류가 발생하였습니다.");
            }
        })
        return true;
    });
});