$(function () {

    $("#find_id").on('click', function () {
        window.location.href = "/forward/members/findId.trip";
    });

    $("#find_password").on('click', function () {
        window.location.href = "/forward/members/findPassword.trip";
    });

    $(".signin__menu_join").on('click', function () {
        window.location.href = "/forward/members/signUp.trip";
    });

    $(".signin_btn").on('click', function () {

    });
});