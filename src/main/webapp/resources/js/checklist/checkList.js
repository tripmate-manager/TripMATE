let isAjaxProcessing = false;

$(function () {
    const editBtn = document.getElementById("checklist_edit");
    const saveBtn = document.getElementById("checklist_save");
    const cancelBtn = document.getElementById("checklist_cancel");
    const backBtn = document.getElementById("icon_arrow_left");
    const togetherMenu = document.getElementById("tab_menu_together");
    const myMenu = document.getElementById("tab_menu_my");
    const togetherCheckList = document.querySelector(".checklist_together_wrap");
    const myCheckList = document.querySelector(".checklist_my_wrap");
    const planLeaderNo = document.getElementById("planLeaderNo");
    const memberNo = document.getElementById("sessionMemberNo").value;
    const deleteBtn = $(".icon_list_delete_circle");
    let editMaterialList = [];

    $("#icon_menu_checklist").hide();
    $("#icon_menu_checklist_choice").show();

    checkboxDisabled();

    if (!planLeaderNo) {
        editBtn.style.display = 'none';
    } else if (planLeaderNo.value === memberNo) {
        editBtn.style.display = 'block';
    }

    togetherMenu.addEventListener('click', function () {
        if (togetherMenu.classList.contains("tab_select") === false) {
            togetherMenu.classList.add("tab_select");
            myMenu.classList.remove("tab_select");
        }

        togetherCheckList.style.display = 'block';
        myCheckList.style.display = 'none';

        if (planLeaderNo === memberNo) {
            editBtn.style.display = 'block';
        } else {
            editBtn.style.display = 'none';
        }
    });

    myMenu.addEventListener('click', function () {
        if (myMenu.classList.contains("tab_select") === false) {
            myMenu.classList.add("tab_select");
            togetherMenu.classList.remove("tab_select");
        }

        myCheckList.style.display = 'block';
        togetherCheckList.style.display = 'none';
        editBtn.style.display = 'block';
    });

    editBtn.addEventListener('click', function () {
        editMaterialList = [];
        editBtn.style.display = 'none';
        saveBtn.style.display = 'block';

        backBtn.style.display = 'none';
        cancelBtn.style.display = 'block';

        document.querySelectorAll(".checkbox").forEach(function (e) {
            e.disabled = true;
        })

        document.querySelectorAll(".icon_list_delete_circle").forEach(function (e) {
            e.style.display = 'block';
        })
    });

    deleteBtn.on('click', function () {
        editMaterialList.push($(this).parent().find(".checklist_item_no").text());
        $(this).parent().css('background', '#F1F1F1');
    });

    cancelBtn.addEventListener('click', function () {
        editBtn.style.display = 'block';
        saveBtn.style.display = 'none';

        backBtn.style.display = 'block';
        cancelBtn.style.display = 'none';

        document.querySelectorAll(".checkbox").forEach(function (e) {
            e.disabled = false;
            checkboxDisabled();
        })

        document.querySelectorAll(".icon_list_delete_circle").forEach(function (e) {
            e.style.display = 'none';
        })

        $(".checklist_item_wrap").css('background', '#FFFFFF');
    });

    saveBtn.addEventListener('click', function (e) {

        if (editMaterialList.length === 0) {
            popUpOpen('삭제할 항목을 선택해주세요.');
        }

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
    });

    $("#icon_menu_home").on('click', function () {
        $("#checkListForm").attr("action", "/plans/planMain.trip").submit();
    });

    $("#bottom_menu_wishlist").on('click', function () {
        $("#checkListForm").attr("action", "/wishlist/wishlist.trip").submit();
    });

    function checkboxDisabled () {
        document.querySelectorAll(".checkbox").forEach(function (e) {
            if (e.value !== 'null' && e.value !== memberNo) {
                e.disabled = true;
            }
        })
    }

});

const addMaterial = (target, checkListTypeCode) => {
    const addMaterialName = target.previousElementSibling.value;

    if (addMaterialName.trim() === "") {
        popUpOpen("추가할 항목을 입력해주세요.");
        return false;
    }

    if (addMaterialName.length > 50) {
        popUpOpen("체크리스트는 최대 50자까지 입력 가능합니다.");
        return false;
    }

    if (isAjaxProcessing) {
        popUpOpen('이전 요청을 처리중 입니다. 잠시 후 다시 시도하세요.');
        return;
    } else {
        isAjaxProcessing = true;
    }

    $.ajax({
        url: "/checkList/addCheckList.trip",
        type: "post",
        dataType: 'json',
        data: {
            planNo: document.getElementById("planNo").value,
            memberNo: document.getElementById("sessionMemberNo").value,
            checkListTypeCode: checkListTypeCode,
            materialName: addMaterialName
        },
        success: function (result) {
            isAjaxProcessing = false;

            if (result.code === constCode.global.resultCodeSuccess) {
                if (result.isInsertCheckListSuccess === true) {
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
}

function clickCheckbox(materialNo, checkListTypeCode) {
    if (isAjaxProcessing) {
        popUpOpen('이전 요청을 처리중 입니다. 잠시 후 다시 시도하세요.');
        return;
    } else {
        isAjaxProcessing = true;
    }

    $.ajax({
        url: "/checkList/updateCheckYn.trip",
        type: "post",
        dataType: 'json',
        traditional: true,
        data: {
            memberNo: document.getElementById("sessionMemberNo").value,
            checkListTypeCode: checkListTypeCode,
            materialNo: materialNo
        },
        success: function (result) {
            isAjaxProcessing = false;

            if (result.code === constCode.global.resultCodeSuccess) {
                if (result.isUpdateCheckYnSuccess === true) {
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
}