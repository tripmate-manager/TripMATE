$(function () {
    let isAjaxProcessing = false;
    const inputMemberEmail = $("#memberEmail");

    inputMemberEmail.on('keyup', function () {
        const inputEmail = $(this).val();

        if (!emailValidationCheck(inputEmail)) {
            $("#checkEmailMessage").show();
        } else {
            $("#checkEmailMessage").hide();
        }
    });

    $(".find_password_button").on('click', function () {
        if (!blankCheck(inputMemberEmail)) {
            popUpOpen('이메일을 입력해 주세요.');
            return false;
        }

        if (!spaceCheck(inputMemberEmail)) {
            popUpOpen('이메일에 공백이 입력되었습니다.');
            return false;
        }

        if (!emailValidationCheck(inputMemberEmail.val())) {
            popUpOpen('잘못된 이메일 형식입니다.');
            return false;
        }

        if (isAjaxProcessing) {
            popUpOpen('이전 요청을 처리중 입니다. 잠시 후 다시 시도하세요.');
            return;
        } else {
            isAjaxProcessing = true;
        }

        // $.ajax({
        //     url: "/members/sendPasswordMail.trip",
        //     type: "post",
        //     dataType: 'json',
        //     data: {
        //         memberId: inputMemberId.val(),
        //         to: inputEmail.val()
        //     },
        //     success: function (result) {
        //         isAjaxProcessing = false;
        //
        //         if (result.code === constCode.global.resultCodeSuccess) {
        //             if (result.sendMailSuccess === true) {
        //                 popUpOpen("인증메일을 전송하였습니다. 이메일을 확인해주세요.");
        //                 $(".popup_close_btn").attr("onclick", null);
        //                 $(".popup_close_btn").on('click', function () {
        //                     popUpClose("/forward/members/signIn.trip");
        //                 });
        //             } else {
        //                 popUpOpen("메일 전송 중 오류가 발생하였습니다.");
        //             }
        //         } else {
        //             popUpOpen(result.message);
        //         }
        //     },
        //     error: function (error) {
        //         isAjaxProcessing = false;
        //         console.log(error);
        //         popUpOpen("처리 중 오류가 발생하였습니다.");
        //     }
        // })
    });
});