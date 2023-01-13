let popupWrap = $(".popup__wrap");

function popUpOpen(message) {
    popupWrap.css("position", "absolute");
    popupWrap.css("top", (($(window).height() - popupWrap.outerHeight()) / 3) + $(window).scrollTop());
    popupWrap.css("left", (($(window).width() - popupWrap.outerWidth()) / 2) + $(window).scrollLeft());
    popupWrap.text(message);
    popupWrap.show();
}

function popUpClose() {
    popupWrap.hide();
}