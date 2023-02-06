$(document).ready(function () {
    $(".main_menu_button").click(function () {
        $("#menu,.page_cover,html").addClass("open");
        window.location.hash = "#open";
    })

    $("#menu ul.sub_mobile").hide();
    $("#menu ul.nav li").click(function () {
        $("ul", this).slideToggle("fast");
    })
})

window.onhashchange = function () {
    if (location.hash !== "#open") {
        $("#menu,.page_cover,html").removeClass("open");
    }
};

function logout() {
    checkPopUpOpen("로그아웃 하시겠습니까?");
}

$(function () {
    $("#menu_logout").on('click', function () {
        checkPopUpOpen("로그아웃 하시겠습니까?");
    });
});
