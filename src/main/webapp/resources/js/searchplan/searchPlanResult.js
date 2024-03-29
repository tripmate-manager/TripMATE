let isAjaxProcessing = false;

$(function () {
    const inputMemberNo = document.getElementById("input_member_no");
    let searchResultItemArr = [];

    document.getElementById("icon_arrow_left").addEventListener('click', function () {
        $("#searchPlanResultForm").attr("method", "get").attr("action", "/searchPlan/search.trip").submit();
        sessionStorage.removeItem("sortTypeCode");
    });

    const searchResultItems = document.querySelectorAll(".searchPlanResultItemForm");
    searchResultItems.forEach(function (item) {
        searchResultItem = {
            "planNo": item.querySelector(".item_plan_no").getAttribute('value'),
            "planTitle": item.querySelector(".item_plan_title").getAttribute('value'),
            "planDescription": item.querySelector(".item_plan_desc").getAttribute('value'),
            "tripStartDate": item.querySelector(".item_trip_start_date").getAttribute('value'),
            "tripEndDate": item.querySelector(".item_trip_end_date").getAttribute('value'),
            "tripTerm": tripTermText(Number(item.querySelector(".item_trip_term").getAttribute('value'))),
            "likeRegistrationCnt": item.querySelector(".item_like_registration_cnt").getAttribute('value'),
            "achieveRate": Number(item.querySelector(".item_achieve_rate").getAttribute('value')),
            "views": item.querySelector(".item_like_views").getAttribute('value'),
            "reviewAverageScore": Number(item.querySelector(".item_review_average_score").getAttribute('value')),
            "sidoName": item.querySelector(".item_sido_name").getAttribute('value'),
            "sigunguName": item.querySelector(".item_sigungu_name").getAttribute('value'),
            "leaderNickName": item.querySelector(".item_leader_nick_name").getAttribute('value'),
            "registrationDateTime": item.querySelector(".item_registration_date_time").getAttribute('value'),
            "planLikeCnt": item.querySelector(".item_plan_like_cnt").getAttribute('value'),
        }
        searchResultItemArr.push(searchResultItem);
    });

    if (document.getElementById("session_member_no") !== null) {
        inputMemberNo.value = document.getElementById("session_member_no").innerText;
    } else {
        inputMemberNo.value = 0;
    }

    if (sessionStorage.getItem("sortTypeCode") !== null) {
        $("#select_option_sort").val(sessionStorage.getItem("sortTypeCode")).prop("selected", true);
    }
    sortSearchResultItems($('#select_option_sort option:selected').val());

    $('#select_option_sort').change(function () {
        sortSearchResultItems($('#select_option_sort option:selected').val());
    });

    function tripTermText(tripTerm) {
        return tripTerm === 0 ? "당일치기" : tripTerm + "박 " + (tripTerm + 1) + "일";
    }

    function sortSearchResultItems(sortTypeCode) {
        let searchResultsortArr = searchResultItemArr.slice();

        if (sortTypeCode === constCode.global.searchResultSortRecent) {
            searchResultsortArr.sort(function (a, b) {
                return a.registrationDateTime > b.registrationDateTime ? -1 : a.registrationDateTime < b.registrationDateTime ? 1 : 0;
            });
        } else if (sortTypeCode === constCode.global.searchResultSortPopular) {
            //TODO: 찜기능 추가 후 보완
        } else if (sortTypeCode === constCode.global.searchResultSortOldest) {
            searchResultsortArr.sort(function (a, b) {
                return a.registrationDateTime < b.registrationDateTime ? -1 : a.registrationDateTime > b.registrationDateTime ? 1 : 0;
            });
        } else if (sortTypeCode === constCode.global.searchResultSortAchieveRate) {
            searchResultsortArr.sort(function (a, b) {
                return b['achieveRate'] - a['achieveRate'];
            });
        } else if (sortTypeCode === constCode.global.searchResultSortReview) {
            searchResultsortArr.sort(function (a, b) {
                return b['reviewAverageScore'] - a['reviewAverageScore'];
            });
        }

        appendSearchResultItems(searchResultsortArr);
    }

    function appendSearchResultItems(array) {
        let searchResultItems = document.querySelectorAll(".searchplan_result_plan_item_wrap")
        searchResultItems.forEach(function (item) {
            item.remove();
        })

        const fragment = $(document.createDocumentFragment());
        const searchResultList = $(".searchplan_result_list_wrap");

        for (let i = 0; i < array.length; i++) {
            let isChecked = array[i]['planLikeCnt'] > 0 ? 'checked' : '';
            fragment.append(
                '<div class="searchplan_result_plan_item_wrap">' +
                    '<div class="searchplan_result_item_contents_wrap">' +
                        '<div id="searchplan_result_item_no" value="' + array[i]['planNo'] + '"></div>' +
                        '<div class="searchplan_result_item_title">' + array[i]['planTitle'] + '</div>' +
                        '<div class="searchplan_result_item_desc">' + array[i]['planDescription'] + '</div>' +
                        '<div class="searchplan_result_item_wrap">' +
                            '<img class="icon_leader"/>' +
                            '<div class="searchplan_result_plan_text">' + array[i]['leaderNickName'] + '</div>' +
                        '</div>' +
                        '<div class="searchplan_result_item_wrap">' +
                            '<img class="icon_location"/>' +
                            '<div class="searchplan_result_plan_text">' + array[i]['sidoName'] + " " + array[i]['sigunguName'] + '</div>' +
                        '</div>' +
                        '<div class="searchplan_result_item_wrap">' +
                            '<img class="icon_calendar"/>' +
                            '<div class="searchplan_result_plan_text" id="trip_term">' + array[i]['tripStartDate'] + " ~ " + array[i]['tripEndDate'] + " [" +
                                array[i]['tripTerm'] + "] " + '</div>' +
                                '<input type="checkbox" name="checkboxHeart" id="checkboxHeart'+ i + '" class="checkboxHeart"' + isChecked + '>' +
                                '<label for="checkboxHeart' + i + '" class="checkboxHeart"></label>' +
                        '</input>' +
                    '</div>' +
                '</div>'
            );
        }
        searchResultList.append(fragment);

        if (inputMemberNo.value === "0") {
            $("label[for *= 'checkboxHeart']").hide();
        } else {
            $("label[for *= 'checkboxHeart']").show();
        }
    }

    if (document.getElementById("icon_search") !== null) {
        document.getElementById("icon_search").addEventListener('click', function () {
            sessionStorage.removeItem("sortTypeCode");
            $("#searchPlanResultForm").attr('action', '/searchPlan/keyword.trip').submit();
        });
    }

    document.querySelectorAll(".searchplan_result_item_contents_wrap").forEach(function (resultItem) {
        resultItem.addEventListener('click', function (event) {
            document.getElementById("input_plan_no").value = resultItem.firstElementChild.getAttribute("value");

            if (event.target.className !== "checkboxHeart") {
                $("#searchPlanResultForm").attr("action", "/plans/planMain.trip").submit();
            }
        })
    })

    $("input.checkboxHeart").on('click', function () {
        this.checked ? this.checked = true : this.checked = false;

        if (isAjaxProcessing) {
            popUpOpen('이전 요청을 처리중 입니다. 잠시 후 다시 시도하세요.');
            return;
        } else {
            isAjaxProcessing = true;
        }

        clickPlanLike(this,
            document.getElementById("input_plan_no").value,
            document.getElementById("input_member_no").value);
    });
});