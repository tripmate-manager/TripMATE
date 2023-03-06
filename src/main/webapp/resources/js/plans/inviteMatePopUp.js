function inviteMatePopUpOpen(planNO) {
    const popupWrap = $(".invite_mate_popup_wrap");

    $("#planNo").val(planNO);
    popupWrap.css("position", "absolute");
    popupWrap.css("top", (($(window).height() - popupWrap.outerHeight()) / 2));
    popupWrap.css("left", (($(window).width() - popupWrap.outerWidth()) / 2) + $(window).scrollLeft());
    popupWrap.show();
}

$(function () {
    let isAjaxProcessing = false;
    let isNonMemberMemnuClicked = false;

    const memberMenu = $("#tab_menu_member");
    const nonMemberMenu = $("#tab_menu_nonmember");
    const memberContents = $(".invite_planmate_member_wrap");
    const nonMemberContents = $(".invite_planmate_nonmember_wrap");
    const searchBtn = $(".icon_search");

    memberMenu.on('click', function () {
        if(memberMenu.hasClass("tab_select") === false) {
            memberMenu.addClass("tab_select");
            nonMemberMenu.removeClass("tab_select");
        }

        memberContents.show();
        nonMemberContents.hide();
    });

    nonMemberMenu.off('click').on('click', function () {
        if(nonMemberMenu.hasClass("tab_select") === false) {
            nonMemberMenu.addClass("tab_select");
            memberMenu.removeClass("tab_select");
        }

        memberContents.hide();
        nonMemberContents.show();

        if (isNonMemberMemnuClicked === false) {
            const planNo = $("#planNo").val();

            if (isAjaxProcessing) {
                popUpOpen('이전 요청을 처리중 입니다. 잠시 후 다시 시도하세요.');
                return;
            } else {
                isAjaxProcessing = true;
            }

            $.ajax({
                url: "/plans/createInviteCode.trip",
                type: "post",
                dataType: 'json',
                data: {
                    planNo: planNo,
                    inviteTypeCode: constCode.global.inviteTypeCodeNonMember
                },
                success: function (result) {
                    isAjaxProcessing = false;
                    if (result.code === constCode.global.resultCodeSuccess) {
                        isNonMemberMemnuClicked = true;
                        $(".invite_nonmember_auth_code").text(result.inviteCode);
                    } else {
                        popUpOpen(result.message);
                    }
                },
                error: function (error) {
                    isAjaxProcessing = false;
                    popUpOpen("처리 중 오류가 발생하였습니다.");
                }
            })
        }
    });

    $(".icon_paste").on('click', function () {
        document.exec
    });

    searchBtn.off('click').on('click', function () {
        let searchDiviCode;
        const searchKeyword = $(".input_search_keyword").val();

        if (searchKeyword.trim() === "") {
            popUpOpen('검색어를 입력해주세요.');
            return;
        }

        if (isAjaxProcessing) {
            popUpOpen('이전 요청을 처리중 입니다. 잠시 후 다시 시도하세요.');
            return;
        } else {
            isAjaxProcessing = true;
        }

        const searchSelectBox = $(".search_member_selectbox option:selected").val();
        switch (searchSelectBox) {
            case 'id':
                searchDiviCode = constCode.global.memberSearchDiviCodeId;
                break;
            case 'nickname':
                searchDiviCode = constCode.global.memberSearchDiviCodeNickName;
                break;
            default:
                searchDiviCode = constCode.global.memberSearchDiviCodeEmail;
        }

        $.ajax({
            url: "/plans/searchMember.trip",
            type: "post",
            dataType: 'json',
            data: {
                searchDiviCode: searchDiviCode,
                searchKeyword: searchKeyword
            },
            success: function (result) {
                isAjaxProcessing = false;
                if (result.code === constCode.global.resultCodeSuccess) {
                    const searchResultWrap = $(".search_member_result_wrap");
                    const fragment = $(document.createDocumentFragment());

                    searchResultWrap.empty();
                    if (result.memberSearchResultList.length > 0) {
                        for (i = 0; i < result.memberSearchResultList.length; i++) {
                            const jsonSearchResultObject = JSON.parse(JSON.stringify(result.memberSearchResultList[i]));

                            fragment.append(
                                '<div class="search_member_result_item_wrap">' +
                                '<div class="result_item_info_wrap">' +
                                '<div class="result_item_member_no" value=' + jsonSearchResultObject.memberNo.toString() + '></div>' +
                                '<div class="result_item_id">' + jsonSearchResultObject.memberId + '</div>' +
                                '<div class="result_item_nickname">' + jsonSearchResultObject.nickName + '</div>' +
                                '<div class="result_item_invite_btn">초대하기</div>' +
                                '</div> ' +
                                '</div>'
                            );
                        }
                        searchResultWrap.append(fragment);
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