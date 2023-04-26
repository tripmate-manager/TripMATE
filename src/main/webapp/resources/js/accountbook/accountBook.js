let isAjaxProcessing = false;

$(function () {
    const editBtn = document.getElementById("accountbook_edit");
    const saveBtn = document.getElementById("accountbook_save");
    const cancelBtn = document.getElementById("accountbook_cancel");
    const backBtn = document.getElementById("icon_arrow_left");
    const deleteBtn = $(".icon_list_delete_circle");
    const selectDays = document.querySelectorAll(".accountbook_day_item");
    const inputAccount = document.querySelectorAll(".accountbook_input_wrap");
    const sortingBtn = document.querySelectorAll(".icon_sorting")
    const dayGroupSelected = $('.select_day').attr("value");
    let deleteAccountList = [];

    $("#icon_menu_home").on('click', function () {
        $("#accountBookForm").attr("action", "/plans/planMain.trip").submit();
    });

    $("#bottom_menu_wishlist").on('click', function () {
        $("#accountBookForm").attr("action", "/wishlist/wishlist.trip").submit();
    });

    $("#bottom_menu_checklist").on('click', function () {
        $("#accountBookForm").attr("action", "/checkList/checkList.trip").submit();
    });

   selectDays.forEach(function (e) {
       e.addEventListener('click', function () {
           selectDays.forEach(function (days) {
               days.classList.remove("select_day");
           });
           e.classList.add("select_day");
        });

       //조회

    })


    editBtn.addEventListener('click', function () {
        deleteAccountList = [];

        editBtn.style.display = 'none';
        saveBtn.style.display = 'block';

        backBtn.style.display = 'none';
        cancelBtn.style.display = 'block';

        inputAccount.forEach(function (e) {
            e.style.display = 'none';
        })

        document.querySelectorAll(".checkbox").forEach(function (e) {
            e.disabled = true;
        })

        document.querySelectorAll(".icon_list_delete_circle").forEach(function (e) {
            e.style.display = 'block';
        })

        sortingBtn.forEach(function (e) {
            e.style.display = 'block';
        })
    });

    cancelBtn.addEventListener('click', function () {
        editBtn.style.display = 'block';
        saveBtn.style.display = 'none';

        backBtn.style.display = 'block';
        cancelBtn.style.display = 'none';

        inputAccount.forEach(function (e) {
            e.style.display = 'block';
        })

        document.querySelectorAll(".checkbox").forEach(function (e) {
            e.disabled = false;
        })

        document.querySelectorAll(".icon_list_delete_circle").forEach(function (e) {
            e.style.display = 'none';
        })

        sortingBtn.forEach(function (e) {
            e.style.display = 'none';
        })

        $(".accountbook_item_wrap").css('background', '#FFFFFF');
    });

    deleteBtn.on('click', function () {
        deleteAccountList.push($(this).parent().find("#accountbook_item_no").val());
        $(this).parent().css('background', '#F1F1F1');
    });

    saveBtn.addEventListener('click', function () {
        // 수정 / 삭제
        if (isAjaxProcessing) {
            popUpOpen('이전 요청을 처리중 입니다. 잠시 후 다시 시도하세요.');
            return;
        } else {
            isAjaxProcessing = true;
        }

        $.ajax({
            url: "/checkList/deleteCheckList.trip",
            type: "post",
            dataType: 'json',
            traditional: true,
            data: {
                planNo: document.getElementById("planNo").value,
                memberNo: document.getElementById("sessionMemberNo").value,
                checkListTypeCode: deleteBtn[0].previousElementSibling.value,
                materialNoList: editMaterialList
            },
            success: function (result) {
                isAjaxProcessing = false;

                if (result.code === constCode.global.resultCodeSuccess) {
                    if (result.isDeleteCheckListSuccess === true) {
                        location.reload();
                    } else {
                        popUpOpen("처리 중 오류가 발생하였습니다.");
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

    document.querySelector(".add_account_item_wrap").addEventListener('click', function () {
        document.querySelector(".add_account_wrap").style.display = 'block';
    });

    document.getElementById("add_account_btn").addEventListener('click', function (e) {
        if (isAjaxProcessing) {
            popUpOpen('이전 요청을 처리중 입니다. 잠시 후 다시 시도하세요.');
            return;
        } else {
            isAjaxProcessing = true;
        }

        $.ajax({
            url: "/accountBook/addAccount.trip",
            type: "post",
            dataType: 'json',
            traditional: true,
            data: {
                memberNo: document.getElementById("sessionMemberNo").value,
                planNo: document.getElementById("planNo").value,
                postTypeCode: $("#select_post_type option:selected").val(),
                accountName: document.getElementById("account_desc_title").value,
                dayGroup: dayGroupSelected,
                amount: document.getElementById("accountbook_add_input").value
            },
            success: function (result) {
                isAjaxProcessing = false;

                if (result.code === constCode.global.resultCodeSuccess) {
                    if (result.isInsertAccountSuccess === true) {
                        location.reload();
                    } else {
                        popUpOpen("처리 중 오류가 발생하였습니다.");
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

    inputAccount.forEach(function (e) {
        e.addEventListener('change', function () {
            console.log('event!! ');
            console.log(e.querySelector(".accountbook_input").value);
        });
    })
});