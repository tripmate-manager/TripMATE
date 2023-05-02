let isAjaxProcessing = false;

const drawStar = (target) => {
    document.querySelector(`.star .` + target.id).style.width = `${target.value * 10}%`;
};

function deleteReview(reviewNo, dailyPlanNo) {
    if (isAjaxProcessing) {
        popUpOpen('이전 요청을 처리중 입니다. 잠시 후 다시 시도하세요.');
        return;
    } else {
        isAjaxProcessing = true;
    }

    $.ajax({
        url: "/review/deleteReview.trip",
        type: "post",
        dataType: 'json',
        data: {
            planNo: document.getElementById("planNo").value,
            reviewNo: reviewNo,
            memberNo: document.getElementById("memberNo").value,
            dailyPlanNo: dailyPlanNo
        },
        success: function (result) {
            isAjaxProcessing = false;

            if (result.code === constCode.global.resultCodeSuccess) {
                if (result.isDeleteReviewSuccess === true) {
                    popUpOpen('리뷰가 삭제되었습니다.');

                    $(".popup_close_btn").attr("onclick", null).on('click', function () {
                        $("#reviewListForm").attr("action", "/review/reviewList.trip").submit();
                    });
                }
            } else {
                popUpOpen(result.message);
            }
        },
        error: function (error) {
            popUpOpen("처리 중 오류가 발생하였습니다.");
        }
    })
}

$(function () {
    const postTypeCode = document.getElementById("postTypeCode").value;

    if (postTypeCode) {
        $("div[class='reviewlist_list_wrap']").each(function () {
            switch (postTypeCode) {
                case constCode.global.postTypeCodeEtc:
                    $(this).find("#review_score_all_wrap").show();
                    break;
                case constCode.global.postTypeCodeRestaurant:
                    $(this).find("#review_score_location_wrap").show();
                    $(this).find("#review_score_amount_wrap").show();
                    $(this).find("#review_score_facility_wrap").show();
                    $(this).find("#review_score_sanitary_wrap").show();
                    break;
                case constCode.global.postTypeCodeTour:
                    $(this).find("#review_score_location_wrap").show();
                    $(this).find("#review_score_amount_wrap").show();
                    break;
                case constCode.global.postTypeCodeLodging:
                    $(this).find("#review_score_food_wrap").show();
                    $(this).find("#review_score_amount_wrap").show();
                    $(this).find("#review_score_service_wrap").show();
                    $(this).find("#review_score_sanitary_wrap").show();
                    break;
            }
        });
    }

    $("#icon_arrow_left").on('click', function () {
        $("#reviewListForm").attr("action", "/dailyPlans/dailyPlan.trip").submit();
    });
});
