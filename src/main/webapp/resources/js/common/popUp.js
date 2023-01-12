function popUpOpen(message) {
    $(".popup__wrap").css("position", "absolute");
    $(".popup__wrap").css("top", (($(window).height() - $(".popup__wrap").outerHeight()) / 3) + $(window).scrollTop());
    $(".popup__wrap").css("left", (($(window).width() - $(".popup__wrap").outerWidth()) / 2) + $(window).scrollLeft());
    $(".popup_message").text(message);
    $(".popup__wrap").show();
}

function popUpClose() {
    $(".popup__wrap").hide();
}