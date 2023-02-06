$(function () {
    $(".main_menu_login_text").on('click', function () {
        window.location.href = "../members/signIn.trip";
    });

    $("#menu_edit_member_info").on('click', function () {
        window.location.href = "../members/mypage/mypage.trip";
    });

    $("#menu_logout").on('click', function () {
        checkPopUpOpen("로그아웃 하시겠습니까?");
    });

    $("#menu_setting").on('click', function () {
        window.location.href = "../members/setting/setting.trip";
    });
});