function authorityPopUpOpen(planNo) {
    const popupWrap = $(".authority_popup_wrap");
    const selectOptionMate = $('#select_option_mate');

    $("#planNo").val(planNo);

    $.ajax({
        type: 'get',
        url: '/plans/planMate.trip',
        dataType: 'json',
        data: {
            planNo: planNo
        },
        success: function (result) {
            selectOptionMate.empty();

            const fragment = $(document.createDocumentFragment());

            for (i = 0; i < result.planMateList.length; i++) {
                const jsonOptionObject = JSON.parse(JSON.stringify(result.planMateList[i]));

                if (jsonOptionObject.memberNo.toString() !== $("#memberNo").val()) {
                    fragment.append('<option value="' + jsonOptionObject.mateNo + '">' + jsonOptionObject.nickName + '</option>');
                }
            }
            selectOptionMate.append(fragment);
        },
        error: function (error) {
            popUpOpen("처리 중 오류가 발생하였습니다.");
        }
    })

    popupWrap.css("position", "absolute");
    popupWrap.css("top", (($(window).height() - popupWrap.outerHeight()) / 3) + $(window).scrollTop());
    popupWrap.css("left", (($(window).width() - popupWrap.outerWidth()) / 2) + $(window).scrollLeft());
    popupWrap.show();
}

function popUpCancel() {
    $(".authority_popup_wrap").hide();
}

function popUpOk() {
    const optionMateNo = $("#select_option_mate option:selected").val();

    $.ajax({
        type: 'post',
        url: '/plans/exitPlan.trip',
        dataType: 'json',
        data: {
            mateNo: optionMateNo,
            planNo: $("#planNo").val(),
            memberNo: $("#memberNo").val()
        },
        success: function (result) {
            if (result.isExitPlanMate === true) {
                location.reload();
            }
        },
        error: function (error) {
            popUpOpen("처리 중 오류가 발생하였습니다.");
        }
    })
}