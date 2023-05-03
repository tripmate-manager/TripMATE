$(function () {
    const memberNo = document.getElementById("sessionMemberNo").value;
    const textSearchMenu = document.getElementById("tab_menu_text");
    const attributeSearchMenu = document.getElementById("tab_menu_attribute");
    const textSearchContents = document.querySelector(".searchplan_contents_text_wrap");
    const attributeSearchContents = document.querySelector(".searchplan_contents_attribute_wrap");
    const inputSearch = document.getElementById("input_search");
    const searchBtn = document.getElementById("icon_search");
    const backBtn = document.getElementById("icon_arrow_left");
    const tripAddressChoice = document.getElementById("trip_address_choice");
    const personnelPlusBtn = document.getElementById("personnel_plus");
    const personnelMinusBtn = document.getElementById("personnel_minus");

    const tripAddressDownBtn = document.getElementById("trip_address_arrow_down_btn");
    const periodDownBtn = document.getElementById("period_arrow_down_btn");
    const themeDownBtn = document.getElementById("theme_arrow_down_btn");
    const tripAddressUpBtn = document.getElementById("trip_address_arrow_up_btn");
    const periodUpBtn = document.getElementById("period_arrow_up_btn");
    const themeUpBtn = document.getElementById("theme_arrow_up_btn");

    let isAjaxProcessing = false;
    let inputGenderCode = $("#genderCode");
    let inputPersonnel = document.getElementById("input_personnel");
    let personnel = document.getElementById("input_personnel").value;

    textSearchMenu.addEventListener('click', function () {
        if (textSearchMenu.classList.contains("tab_select") === false) {
            textSearchMenu.classList.add("tab_select");
            attributeSearchMenu.classList.remove("tab_select");

            inputSearch.style.display = 'block';
            searchBtn.style.display = 'block';
        }

        textSearchContents.style.display = 'block';
        attributeSearchContents.style.display = 'none';

    });

    attributeSearchMenu.addEventListener('click', function () {
        if (attributeSearchMenu.classList.contains("tab_select") === false) {
            attributeSearchMenu.classList.add("tab_select");
            textSearchMenu.classList.remove("tab_select");

            inputSearch.style.display = 'none';
            searchBtn.style.display = 'none';
        }

        attributeSearchContents.style.display = 'block';
        textSearchContents.style.display = 'none';
    });

    $(".btn-default").on('click', function () {
        const genderFemaleBtn = $(this).parent().find(".btn-primary");

        if (inputGenderCode.val() !== $("#GENDER_CODE_MALE").val()) {
            $(this).toggleClass('active');
            genderFemaleBtn.toggleClass('active');
            inputGenderCode.val($("#GENDER_CODE_MALE").val());
        }
    });

    $(".btn-primary").on('click', function () {
        const genderMaleBtn = $(this).parent().find(".btn-default");

        if (inputGenderCode.val() !== $("#GENDER_CODE_FEMALE").val()) {
            $(this).toggleClass('active');
            genderMaleBtn.toggleClass('active');
            inputGenderCode.val($("#GENDER_CODE_FEMALE").val());
        }
    });

    personnelPlusBtn.addEventListener('click', function () {
        personnel = inputPersonnel.value;
        if (personnel < 40) {
            inputPersonnel.value = personnel + 1;
        } else {
            popUpOpen("인원은 최대 40명까지 입력 가능합니다.");
        }
    })

    personnelMinusBtn.addEventListener('click', function () {
        personnel = inputPersonnel.value;
        if (personnel > 0) {
            inputPersonnel.value = personnel - 1;
        } else {
            popUpOpen("인원은 양수만 입력 가능합니다.");
        }
    })

    backBtn.addEventListener('click', function () {
        $("#searchPlanKeywordForm").attr('method', 'get').attr("action", "/main/main.trip").submit();
    })

    tripAddressDownBtn.addEventListener('click', function () {
        tripAddressDownBtn.style.display = 'none';
        document.getElementById("trip_address_choice_wrap").style.display = 'block';
    })

    periodDownBtn.addEventListener('click', function () {
        periodDownBtn.style.display = 'none';
        document.getElementById("period_choice_wrap").style.display = 'block';
    })

    themeDownBtn.addEventListener('click', function () {
        themeDownBtn.style.display = 'none';
        document.getElementById("plan_theme_choice_wrap").style.display = 'block';
    })

    tripAddressUpBtn.addEventListener('click', function () {
        tripAddressDownBtn.style.display = 'block';
        document.getElementById("trip_address_choice_wrap").style.display = 'none';
    })

    periodUpBtn.addEventListener('click', function () {
        periodDownBtn.style.display = 'block';
        document.getElementById("period_choice_wrap").style.display = 'none';
    })

    themeUpBtn.addEventListener('click', function () {
        themeDownBtn.style.display = 'block';
        document.getElementById("plan_theme_choice_wrap").style.display = 'none';
    })

    document.getElementById("trip_address_btn").addEventListener('click', function () {
        tripAddressChoice.style.display = 'block';
    })

    document.getElementById("trip_address_btn").addEventListener('click', function () {
        tripAddressChoice.style.display = 'block';
    })

    searchBtn.addEventListener('click', function () {
        if(inputSearch.value.trim() === "") {
            popUpOpen('검색어를 입력해주세요.');
            return;
        }

        $("#searchPlanKeywordForm").attr('action', '/searchPlan/keyword.trip').submit();
    });
});

function address_option(option) {
    address_comboBox(option, $("#select_option_sigungu"));
}