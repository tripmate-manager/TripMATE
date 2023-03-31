let popUpPlanNo, popUpPostNo, popUpTripStartDate;

function addDailyPlanPopUpOpen(planNo, postNo, tripTerm, tripStartDate) {
    popUpPlanNo = planNo;
    popUpPostNo = postNo;
    popUpTripStartDate = tripStartDate;

    const selectOptionDay = $('#select_option_day');
    selectOptionDay.empty();

    const daysFragment = $(document.createDocumentFragment());
    for (let i = 0; i <= tripTerm; i++) {
        daysFragment.append('<option value="' + (i + 1) + '">' + "Day" + (i + 1) + '</option>');
    }
    selectOptionDay.append(daysFragment);

    const selectOptionTime = $('#select_option_time');
    selectOptionTime.empty();

    const timeFragment = $(document.createDocumentFragment());
    for (let hour = 0; hour < 24; hour++) {
        const hourStr = hour.toString().padStart(2, "0") + ":";

        let hourItem = hourStr + "00";
        timeFragment.append('<option value="' + hourItem.replace(":", "") + '">' + hourItem + '</option>');

        hourItem = hourStr + "30";
        timeFragment.append('<option value="' + hourItem.replace(":", "") + '">' + hourItem + '</option>');
    }
    selectOptionTime.append(timeFragment);

    const popupWrap = $("#add_dailyplan_popup_wrap");
    popupWrap.css("position", "absolute");
    popupWrap.css("top", (($(window).height() - popupWrap.outerHeight()) / 3) + $(window).scrollTop());
    popupWrap.css("left", (($(window).width() - popupWrap.outerWidth()) / 2) + $(window).scrollLeft());
    popupWrap.show();
}

function popUpCancel() {
    $("#add_dailyplan_popup_wrap").hide();
}

function popUpOk() {
    const optionDay = $("#select_option_day option:selected").val();
    const optionHour = $("#select_option_time option:selected").val().substring(0, 2);
    const optionMin = $("#select_option_time option:selected").val().substring(2, 4);

    let popUpDateTime = new Date(popUpTripStartDate);
    popUpDateTime.setDate(popUpDateTime.getDate() + (optionDay - 1));
    popUpDateTime.setHours(optionHour);
    popUpDateTime.setMinutes(optionMin);
    popUpDateTime = dateToString(popUpDateTime);

    $.ajax({
        url: '/dailyPlans/addDailyPlan.trip',
        type: 'post',
        dataType: 'json',
        data: {
            planNo: popUpPlanNo,
            postNo: popUpPostNo,
            memberNo: document.getElementById("memberNo").value,
            dayGroupNo: optionDay,
            dailyPlanDateTime: popUpDateTime
        },
        success: function (result) {
            if (result.isInsertDailyPlanSuccess === true) {
                location.reload();
            }
        },
        error: function (error) {
            popUpOpen("처리 중 오류가 발생하였습니다.");
        }
    })
}