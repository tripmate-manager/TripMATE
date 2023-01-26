$(function () {
    let isAjaxProcessing = false;
    const inputMemberName = $("#memberName");
    const inputEmail = $("#email");

    inputEmail.on('keyup', function () {
        const inputEmail = $(this).val();

        if (!emailValidationCheck(inputEmail)) {
            $("#checkEmailMessage").show();
        } else {
            $("#checkEmailMessage").hide();
        }
    });

    function formBlankCheck() {
        if (!blankCheck(inputMemberName)) {
            popUpOpen('아이디를 입력해 주세요.');
            return false;
        }

        if (!blankCheck(inputEmail)) {
            popUpOpen('이메일을 입력해 주세요.');
            return false;
        }

        return true;
    }

    function formSpaceCheck() {
        if (!spaceCheck(inputMemberName)) {
            popUpOpen('이름에 공백이 입력되었습니다.');
            return false;
        }

        if (!spaceCheck(inputEmail)) {
            popUpOpen('이메일에 공백이 입력되었습니다.');
            return false;
        }

        return true;
    }

    function formValidationCheck() {
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

    $(".find_id_button").on('click', function () {
        // if (!formBlankCheck()) {
        //     return false;
        // }
        // if (!formSpaceCheck()) {
        //     return false;
        // }
        // if (!formValidationCheck()) {
        //     return false;
        // }

        if (isAjaxProcessing) {
            popUpOpen('이전 요청을 처리중 입니다. 잠시 후 다시 시도하세요.');
            return;
        } else {
            isAjaxProcessing = true;
        }

        $.ajax({
            url: "/members/signIn/findId.trip",
            type: "get",
            dataType: 'json',
            data: {
                memberName: inputMemberName.val(),
                email: inputEmail.val()
            },
            success: function (result) {
                isAjaxProcessing = false;

                if (result.code === constCode.global.resultCodeSuccess) {
                    popUpOpen("사용자의 아이디는 " + maskingId(result.memberId) + "입니다.");
                } else if (result.code === "9001") {
                    popUpOpen("존재하지 않는 회원 정보입니다.");
                } else {
                    popUpOpen(result.message);
                }
            },
            error: function (error) {
                isAjaxProcessing = false;
                popUpOpen("처리 중 오류가 발생하였습니다.");
            }
        })
    });

    function maskingId(memberId) {
        if (memberId === undefined || memberId === '') {
            return '';
        }
        const pattern = /.{4}$/; // 정규식
        return memberId.replace(pattern, "****");
    }
});