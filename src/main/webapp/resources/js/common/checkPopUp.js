function checkPopUpOpen(message) {
    const popupWrap = $(".check_popup_wrap");

    popupWrap.css("position", "absolute");
    popupWrap.css("top", (($(window).height() - popupWrap.outerHeight()) / 3) + $(window).scrollTop());
    popupWrap.css("left", (($(window).width() - popupWrap.outerWidth()) / 2) + $(window).scrollLeft());
    $(".check_popup_message").text(message);
    popupWrap.show();
}

function popUpCancel() {
    const popupWrap = $(".check_popup_wrap");
    popupWrap.hide();
}

function popUpOk(url) {
    const popupWrap = $(".check_popup_wrap");
    popupWrap.hide();

    if (url) {
        window.location.replace(url);
    }
}
