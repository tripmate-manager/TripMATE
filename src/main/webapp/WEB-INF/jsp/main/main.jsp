<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/include/commonImport.jsp" %>
<%@ include file="/WEB-INF/jsp/main/mainNavigationMenu.jsp" %>

<html>
<head>
    <jsp:include page="../common/include/header.jsp">
        <jsp:param name="title" value="Main"/>
    </jsp:include>
    <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/main/main.css"/>
    <script src="<%=Const.STATIC_JS_PATH%>/main/main.js"></script>
</head>
<body>
<div class="main__wrap">
    <div onclick="history.back();" class="page_cover"></div>
    <div class="main_menu_header">
        <img class="main_menu_button" src="<%=Const.STATIC_IMG_PATH%>/common/icon_menu.png"/>
        <div class="main_menu_title">TripMATE</div>
    </div>
    <div class="main_search_box">
        <img class="search_icon" src="<%=Const.STATIC_IMG_PATH%>/main/icon_search.png"/>
        <div class="search_box">검색어를 입력하세요</div>
    </div>
    <div class="main_sub_title">현재 인기 플랜</div>
    <div class="popular_plan_item_wrap">
        <div class="rectangle-18-3ZD">
        </div>
        <div class="popular_plan_item">
            <span class="popular_plan_item_nickname">닉네임2</span>
            <span class="popular_plan_item_sub">님의</span>
            <div class="popular_plan_item_plan">부산 여행</div>
        </div>
        <img class="popular_plan_rank_icon" src="<%=Const.STATIC_IMG_PATH%>/main/icon_main_rank_1.png"/>
    </div>
    <div class="popular_plan_item_wrap">
        <div class="rectangle-18-3ZD">
            <div class="popular_plan_item">
                <span class="popular_plan_item_nickname">닉네임1</span>
                <span class="popular_plan_item_sub">님의</span>
                <div class="popular_plan_item_plan">제주 한 달 살기</div>
            </div>
            <img class="popular_plan_rank_icon" src="<%=Const.STATIC_IMG_PATH%>/main/icon_main_rank_2.png"/>
        </div>
    </div>
    <div class="popular_plan_item_wrap">
        <div class="rectangle-18-3ZD">
        </div>
        <div class="popular_plan_item">
            <span class="popular_plan_item_nickname">닉네임3</span>
            <span class="popular_plan_item_sub">님의</span>
            <div class="popular_plan_item_plan">강릉 당일치기</div>
        </div>
        <img class="popular_plan_rank_icon" src="<%=Const.STATIC_IMG_PATH%>/main/icon_main_rank_3.png"/>
    </div>

    <div class="main_sub_title">추천 플랜</div>
    <div class="recommend_plan_item">
        <div class="item--6nw">부산 여행</div>
        <div class="item--Qod">부산광역시</div>
        <img class="codicon-heart-filled-E1y" src="<%=Const.STATIC_IMG_PATH%>/main/icon_heart_filled.png"/>
        <div class="rectangle-24-McP">
        </div>
        <div class="recommend_plan_item_title">
            <span class="recommend_plan_item_title-sub-0">닉네임1</span>
            <span class="recommend_plan_item_title-sub-1">님의</span>
            <span class="recommend_plan_item_title-sub-2"> </span>
        </div>
        <div class="item--qvs">부산 여행</div>
        <div class="item--9wZ">부산광역시</div>
        <div class="item-3-4-Fzb">3박 4일</div>
        <img class="spot_icon" src="<%=Const.STATIC_IMG_PATH%>/main/icon_location.png"/>
        <img class="calender_icon" src="<%=Const.STATIC_IMG_PATH%>/main/icon_calendar.png"/>
    </div>
    <div class="recommend_plan_item">
        <div class="item--6nw">부산 여행</div>
        <div class="item--Qod">부산광역시</div>
        <img class="codicon-heart-filled-E1y" src="<%=Const.STATIC_IMG_PATH%>/main/icon_heart_filled.png"/>
        <div class="rectangle-24-McP">
        </div>
        <div class="recommend_plan_item_title">
            <span class="recommend_plan_item_title-sub-0">닉네임2</span>
            <span class="recommend_plan_item_title-sub-1">님의</span>
            <span class="recommend_plan_item_title-sub-2"> </span>
        </div>
        <div class="item--qvs">부산 여행</div>
        <div class="item--9wZ">부산광역시</div>
        <div class="item-3-4-Fzb">3박 4일</div>
        <img class="spot_icon" src="<%=Const.STATIC_IMG_PATH%>/main/icon_location.png"/>
        <img class="calender_icon" src="<%=Const.STATIC_IMG_PATH%>/main/icon_calendar.png"/>
    </div>
</div>
</body>
</html>
