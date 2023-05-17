let isAjaxProcessing = false;

window.onhashchange = function () {
    if (location.hash !== "#open") {
        $("#menu,.page_cover,html").removeClass("open");
    }
};

$(function () {
    let inputMemberNo = document.getElementById("input_member_no");

    $("#icon_menu_home").hide();
    $("#icon_menu_home_choice").show();

    $(".icon_arrow_left").on('click', function () {
        //todo: 페이지이동 수정
        pageLink(document.referrer);
    });

    if (document.getElementById("session_member_no") !== null) {
        inputMemberNo.value = document.getElementById("session_member_no").innerText;
    } else {
        inputMemberNo.value = 0;
    }

    $(".plan_main_plan_edit").on('click', function () {
        $("#planMainForm").attr("action", "/plans/editPlan.trip").submit();
    });

    $(".icon_plan_mate").click(function () {
        $("#menu,.page_cover,html").addClass("open");
        window.location.hash = "#open";
    })

    $("#menu ul.sub_mobile").hide();
    $("#menu ul.nav li").click(function () {
        $("ul", this).slideToggle("fast");
    })

    $("#bottom_menu_wishlist").on('click', function () {
        $("#planMainForm").attr("action", "/wishlist/wishlist.trip").submit();
    });

    $("#bottom_menu_checklist").on('click', function () {
        $("#planMainForm").attr("action", "/checkList/checkList.trip").submit();
    });

    $("#bottom_menu_account").on('click', function () {
        $("#planMainForm").attr("action", "/accountBook/accountBook.trip").submit();
    });

    $(".icon_arrow_right").on('click', function () {
        $("#dailyplan_day").val($(this).parent().find(".dailyplan_item_day").attr("value"));
        $("#planMainForm").attr("action", "/dailyPlans/dailyPlan.trip").submit();
    });

    document.querySelector(".checkboxHeart").addEventListener('click', function () {
        if (isAjaxProcessing) {
            popUpOpen('이전 요청을 처리중 입니다. 잠시 후 다시 시도하세요.');
            return;
        } else {
            isAjaxProcessing = true;
        }

        clickPlanLike(document.querySelector(".checkboxHeart"),
            document.getElementById("plan_no").value,
            document.getElementById("input_member_no").value);
    });
});