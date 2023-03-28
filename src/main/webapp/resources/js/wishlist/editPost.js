$(function () {
    let isAjaxProcessing = false;

    document.querySelector(".icon_arrow_left").addEventListener('click', (e) => {
        checkPopUpOpen("작성한 내용은 저장되지 않습니다.\n" + "작성을 취소하시겠습니까?");
        document.querySelector(".check_popup_btn_ok").setAttribute('onClick', null);
        document.querySelector(".check_popup_btn_ok").addEventListener('click', () => {
            history.back();
        })
    });

    const postTypeCode = document.getElementById("postTypeCode").value;

    switch (postTypeCode) {
        case constCode.global.postTypeCodeLodging:
            document.querySelector(".edit_post_spot_info_wrap").style.display = 'block';
            break;
        case constCode.global.postTypeCodeTour:
            document.querySelector(".edit_post_spot_info_wrap").style.display = 'block';
            document.querySelector(".input_amount_wrap").style.display = 'block';
            document.querySelector(".input_business_hours_wrap").style.display = 'block';
            break;
        case constCode.global.postTypeCodeRestaurant:
            document.querySelector(".edit_post_spot_info_wrap").style.display = 'block';
            document.querySelector("#input_main_menu_wrap").style.display = 'block';
            document.querySelector("#input_business_hours_wrap").style.display = 'block';
            break;
        case constCode.global.postTypeCodeEtc:
            document.querySelector("#input_title_wrap").style.display = 'block';
            document.querySelector("#input_remark_wrap").style.display = 'block';
            break;
    }

    document.getElementById("edit_post_save").addEventListener('click', () => {
        switch (document.getElementById("postTypeCode")) {
            case constCode.global.postTypeCodeLodging:
            case constCode.global.postTypeCodeTour:
            case constCode.global.postTypeCodeRestaurant:
                if ($("#spotName").val().trim() === "" || $("#spotAddress").val().trim() === "") {
                    popUpOpen("장소 정보를 입력해주세요.");
                    return false;
                }
                break;
            case constCode.global.postTypeCodeEtc:
                if ($("#postTitle").val().trim() === "") {
                    popUpOpen("제목을 입력해주세요.");
                    return false;
                }

                if ($("#postTitle").val().length > 100) {
                    popUpOpen("제목은 최대 100자까지 입력가능합니다.");
                    return false;
                }

                if ($("#remark").val().length >= 200) {
                    popUpOpen("특이사항은 최대 200자까지 입력가능합니다.");
                    return false;
                }

                break;
        }
        if ($("#postContents").val().trim() === "") {
            popUpOpen("본문을 입력해주세요.");
            return false;
        }

        if (isAjaxProcessing) {
            popUpOpen('이전 요청을 처리중 입니다. 잠시 후 다시 시도하세요.');
            return;
        } else {
            isAjaxProcessing = true;
        }

        $.ajax({
            url: "/wishlist/editPost/callApi.trip",
            type: "post",
            dataType: 'json',
            data: $("#editPostForm").serialize(),
            success: function (result) {
                isAjaxProcessing = false;

                if (result.code === constCode.global.resultCodeSuccess) {
                    if (result.isUpdatePostSuccess === true) {
                        $("#editPostForm").attr("action", "/wishlist/wishlist.trip").submit();
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

    $(".edit_post_search").on('click', function () {
        //todo: 지도 api 연결
    });
});