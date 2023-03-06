function checkPopUpOpen(message) {
    const popupWrap = $(".check_popup_wrap");

    popupWrap.css("position", "absolute");
    popupWrap.css("top", (($(window).height() - popupWrap.outerHeight()) / 3));
    popupWrap.css("left", (($(window).width() - popupWrap.outerWidth()) / 2) + $(window).scrollLeft());
    $(".check_popup_message").text(message);
    popupWrap.show();
}

function popUpCancel() {
    $(".check_popup_wrap").hide();
}

function popUpOk(url) {
    $(".check_popup_wrap").hide();

    if (url) {
        pageReplace(url);
    }
}
