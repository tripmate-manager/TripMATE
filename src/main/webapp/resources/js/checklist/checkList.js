let isAjaxProcessing = false;

$(function () {
    const editBtn = document.getElementById("checklist_edit");
    const saveBtn = document.getElementById("checklist_save");
    const togetherMenu = document.getElementById("tab_menu_together");
    const myMenu = document.getElementById("tab_menu_my");
    const togetherCheckList = document.querySelector(".checklist_together_wrap");
    const myCheckList = document.querySelector(".checklist_my_wrap");
    const planLeaderNo = document.getElementById("planLeaderNo").value;
    const memberNo = document.getElementById("sessionMemberNo").value;
    const deleteBtn = document.querySelectorAll(".icon_list_delete_circle");
    let editMaterialList = [];

    $("#icon_menu_checklist").hide();
    $("#icon_menu_checklist_choice").show();

    if (planLeaderNo === memberNo) {
        editBtn.style.display = 'block';
    } else {
        editBtn.style.display = 'none';
    }

    togetherMenu.addEventListener('click', function () {
        if(togetherMenu.classList.contains("tab_select") === false) {
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
        if(myMenu.classList.contains("tab_select") === false) {
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

        document.querySelectorAll(".icon_list_delete_circle").forEach(function (e) {
            e.style.display = 'block';
        })
    });

    deleteBtn.forEach(function (e) {
        e.addEventListener('click', function () {
            editMaterialList.push(e.value);
            e.parentElement.style.background = "#F1F1F1";
        });
    })

    saveBtn.addEventListener('click', function () {
        console.log(deleteBtn.previousElementSibling);
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

        // saveBtn.style.display = 'none';
        // editBtn.style.display = 'block';
        //
        // document.querySelectorAll(".icon_list_delete_circle").forEach(function (e) {
        //     e.style.display = 'none';
        // })
    });
});

const addMaterial = (target, checkListTypeCode) => {
    const addMaterialName = target.previousElementSibling.value;
    console.log(addMaterialName);

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