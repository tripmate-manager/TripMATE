function bookMark(postNo) {
    let bookmarkCheckbox = document.getElementById("checkboxBookmark" + postNo);

    if (bookmarkCheckbox.hasAttribute('checked')) {
        bookmarkCheckbox.removeAttribute('checked');
    } else {
        addDailyPlanPopUpOpen(document.getElementById("wishlist_plan_no").value
            , postNo
            , document.getElementById("wishlist_trip_term").value
            , document.getElementById("wishlist_trip_start_date").value);

        bookmarkCheckbox.setAttribute('checked', '');
    }
}

$(function () {
    $("#icon_menu_wishlist").hide();
    $("#icon_menu_wishlist_choice").show();

    $(document).mouseup(function (e) {
        const popUp = $("#add_dailyplan_popup_wrap");
        if ($(popUp).has(e.target).length === 0) {
            $(popUp).hide();
        }
    });

    $(".icon_write").on('click', function () {
        $("#wishListForm").attr("action", "/wishlist/createPost.trip").submit();
    });

    $("#bottom_menu_home").on('click', function () {
        $("#wishListForm").attr("action", "/plans/planMain.trip").submit();
    });

    $(".wishlist_item_wrap").on('click', function () {
        const planNo = $(this).find("#wishlist_item_post_no").attr("value").toString();
        $("#wishlist_post_no").val(planNo);
        $("#wishListForm").attr("action", "/wishlist/postMain.trip").submit();
    });
});