$(function () {
    $("#icon_menu_wishlist").hide();
    $("#icon_menu_wishlist_choice").show();

    $(".icon_write").on('click', function () {
        $("#wishListForm").attr("action", "/wishlist/createPost.trip").submit();
    });

    $("#bottom_menu_home").on('click', function () {
        $("#wishListForm").attr("action", "/plans/planMain.trip").submit();
    });

    $(".wishlist_item_wrap").on('click', function () {
        const planNo = $(this).find("#wishlist_item_post_no").attr("value").toString();
        $(".wishlist_post_no").val(planNo);
        $("#wishListForm").attr("action", "/wishlist/postMain.trip").submit();
    });
});