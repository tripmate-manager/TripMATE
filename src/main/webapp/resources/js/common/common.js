function pageLink (url) {
    window.location.href = url;
}

function pageReplace (url) {
    window.location.replace(url);
}

function dateToString(date) {
    let year = date.getFullYear().toString();

    let month = date.getMonth() + 1;
    month = month < 10 ? '0' + month.toString() : month.toString();

    let day = date.getDate();
    day = day < 10 ? '0' + day.toString() : day.toString();

    let hour = date.getHours();
    hour = hour < 10 ? '0' + hour.toString() : hour.toString();

    let minites = date.getMinutes();
    minites = minites < 10 ? '0' + minites.toString() : minites.toString();

    let seconds = date.getSeconds();
    seconds = seconds < 10 ? '0' + seconds.toString() : seconds.toString();

    return year + month + day + hour + minites + seconds;
}

function address_comboBox(option, comboBoxArea) {
    if (option === 'default') {
        comboBoxArea.empty().attr("disabled", true);

        let optionItem = $('<option value="default">--시군구 선택--</option>');
        comboBoxArea.append(optionItem);
        return false;
    }

    $.ajax({
        type: 'GET',
        url: '/plans/addressOption/' + option + '.trip',
        dataType: 'json',
        data: {
            sidoName: option
        },
        success: function (result) {
            isAjaxProcessing = false;
            comboBoxArea.empty();

            const fragment = $(document.createDocumentFragment());

            for (i = 0; i < result.addressOptionList.length; i++) {
                const jsonOptionObject = JSON.parse(JSON.stringify(result.addressOptionList[i]));

                fragment.append('<option value="' + jsonOptionObject.addressNo + '">' + jsonOptionObject.sigunguName + '</option>');
            }
            comboBoxArea.attr("disabled", false);
            comboBoxArea.append(fragment);
        },
        error: function (error) {
            isAjaxProcessing = false;
            popUpOpen("처리 중 오류가 발생하였습니다.");
        }
    })
}

function clickPlanLike(checkBoxHeart, planNo, memberNo) {
    if(checkBoxHeart.checked) {
        $.ajax({
            url: "/plans/insertPlanLike.trip",
            type: "post",
            dataType: 'json',
            data: {
                planNo: planNo,
                memberNo: memberNo
            },
            success: function (result) {
                isAjaxProcessing = false;
                if (result.code === constCode.global.resultCodeSuccess) {
                    if (result.isInsertPlanLikeSuccess !== true) {
                        checkBoxHeart.checked = false;
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
    } else {
        $.ajax({
            url: "/plans/deletePlanLike.trip",
            type: "post",
            dataType: 'json',
            data: {
                planNo: planNo,
                memberNo: memberNo
            },
            success: function (result) {
                isAjaxProcessing = false;
                if (result.code === constCode.global.resultCodeSuccess) {
                    if (result.isDeletePlanLikeSuccess !== true) {
                        checkBoxHeart.checked = false;
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
}