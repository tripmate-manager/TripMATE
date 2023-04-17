const drawStar = (target) => {
    document.querySelector(`.star .` + target.id).style.width = `${target.value * 10}%`;
    $(target).parent().parent().find(".review_score").val(target.value / 2);
};

$(function () {
    const postTypeCode = document.getElementById("post_type_code").value;

    let isAjaxProcessing = false;
    let reviewImageCnt = document.querySelector('.create_review_input_img_list').childElementCount - 1;

    switch (postTypeCode) {
        case constCode.global.postTypeCodeEtc:
            $("#review_score_all_wrap").show();
            break;
        case constCode.global.postTypeCodeRestaurant:
            $("#review_score_location_wrap").show();
            $("#review_score_amount_wrap").show();
            $("#review_score_facility_wrap").show();
            $("#review_score_sanitary_wrap").show();
            break;
        case constCode.global.postTypeCodeTour:
            $("#review_score_location_wrap").show();
            $("#review_score_amount_wrap").show();
            break;
        case constCode.global.postTypeCodeLodging:
            $("#review_score_food_wrap").show();
            $("#review_score_amount_wrap").show();
            $("#review_score_service_wrap").show();
            $("#review_score_sanitary_wrap").show();
            break;
    }

    document.querySelector(".icon_arrow_left").addEventListener('click', (e) => {
        checkPopUpOpen("작성한 내용은 저장되지 않습니다.\n" + "작성을 취소하시겠습니까?");
        $(".check_popup_btn_ok").attr("onclick", null).on('click', function () {
            history.back();
        });
    });

    document.getElementById("review_save").addEventListener('click', (e) => {
        if (document.getElementById("create_review_input_contents").value.length > 500) {
            popUpOpen("리뷰는 최대 500자까지 입력가능합니다.");
            return false;
        }

        if (isAjaxProcessing) {
            popUpOpen('이전 요청을 처리중 입니다. 잠시 후 다시 시도하세요.');
            return;
        } else {
            isAjaxProcessing = true;
        }
        document.getElementById("review_average_score").value = getReviewAverageScore();

        $.ajax({
            url: '/review/createReview/callApi.trip',
            type: 'post',
            dataType: 'json',
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            data: new FormData($('#createReviewForm')[0]),
            success: function (result) {
                isAjaxProcessing = false;
                $(".check_popup_wrap").hide();

                if (result.code === constCode.global.resultCodeSuccess) {
                    if (result.createReviewNo > 0) {
                        popUpOpen("리뷰가 저장되었습니다.");

                        $(".popup_close_btn").attr("onclick", null).on('click', function () {
                            history.back();
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

    function getReviewAverageScore() {
        let reviewAverageScore = 0;
        $('.review_score').each(function () {
            reviewAverageScore += parseFloat(this.value);
        });

        if (document.getElementById("post_type_code").value === constCode.global.postTypeCodeTour) {
            reviewAverageScore /= 2;
        } else if (document.getElementById("post_type_code").value === constCode.global.postTypeCodeLodging
            || document.getElementById("post_type_code").value === constCode.global.postTypeCodeRestaurant) {
            reviewAverageScore /= 4;
        }

        return reviewAverageScore;
    }

    document.getElementById("img_select").addEventListener('click', (e) => {
        if (reviewImageCnt >= 5) {
            document.getElementById("img_select").setAttribute("disabled", "");
            popUpOpen('이미지는 5개까지 첨부 가능합니다.');
        }
    });

    $('#img_select').change(function () {
        if (reviewImageCnt >= 5) {
            popUpOpen('이미지는 5개까지 첨부 가능합니다.');
            return false;
        }

        setImageFromFile(this);
        document.getElementById("review_img_input_count").innerText = reviewImageCnt.toString();
    });

    function setImageFromFile(input) {
        const reviewImages = $('.create_review_input_img_list');

        for (let i = 0; i < input.files.length; i++) {
            if (input.files && input.files[i]) {
                var reader = new FileReader();

                reader.onload = function (e) {
                    let imageItem = $('<div class="create_review_preview_img_wrap">' +
                        '<img src="' + e.target.result + '" class="review_img_preview" id="preview"/></div>');
                    reviewImages.append(imageItem);
                }
                reader.readAsDataURL(input.files[i]);
                reviewImageCnt++;
            }
        }
    }
});