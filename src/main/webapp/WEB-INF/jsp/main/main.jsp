<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include/commonImport.jsp" %>
<%@ include file="/WEB-INF/jsp/main/mainNavigationMenu.jsp" %>

<html>
<head>
    <jsp:include page="/WEB-INF/jsp/common/include/header.jsp">
        <jsp:param name="title" value="Main"/>
    </jsp:include>
    <jsp:include page="/WEB-INF/jsp/common/messagePopUp.jsp"/>
    <jsp:include page="/WEB-INF/jsp/common/checkPopUp.jsp"/>
    <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/main/main.css"/>
    <script src="<%=Const.STATIC_JS_PATH%>/main/main.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/common/popUp.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/common/checkPopUp.js"></script>
</head>
<body>
<div class="main_wrap">
    <div class="main_title_wrap">
        <img class="main_menu_button" src="<%=Const.STATIC_IMG_PATH%>/common/icon_menu.png"/>
        <div onclick="history.back();" class="page_cover"></div>
        <div class="main_title">TripMATE</div>
        <div class="icon_alarm_wrap"  style="display: none;">
            <img class="icon_alarm" id="icon_alarm_off" src="<%=Const.STATIC_IMG_PATH%>/main/icon_alarm_off.png" />
            <img class="icon_alarm" id="icon_alarm_on" src="<%=Const.STATIC_IMG_PATH%>/main/icon_alarm_on.png" />
        </div>
    </div>
    <div class="main_search_wrap">
        <img class="icon_search" src="<%=Const.STATIC_IMG_PATH%>/common/icon_search.png">
        <div class="main_search_text">검색어를 입력하세요</div>
    </div>
    <div class="main_sub_title">현재 인기 플랜</div>
    <div class="popular_plan_item_wrap">
        <div class="popular_plan_item_rectangle"></div>
        <div class="main_plan_item_nick_name_wrap">
            <span class="main_plan_item_nick_name">닉네임1</span>
            <span class="main_plan_item_nick_name_sub">님의</span>
        </div>
        <div class="main_plan_item_title">부산 여행</div>
        <img class="icon_main_rank" src="<%=Const.STATIC_IMG_PATH%>/main/icon_main_rank_1.png"/>
        <div class="icon_heart_wrap">
            <img class="icon_heart" src="<%=Const.STATIC_IMG_PATH%>/common/icon_heart_filled.png"/>
            <img class="icon_heart" style="display: none;" src="<%=Const.STATIC_IMG_PATH%>/common/icon_heart_unfilled.png"/>
        </div>
    </div>
    <div class="popular_plan_item_wrap">
        <div class="popular_plan_item_rectangle"></div>
        <div class="main_plan_item_nick_name_wrap">
            <span class="main_plan_item_nick_name">닉네임1</span>
            <span class="main_plan_item_nick_name_sub">님의</span>
        </div>
        <div class="main_plan_item_title">부산 여행</div>
        <img class="icon_main_rank" src="<%=Const.STATIC_IMG_PATH%>/main/icon_main_rank_2.png"/>
        <div class="icon_heart_wrap">
            <img class="icon_heart" style="display: none;" src="<%=Const.STATIC_IMG_PATH%>/common/icon_heart_filled.png"/>
            <img class="icon_heart" src="<%=Const.STATIC_IMG_PATH%>/common/icon_heart_unfilled.png"/>
        </div>
    </div>
    <div class="popular_plan_item_wrap">
        <div class="popular_plan_item_rectangle"></div>
        <div class="main_plan_item_nick_name_wrap">
            <span class="main_plan_item_nick_name">닉네임1</span>
            <span class="main_plan_item_nick_name_sub">님의</span>
        </div>
        <div class="main_plan_item_title">부산 여행</div>
        <img class="icon_main_rank" src="<%=Const.STATIC_IMG_PATH%>/main/icon_main_rank_3.png"/>
        <div class="icon_heart_wrap">
            <img class="icon_heart" style="display: none;" src="<%=Const.STATIC_IMG_PATH%>/common/icon_heart_filled.png"/>
            <img class="icon_heart" src="<%=Const.STATIC_IMG_PATH%>/common/icon_heart_unfilled.png"/>
        </div>
    </div>

    <div class="recommend_plan_wrap">
        <div class="main_sub_title">추천 플랜</div>
        <div class="recommend_plan_item_wrap">
            <div class="main_plan_item_nick_name_wrap">
                <span class="main_plan_item_nick_name">닉네임1</span>
                <span class="main_plan_item_nick_name_sub">님의</span>
            </div>
            <div class="main_plan_item_title">부산 여행</div>
            <div class="main_plan_item_trip_address">부산광역시</div>
            <div class="main_plan_item_trip_period">3박 4일</div>
            <div class="icon_heart_wrap">
                <img class="icon_heart" src="<%=Const.STATIC_IMG_PATH%>/common/icon_heart_filled.png"/>
                <img class="icon_heart" style="display: none;" src="<%=Const.STATIC_IMG_PATH%>/common/icon_heart_unfilled.png"/>
            </div>
            <img class="icon_location" src="<%=Const.STATIC_IMG_PATH%>/common/icon_location.png"/>
            <img class="icon_calendar" src="<%=Const.STATIC_IMG_PATH%>/common/icon_calendar.png"/>
        </div>
        <div class="recommend_plan_item_wrap">
            <div class="main_plan_item_nick_name_wrap">
                <span class="main_plan_item_nick_name">닉네임1</span>
                <span class="main_plan_item_nick_name_sub">님의</span>
            </div>
            <div class="main_plan_item_title">부산 여행</div>
            <div class="main_plan_item_trip_address">부산광역시</div>
            <div class="main_plan_item_trip_period">3박 4일</div>
            <div class="icon_heart_wrap">
                <img class="icon_heart" src="<%=Const.STATIC_IMG_PATH%>/common/icon_heart_filled.png"/>
                <img class="icon_heart" style="display: none;" src="<%=Const.STATIC_IMG_PATH%>/common/icon_heart_unfilled.png"/>
            </div>
            <img class="icon_location" src="<%=Const.STATIC_IMG_PATH%>/common/icon_location.png"/>
            <img class="icon_calendar" src="<%=Const.STATIC_IMG_PATH%>/common/icon_calendar.png"/>
        </div>
    </div>

    <div class="my_like_plan_wrap" style="display: none">
        <div class="main_sub_title_wrap_like_plan">
            <div class="main_sub_title_like_plan">내가 찜한 플랜</div>
            <div class="main_sub_title_more">더보기 &gt;</div>
        </div>
        <div class="my_like_plan_item_wrap">
            <div class="main_plan_item_nick_name_wrap">
                <span class="main_plan_item_nick_name">닉네임1</span>
                <span class="main_plan_item_nick_name_sub">님의</span>
            </div>
            <div class="main_plan_item_title">부산 여행</div>
            <div class="main_plan_item_trip_address">부산광역시</div>
            <div class="main_plan_item_trip_period">3박 4일</div>
            <div class="icon_heart_wrap">
                <img class="icon_heart" src="<%=Const.STATIC_IMG_PATH%>/common/icon_heart_filled.png"/>
                <img class="icon_heart" style="display: none;" src="<%=Const.STATIC_IMG_PATH%>/common/icon_heart_unfilled.png"/>
            </div>
            <img class="icon_location" src="<%=Const.STATIC_IMG_PATH%>/common/icon_location.png"/>
            <img class="icon_calendar" src="<%=Const.STATIC_IMG_PATH%>/common/icon_calendar.png"/>
        </div>
    </div>
</div>
</body>
</html>
