$(function () {
    const inputMemberNo = document.getElementById("input_member_no");
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
    const searchAttributeBtn = document.getElementById("searchplan_attribute");

    const tripAddressDownBtn = document.getElementById("trip_address_arrow_down_btn");
    const tripTermDownBtn = document.getElementById("trip_term_arrow_down_btn");
    const themeDownBtn = document.getElementById("theme_arrow_down_btn");
    const tripAddressUpBtn = document.getElementById("trip_address_arrow_up_btn");
    const tripTermUpBtn = document.getElementById("trip_term_arrow_up_btn");
    const themeUpBtn = document.getElementById("theme_arrow_up_btn");

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
        searchAttributeBtn.style.display = 'none';
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
        searchAttributeBtn.style.display = 'block';
    });

    $(".btn-default").on('click', function () {
        const genderFemaleBtn = $(this).parent().find(".btn-primary");

        if (inputGenderCode.val() !== $("#GENDER_CODE_MALE").val()) {
            $(this).toggleClass('active');
            genderFemaleBtn.toggleClass('active');
            inputGenderCode.val(constCode.global.memberGenderCodeMale);
        }
    });

    $(".btn-primary").on('click', function () {
        const genderMaleBtn = $(this).parent().find(".btn-default");

        if (inputGenderCode.val() !== $("#GENDER_CODE_FEMALE").val()) {
            $(this).toggleClass('active');
            genderMaleBtn.toggleClass('active');
            inputGenderCode.val(constCode.global.memberGenderCodeFemale);
        }
    });

    personnelPlusBtn.addEventListener('click', function () {
        personnel = inputPersonnel.value;

        if (personnel < 40) {
            inputPersonnel.value = Number(personnel) + 1;
        } else {
            popUpOpen("인원은 최대 40명까지 입력 가능합니다.");
        }
    })

    personnelMinusBtn.addEventListener('click', function () {
        personnel = inputPersonnel.value;

        if (personnel > 0) {
            inputPersonnel.value = Number(personnel) - 1;
        } else {
            popUpOpen("인원은 양수만 입력 가능합니다.");
        }
    })

    backBtn.addEventListener('click', function () {
        $("#searchPlanForm").attr('method', 'get').attr("action", "/main/main.trip").submit();
    })

    tripAddressDownBtn.addEventListener('click', function () {
        tripAddressDownBtn.style.display = 'none';
        document.getElementById("trip_address_choice_wrap").style.display = 'block';
    })

    tripTermDownBtn.addEventListener('click', function () {
        tripTermDownBtn.style.display = 'none';
        document.getElementById("trip_term_choice_wrap").style.display = 'block';
    })

    themeDownBtn.addEventListener('click', function () {
        themeDownBtn.style.display = 'none';
        document.getElementById("plan_theme_choice_wrap").style.display = 'block';
    })

    tripAddressUpBtn.addEventListener('click', function () {
        tripAddressDownBtn.style.display = 'block';
        document.getElementById("trip_address_choice_wrap").style.display = 'none';
    })

    tripTermUpBtn.addEventListener('click', function () {
        tripTermDownBtn.style.display = 'block';
        document.getElementById("trip_term_choice_wrap").style.display = 'none';
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

    $("input[id*='plan_theme_item']").on('click', function () {
        let count = $("input:checked[id*='plan_theme_item']").length;

        if (count > 3) {
            $(this).prop("checked", false);
            popUpOpen("여행테마는 최대 3개까지만 선택할 수 있습니다.");
        }
    });

    $("#select_address_btn").on('click', function () {
        const inputAddressNo = $("#select_option_sigungu option:selected").val();
        const inputSido = $("#select_option_sido option:selected").val();
        const inputSigungu = $("#select_option_sigungu option:selected").text();
        let isAddressDuplicate = false;

        $(".searchplan_address_list").show();

        if ($("div[class='address_item_text']").length === 3) {
            popUpOpen("여행테마는 최대 3개까지만 선택할 수 있습니다.");
            return;
        }

        if (inputSido === 'default' || inputAddressNo === 'default') {
            popUpOpen("여행지를 선택해주세요.");
            return false;
        }

        $("div[class='address_item_text']").each(function (item) {
            if (inputAddressNo === $(this).attr("value")) {
                popUpOpen("이미 입력된 여행지입니다.");
                isAddressDuplicate = true;
            }
        });

        if (!isAddressDuplicate) {
            let optionItem = $('<div class="address_item_text" value="' + inputAddressNo + '">' +
                '<input name="tripAddressList" value="' + inputAddressNo + '" + hidden>' + inputSido + " " + inputSigungu + '</div>');
            $(".searchplan_address_list").append(optionItem);
        }
    });

    $(".searchplan_address_list").on('click', '.address_item_text', function () {
        $(this).remove();

        if ($("div[class='address_item_text']").length == 0) {
            $(".searchplan_address_list").hide();
        }
    });

    $('#select_option_trip_term').change(function () {
        const tripTerm = document.getElementById("searchplan_trip_term");

        if ($('#select_option_trip_term option:selected').val() === 'default') {
            document.getElementById("input_tripTerm").disabled = true;
            tripTerm.style.display = 'none';
            return;
        }

        document.getElementById("input_tripTerm").value = tripTerm.value;
        tripTerm.style.display = 'block';
        tripTerm.innerText = $('#select_option_trip_term option:selected').text();
    });

    searchAttributeBtn.addEventListener('click', function () {
        if ($('#select_option_trip_term option:selected').val() === 'default') {
            document.getElementById("input_tripTerm").disabled = true;
        }

        $("#searchPlanForm").attr('action', '/searchPlan/attribute.trip').submit();
    });

    function searchKeyword() {
        if (inputSearch.value.trim() === "") {
            popUpOpen('검색어를 입력해주세요.');
            return;
        }

        $("#searchPlanForm").attr('action', '/searchPlan/keyword.trip').submit();
    }

    searchBtn.addEventListener('click', function () {
        searchKeyword();
    });

    document.querySelectorAll("div[class='recommend_word']").forEach(function (item) {
        item.addEventListener('click', function () {
            inputSearch.value = item.innerText;
            searchKeyword();
        });
    });

    document.querySelectorAll("div[class='popular_hashtag']").forEach(function (item) {
        item.addEventListener('click', function () {
            inputSearch.value = item.innerText.substring(2);
            searchKeyword();
        });
    });
});

function address_option(option) {
    address_comboBox(option, $("#select_option_sigungu"));
}