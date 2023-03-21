$(function () {
    let isAjaxProcessing = false;
    let postTypeCode = $(".select_option_post_type option:selected").val();

    $("#postTypeCode").val(postTypeCode);

    $(".icon_arrow_left").on('click', function () {
        checkPopUpOpen("작성한 내용은 저장되지 않습니다.\n" + "작성을 취소하시겠습니까?");
        $(".check_popup_btn_ok").attr("onclick", null).on('click', function () {
            history.back();
        });
    });

    $("#select_option_post_type").change(function () {
        const postTypeCodeSelected = $(".select_option_post_type option:selected").val();

        if (postTypeCodeSelected !== postTypeCode) {
            postTypeCode = postTypeCodeSelected;
            $("#postTypeCode").val(postTypeCode);

            $("#createPostForm")[0].reset();
            $(".create_post_spot_info_wrap").hide();
            $("div[class='create_post_input_item_wrap']").hide();
            $("#input_information_url_wrap").show();

            switch (postTypeCodeSelected) {
                case constCode.global.postTypeCodeLodging:
                    $(".create_post_spot_info_wrap").show();
                    break;
                case constCode.global.postTypeCodeTour:
                    $(".create_post_spot_info_wrap").show();
                    $("#input_amount_wrap").show();
                    $("#input_tour_business_hours_wrap").show();
                    break;
                case constCode.global.postTypeCodeRestaurant:
                    $(".create_post_spot_info_wrap").show();
                    $("#input_main_menu_wrap").show();
                    $("#input_restaurant_business_hours_wrap").show();
                    break;
                case constCode.global.postTypeCodeEtc:
                    $("#input_title_wrap").show();
                    $("#input_remark_wrap").show();
                    break;
            }
        }
    });

    $(".create_post_save").on('click', function () {
        switch (postTypeCode) {
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
            url: "/wishlist/createPost/callApi.trip",
            type: "post",
            dataType: 'json',
            data: $("#createPostForm").serialize(),
            success: function (result) {
                isAjaxProcessing = false;
                if (result.code === constCode.global.resultCodeSuccess) {
                    if (result.createPostNo != null && result.createPostNo !== "0") {
                        popUpOpen("게시글이 위시리스트에 저장되었습니다.");
                        //todo: 추후 게시글 메인 페이지로 이동하도록 수정
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

    $(".create_post_search").on('click', function () {
        //todo: 지도 api 연결
    });
});