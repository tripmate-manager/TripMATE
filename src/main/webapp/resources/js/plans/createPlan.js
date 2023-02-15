function address_option(option) {
    console.log(option);
    $.ajax({
        type: 'GET',
        url: '/plans/addressOption/'+ option + '.trip',
        dataType: 'json',
        data: {
            sidoName: option
        },
        success: function (result) {
            $('#select_option_sigungu').empty();

            for (i = 0; i < result.addressOptionList.length; i++) {
                const jsonOptionObject = JSON.parse(JSON.stringify(result.addressOptionList[i]));

                let option = $("<option value=" + jsonOptionObject.addressNo + ">" + jsonOptionObject.sigunguName + "</option>");
                $('#select_option_sigungu').append(option);
            }
        },
        error: function (error) {
            isAjaxProcessing = false;
            popUpOpen("처리 중 오류가 발생하였습니다.");
        }
    })
}

$(function () {
    let isAjaxProcessing = false;
    let inputPublicYn = $("#checkboxPublic").val();
    let isAddressInputFirst = true;

    const sessionMemberNo = $("#memberNo");
    const inputPlanTitle = $("#planTitle");
    const inputPlanDescription = $("#planDescription");
    const inputPlanAddress = $("#planAddress");
    const inputTripStartDate = $("#planStartDate");
    const inputTripEndDate = $("#planEndDate");
    const themeArrowDownBtn = $("#theme_arrow_down_btn");
    const hashtagArrowDownBtn = $("#hashtag_arrow_down_btn");
    const themeArrowUpBtn = $("#theme_arrow_up_btn");
    const hashtagArrowUpBtn = $("#hashtag_arrow_up_btn");
    const themeChoiceWrap = $(".plan_theme_choice_wrap");
    const hashtagChoiceWrap = $(".plan_hashtag_choice_wrap");

    themeArrowDownBtn.on('click', function () {
        themeArrowDownBtn.hide();
        themeChoiceWrap.show();
    });

    hashtagArrowDownBtn.on('click', function () {
        hashtagArrowDownBtn.hide();
        hashtagChoiceWrap.show();
    });

    themeArrowUpBtn.on('click', function () {
        themeArrowDownBtn.show();
        themeChoiceWrap.hide();
    });

    hashtagArrowUpBtn.on('click', function () {
        hashtagArrowDownBtn.show();
        hashtagChoiceWrap.hide();
    });

    $(".plan_address_choice_btn").on('click', function () {
        $(".createplan_trip_address").show();
    });

    $(".icon_arrow_left").on('click', function () {
        checkPopUpOpen("작성한 내용은 저장되지 않습니다.\n" + "작성을 취소하시겠습니까?");
        $(".check_popup_btn_ok").attr("onclick", null).on('click', function () {
            popUpOk("/forward/plans/myPlan.trip");
        });
    });

    $("input[id*='plan_theme_item']").on('click', function () {
        let count = $("input:checked[id*='plan_theme_item']").length;

        if (count > 3) {
            $(this).prop("checked", false);
            popUpOpen("여행테마는 최대 3개까지만 선택할 수 있습니다.");
        }
    });

    function formBlankCheck() {
        if (!blankCheck(inputPlanTitle)) {
            popUpOpen('플랜 제목을 입력해 주세요.');
            return false;
        }

        if (!blankCheck(inputPlanDescription)) {
            popUpOpen('플랜 설명을 입력해 주세요.');
            return false;
        }

        if (!blankCheck(inputTripStartDate) || !blankCheck(inputTripEndDate)) {
            popUpOpen('여행일자를 입력해 주세요.');
            return false;
        }

        return true;
    }

    function formValidationCheck() {
        if (!planTitleValidationCheck(inputPlanTitle)) {
            popUpOpen('플랜 제목은 최대 20자까지 입력가능합니다.');
            return false;
        }

        if (!planDescriptionValidationCheck(inputPlanDescription)) {
            popUpOpen('플랜 설명은 최대 100자까지 입력가능합니다.');
            return false;
        }

        if (!planDateStartEndCheck(inputTripStartDate, inputTripEndDate)) {
            popUpOpen('여행 시작일자와 종료일자를 확인해주세요.');
            return false;
        }

        return true;
    }

    $(".createplan_add_hashtag_btn").on('click', function () {
        const inputHashtag = $("#hashtag");
        let isHashtagDuplicate = false;

        if (!hashtagValidationCheck(inputHashtag)) {
            popUpOpen('해시태그는 최대 10자까지 입력 가능합니다.');
            return false;
        }

        $("div[class='hashtag_item_text']").each(function (item) {
            if (inputHashtag.val() === $(this).text()) {
                popUpOpen('이미 입력된 해시태그입니다.');
                isHashtagDuplicate = true;
            }
        })

        if (!isHashtagDuplicate) {
            let option = $("<div class=\"hashtag_item_text\">" + inputHashtag.val() + "</div>");
            $(".createplan_hashtag_list").append(option);
        }

        inputHashtag.val("");

        if ($("div[class='hashtag_item_text']").length > 0) {
            $(".createplan_hashtag_list").show();
        }
    });

    $("#select_address_btn").on('click', function () {
        const inputAddressNo = $("#select_option_sigungu option:selected").val();
        const inputSido = $("#select_option_sido option:selected").val();
        const inputSigungu = $("#select_option_sigungu option:selected").text();
        let isAddressDuplicate = false;

        if (inputSido === 'default' || inputAddressNo === 'default') {
            popUpOpen("여행지를 선택해주세요.");
            return false;
        }

        $("div[class='address_item_text']").each(function (item) {
            if (inputAddressNo === $(this).attr("value")) {
                popUpOpen("이미 입력된 여행지입니다.");
                isAddressDuplicate = true;
            };
        });

        if (!isAddressDuplicate) {
            let option = $("<div class=\"address_item_text\" value=" + inputAddressNo + ">" + inputSido + " " + inputSigungu + "</div>");
            $(".createplan_address_list").append(option);
            $("#planAddress").text(inputSido + " " + inputSigungu);

            if (isAddressInputFirst) {
                inputPlanAddress.attr('value', inputSido + " " + inputSigungu);
                isAddressInputFirst = false;
            }
        }

        if ($("div[class='address_item_text']").length > 0) {
            $(".createplan_address_list").show();
        }
    });

    $(".createplan_save").on('click', function () {
        if ($('#checkboxPublic').is(':checked')) {
            inputPublicYn = constCode.global.booleanTrue;
        } else {
            inputPublicYn = constCode.global.booleanFalse;
        }

        if (!formBlankCheck() || !formValidationCheck()) {
            return false;
        }

        if (isAjaxProcessing) {
            popUpOpen('이전 요청을 처리중 입니다. 잠시 후 다시 시도하세요.');
            return;
        } else {
            isAjaxProcessing = true;
        }

        let planThemeList = [];
        $("input[id*='plan_theme_item']:checked").each(function (item) {
            planThemeList.push($(this).val());
        });

        let planHashtagList = [];
        $("div[class='hashtag_item_text']").each(function (item) {
            planHashtagList.push($(this).text());
        });

        let planAddressList = [];
        $("div[class='address_item_text']").each(function (item) {
            planAddressList.push($(this).attr("value"));
        });

        if (planThemeList.length === 0) {
            popUpOpen('여행지를 입력해주세요.');
            return false;
        }

        $.ajax({
            url: "/plans.trip",
            type: "post",
            traditional: true,
            dataType: 'json',
            data: {
                memberNo: sessionMemberNo.val(),
                planTitle: inputPlanTitle.val(),
                planDescription: inputPlanDescription.val(),
                publicYn: inputPublicYn,
                tripStartDate: inputTripStartDate.val(),
                tripEndDate: inputTripEndDate.val(),
                planAddressList: planAddressList,
                planThemeList: planThemeList,
                planHashtagList: planHashtagList
            },
            success: function (result) {
                isAjaxProcessing = false;
                if (result.code === constCode.global.resultCodeSuccess) {
                    if (result.createPlanSuccess == true) {
                        pageReplace("/forward/plans/myPlan.trip");
                    }
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
});