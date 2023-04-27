let isAjaxProcessing = false;

function editPost() {
    $("#commentForm").attr("action", "/wishlist/editPost.trip").submit();
}

function deletePost() {
    checkPopUpOpen("현재 게시글을 삭제하시겠습니까?");
    document.querySelector(".check_popup_btn_ok").setAttribute('onClick', null);
    document.querySelector(".check_popup_btn_ok").addEventListener('click', () => {
        if (isAjaxProcessing) {
            popUpOpen('이전 요청을 처리중 입니다. 잠시 후 다시 시도하세요.');
            return;
        } else {
            isAjaxProcessing = true;
        }

        $.ajax({
            url: "/wishlist/deletePost.trip",
            type: "post",
            dataType: 'json',
            data: {
                postNo: document.getElementById("postNo").value
            },
            success: function (result) {
                isAjaxProcessing = false;

                if (result.code === constCode.global.resultCodeSuccess) {
                    if (result.isDeletePostSuccess === true) {
                        $("#commentForm").attr("action", "/wishlist/wishlist.trip").submit();
                    }
                } else {
                    popUpOpen(result.message);
                }
            },
            error: function (error) {
                isAjaxProcessing = false;
                popUpOpen("처리 중 오류가 발생하였습니다.");
            }
        })
    })
}

function deleteComment(commentNo, commenterNo) {
    if (isAjaxProcessing) {
        popUpOpen('이전 요청을 처리중 입니다. 잠시 후 다시 시도하세요.');
        return;
    } else {
        isAjaxProcessing = true;
    }

    $.ajax({
        url: "/wishlist/deleteComment.trip",
        type: "post",
        dataType: 'json',
        data: {
            commentNo: commentNo,
            commenterNo: commenterNo,
        },
        success: function (result) {
            isAjaxProcessing = false;

            if (result.code === constCode.global.resultCodeSuccess) {
                if (result.isDeleteCommentSuccess === true) {
                    location.reload();
                }
            } else {
                popUpOpen(result.message);
            }
        },
        error: function (error) {
            isAjaxProcessing = false;
            popUpOpen("처리 중 오류가 발생하였습니다.");
        }
    })
}

$(function () {
    const postNo = $("#postNo").val();
    const commenterNo = $("#sessionMemberNo").val();
    const commentText = $("#commentText");

    let commentDepth = 1;
    let commentGroupNo = 0;
    let commentGroupNo2;
    let commentSelected;

    $("#icon_menu_wishlist").hide();
    $("#icon_menu_wishlist_choice").show();

    $(".comment_wrap").on('click', function () {
        commentSelected = $(this);
        commentText.focus();
        commentGroupNo = $(this).find(".commentNo").val();
        commentDepth = Number($(this).find(".commentDepth").val()) + 1;
    })

    $("div[name=comment_reply_contents_wrap]").on('click', function () {
        if ($(this).parent().find("#comment_reply").length > 0) {
            return;
        }
        commentSelected = $(this);
        commentText.focus();
        commentGroupNo = $(this).parent().find(".commentGroupNo").val();
        commentGroupNo2 = $(this).parent().find(".commentGroupNo2").val();
        commentDepth = Number($(this).parent().find(".commentDepth").val()) + 1;
    })

    commentText.focus(function () {
        if (commentSelected) {
            commentSelected.css('border', 'solid 0.5rem #DAF2F0');
        }
    });

    commentText.blur(function (element) {
        if (commentSelected) {
            commentSelected.css('border', '');
        }
        commentSelected = null;
    });

    commentText.on('click', function () {
        if (commentSelected) {
            commentSelected.css('border', '');
        }
        commentSelected = null;
        commentDepth = 1;
    });

    $(".icon_send").on('click', function () {
        if (isAjaxProcessing) {
            popUpOpen('이전 요청을 처리중 입니다. 잠시 후 다시 시도하세요.');
            return;
        } else {
            isAjaxProcessing = true;
        }

        if (commentText.val().trim() === "") {
            popUpOpen('댓글을 입력해주세요.');
            return;
        }

        $.ajax({
            url: "/wishlist/createComment.trip",
            type: "post",
            dataType: 'json',
            data: {
                postNo: postNo,
                commenterNo: commenterNo,
                commentGroupNo: commentGroupNo,
                commentGroupNo2: commentGroupNo2,
                commentDepth: commentDepth,
                commentText: commentText.val()
            },
            success: function (result) {
                isAjaxProcessing = false;

                if (result.code === constCode.global.resultCodeSuccess) {
                    if (result.createCommentNo) {
                        location.reload();
                    }
                } else {
                    popUpOpen(result.message);
                }
            },
            error: function (error) {
                isAjaxProcessing = false;
                popUpOpen("처리 중 오류가 발생하였습니다.");
            }
        })
    });

    $("#icon_menu_home").on('click', function () {
        $("#commentForm").attr("action", "/plans/planMain.trip").submit();
    });

    $("#bottom_menu_wishlist").on('click', function () {
        $("#commentForm").attr("action", "/wishlist/wishlist.trip").submit();
    });

    $("#bottom_menu_checklist").on('click', function () {
        $("#commentForm").attr("action", "/checkList/checkList.trip").submit();
    });

    $("#icon_arrow_left").on('click', function () {
        $("#commentForm").attr("action", "/wishlist/wishlist.trip").submit();
    });
});