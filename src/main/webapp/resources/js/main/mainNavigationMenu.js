$(function () {
    const sessionMemberInfo = $("#memberInfo").val();
    const memberId = $("#memberId").val();
    const memberNickName = $("#memberNickName").val();

    if (sessionMemberInfo) {
        $(".main_menu_login_text").hide();
        $(".main_menu_login_info").show();

        $(".main_menu_login_id").text(memberId);
        $(".main_menu_login_nick_name").text(memberNickName);

        $(".main_menu_item_login").show();
    }

    $(".main_menu_login_text").on('click', function () {
        pageLink("/forward/members/signIn.trip");
    });

    $("#menu_my_plan").on('click', function () {
        if (!sessionMemberInfo) {
            popUpOpen("로그인 시 이용할 수 있는 서비스입니다.");
            return false;
        }
        pageLink("/plans/myPlan.trip");
    });

    $("#menu_my_like_plan").on('click', function () {
        if (!sessionMemberInfo) {
            popUpOpen("로그인 시 이용할 수 있는 서비스입니다.");
            return false;
        }

    });

    $("#menu_edit_member_info").on('click', function () {
        pageLink("/forward/members/mypage/mypage.trip");
    });

    $("#menu_logout").on('click', function () {
        checkPopUpOpen("로그아웃 하시겠습니까?");
        $(".check_popup_btn_ok").attr("onclick", null).on('click', function () {
            $("#checkPopUpForm").attr("action", "/members/signOut.trip").submit();
        });
    });

    $("#menu_setting").on('click', function () {
        pageLink("/forward/members/setting/setting.trip");
    });
});