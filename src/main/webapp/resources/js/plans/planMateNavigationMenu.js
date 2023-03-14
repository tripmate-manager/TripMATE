$(function () {
    const planNo = $(".plan_main_plan_no").val();

    $(".planmate_menu_invite").on('click', function () {
        history.back();
        inviteMatePopUpOpen(planNo);
    });
});