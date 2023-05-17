let isAjaxProcessing = false;

$(function () {
    $(".icon_arrow_left").on('click', function () {
        pageLink("/main/main.trip");
    });

    document.getElementById("icon_arrow_left").addEventListener('click', function () {
        pageLink("/main/main.trip");
    });

    document.querySelectorAll(".myplanlike_item_contents_wrap").forEach(function (resultItem) {
        resultItem.addEventListener('click', function (event) {
            document.getElementById("input_plan_no").value = resultItem.firstElementChild.getAttribute("value");

            if (event.target.className !== "checkboxHeart") {
                $("#myPlanLikeForm").attr("action", "/plans/planMain.trip").submit();
            }
        })
    })

    $("input.checkboxHeart").on('click', function () {
        if (isAjaxProcessing) {
            popUpOpen('이전 요청을 처리중 입니다. 잠시 후 다시 시도하세요.');
            return;
        } else {
            isAjaxProcessing = true;
        }

        console.log(this);
        clickPlanLike(this,
            document.getElementById("input_plan_no").value,
            document.getElementById("input_member_no").value);
    });
});