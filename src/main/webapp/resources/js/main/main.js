let isAjaxProcessing = false;

window.onhashchange = function () {
    if (location.hash !== "#open") {
        $("#menu,.page_cover,html").removeClass("open");
    }
};

$(function () {
    let inputMemberNo = document.getElementById("member_no");
    const sessionInviteCode = document.getElementById("invitePlanNo");
    const searchText = document.getElementById("main_search_text");
    const planLikeMore = document.getElementById("main_sub_title_more");

    sessionStorage.removeItem('planMainReferrer');

    if (document.getElementById("session_member_no") !== null) {
        inputMemberNo.value = document.getElementById("session_member_no").innerText;
    } else {
        inputMemberNo.value = 0;
    }

    if (sessionInviteCode) {
        inviteCodePopUpOpen();
    }

    $(".main_menu_button").click(function () {
        $("#menu,.page_cover,html").addClass("open");
        window.location.hash = "#open";
    });

    $("#menu ul.sub_mobile").hide();
    $("#menu ul.nav li").click(function () {
        $("ul", this).slideToggle("fast");
    })

    $(".icon_alarm").on('click', function () {
        pageLink("/main/notificationList.trip");
    });

    if (searchText) {
        searchText.addEventListener('click', function () {
            pageLink("/searchPlan/search.trip");
        })
    }

    if (planLikeMore) {
        planLikeMore.addEventListener('click', function () {
            pageLink("/plans/myPlanLike.trip");
        });
    }

});

function planLike(planNo) {
    if (isAjaxProcessing) {
        popUpOpen('이전 요청을 처리중 입니다. 잠시 후 다시 시도하세요.');
        return;
    } else {
        isAjaxProcessing = true;
    }
    clickPlanLike(this, planNo, document.getElementById("member_no").value);
}

function planMain(planNo) {
    if (this.event.target.className !== "checkboxHeart") {
        document.getElementById("plan_no").value = planNo;
        $("#mainForm").attr("action", "/plans/planMain.trip").submit();
    }
}
