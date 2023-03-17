$(function () {
    $("#icon_menu_wishlist").hide();
    $("#icon_menu_wishlist_choice").show();

    $(".icon_write").on('click', function () {
        $("#wishListForm").attr("action", "/wishlist/createPost.trip").submit();
    });

    $("#bottom_menu_home").on('click', function () {
        $("#wishListForm").attr("action", "/plans/planMain.trip").submit();
    });
});