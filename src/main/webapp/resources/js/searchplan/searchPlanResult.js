$(function () {
    const backBtn = document.getElementById("icon_arrow_left");

    backBtn.addEventListener('click', function () {
        $("#searchPlanResultForm").attr("method", "get").attr("action", "/searchPlan/search.trip").submit();
    });
});