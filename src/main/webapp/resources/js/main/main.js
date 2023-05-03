window.onhashchange = function () {
    if (location.hash !== "#open") {
        $("#menu,.page_cover,html").removeClass("open");
    }
};

$(function () {
    const sessionMemberInfo = $("#memberInfo").val();
    const sessionInviteCode = $("#invitePlanNo").val();
    const searchText = document.getElementById("main_search_text");

    if (sessionMemberInfo) {
        $(".icon_alarm_wrap").show();
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
        $("#mainForm").attr("action", "/main/notificationList.trip").submit();
    });

    searchText.addEventListener('click',function () {
        $("#mainForm").attr("method", "get").attr("action", "/searchPlan/search.trip").submit();
    })
});
