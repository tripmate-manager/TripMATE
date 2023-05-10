function address_option(option) {
    address_comboBox(option, $("#select_option_sigungu"));
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
            $("#createPlanForm").attr("method", "get").attr("action", "/plans/myPlan.trip").submit();
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

        $("div[class='hashtag_item_text']").each(function () {
            if (inputHashtag.val().trim() === $(this).text().trim()) {
                popUpOpen('이미 입력된 해시태그입니다.');
                isHashtagDuplicate = true;
            }
        })

        if (!isHashtagDuplicate) {
            let optionItem = $('<div class="hashtag_item_text">' + inputHashtag.val().trim() + '</div>');
            $(".createplan_hashtag_list").append(optionItem);
        }

        inputHashtag.val("");

        if ($("div[class='hashtag_item_text']").length > 0) {
            $(".createplan_hashtag_list").show();
        }
    });

    $(".createplan_hashtag_list").on('click', '.hashtag_item_text', function () {
        $(this).remove();

        if ($("div[class='hashtag_item_text']").length == 0) {
            $(".createplan_hashtag_list").hide();
        }
    });

    $("#select_address_btn").on('click', function () {
        const inputAddressNo = $("#select_option_sigungu option:selected").val();
        const inputSido = $("#select_option_sido option:selected").val();
        const inputSigungu = $("#select_option_sigungu option:selected").text();
        let isAddressDuplicate = false;

        if ($("div[class='address_item_text']").length === 3) {
            popUpOpen("여행지는 최대 3개까지만 선택할 수 있습니다.");
            return;
        }

        if (inputSido === 'default' || inputAddressNo === 'default') {
            popUpOpen("여행지를 선택해주세요.");
            return false;
        }

        $("div[class='address_item_text']").each(function (item) {
            if (inputAddressNo === $(this).attr("value")) {
                popUpOpen("이미 입력된 여행지입니다.");
                isAddressDuplicate = true;
            }
        });

        if (!isAddressDuplicate) {
            let optionItem = $('<div class="address_item_text" value="' + inputAddressNo + '">' + inputSido + " " + inputSigungu + '</div>');
            $(".createplan_address_list").append(optionItem);
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

    $(".createplan_address_list").on('click', '.address_item_text', function () {
        $(this).remove();

        if ($("div[class='address_item_text']").length == 0) {
            $(".createplan_address_list").hide();
        }
    });

    $(".createplan_save").on('click', function () {
        if ($('#checkboxPublic').is(':checked')) {
            inputPublicYn = constCode.global.Y;
        } else {
            inputPublicYn = constCode.global.N;
        }

        if (!formBlankCheck() || !formValidationCheck()) {
            return false;
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

        if (planAddressList.length === 0) {
            popUpOpen('여행지를 입력해주세요.');
            return false;
        }

        if (isAjaxProcessing) {
            popUpOpen('이전 요청을 처리중 입니다. 잠시 후 다시 시도하세요.');
            return;
        } else {
            isAjaxProcessing = true;
        }

        $.ajax({
            url: "/plans/createPlan.trip",
            type: "post",
            traditional: true,
            dataType: 'json',
            data: {
                memberNo: sessionMemberNo.val(),
                planTitle: inputPlanTitle.val().trim(),
                planDescription: inputPlanDescription.val().trim(),
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
                    if (result.createPlanNo > 0) {
                        checkPopUpOpen("플랜이 생성되었습니다. \n" + "플랜메이트를 추가하시겠습니까?");
                        $(".check_popup_btn_ok").attr("onclick", null).on('click', function () {
                            popUpCancel();
                            inviteMatePopUpOpen(result.createPlanNo);

                            $(".invite_complete").attr("onclick", null).on('click', function () {
                                $("#inviteMatePopUpForm").attr("action", "/plans/planMain.trip").submit();
                            });
                        });
                        $(".check_popup_btn_cancel").attr("onclick", null).on('click', function () {
                            $("#createPlanForm").attr("action", "/plans/myPlan.trip").submit();
                        });
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