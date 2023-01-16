function popUpOpen(message) {
    const popupWrap = $(".popup__wrap");

    popupWrap.css("position", "absolute");
    popupWrap.css("top", (($(window).height() - popupWrap.outerHeight()) / 3) + $(window).scrollTop());
    popupWrap.css("left", (($(window).width() - popupWrap.outerWidth()) / 2) + $(window).scrollLeft());
    $(".popup_message").text(message);
    popupWrap.show();
}

function popUpClose() {
    popupWrap.hide();
}