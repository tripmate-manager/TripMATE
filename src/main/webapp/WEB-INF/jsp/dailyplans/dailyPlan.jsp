<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/include/commonImport.jsp" %>

<html>
<head>
    <jsp:include page="/WEB-INF/jsp/common/include/header.jsp">
        <jsp:param name="title" value="DailyPlan"/>
    </jsp:include>
    <jsp:include page="/WEB-INF/jsp/common/messagePopUp.jsp"/>
    <jsp:include page="/WEB-INF/jsp/common/checkPopUp.jsp"/>
    <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/dailyplans/dailyPlan.css"/>
    <script src="<%=Const.STATIC_JS_PATH%>/dailyplans/dailyPlan.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/common/popUp.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/common/checkPopUp.js"></script>
</head>
<body>

<div class="dailyplan_wrap">
    <img class="icon_arrow_left" src="<%=Const.STATIC_IMG_PATH%>/common/icon_arrow_left.png"
         onclick="history.back()"/>
    <div class="dailyplan_title_wrap">
        <div class="dailyplan_title">Day 1</div>
        <div class="dailyplan_rate_wrap">
            <div class="dailyplan_rate_box">
                <div class="dailyplan_rate_number">10</div>
                <div class="dailyplan_rate_percent">%</div>
            </div>
        </div>
    </div>
    <div class="dailyplan_divi_line"></div>

    <form name="dailyplanForm" id="dailyplanForm" method="post">

        <input type="hidden" name="postNo" class="dailyplan_post_no" id="dailyplan_post_no">

        <div class="dailyplan_item_list_wrap">
            <div class="dailyplan_item_sub_info_wrap">
                <div class="dailyplan_item_noti_time">17:00</div>
                <img class="icon_bell" src="<%=Const.STATIC_IMG_PATH%>/dailyplans/icon_bell.png"/>
                <img class="icon_spot" src="<%=Const.STATIC_IMG_PATH%>/dailyplans/icon_spot.png"/>
                <div class="dailyplan_item_distance">10.3km</div>
            </div>
            <div class="dailyplan_item_wrap">
                <div class="dailyplan_item_post_no" hidden></div>
                <div class="dailyplan_item_title_wrap">
                    <div class="dailyplan_post_title">제목
                    </div>
                    <div class="dailyplan_post_type">숙소</div>
                    <input type="checkbox" name="bookmarkYn" id="checkboxBookmark" class="checkboxBookmark">
                    <label for="checkboxBookmark"></label>
                </div>
                <div class="dailyplan_post_contents">본문
                </div>
                <div class="dailyplan_item_info_wrap">
                    <img class="icon_location" src="<%=Const.STATIC_IMG_PATH%>/common/icon_location.png"/>
                    <div class="dailyplan_post_address">주소주소주소주소주소주소주소주소주소
                    </div>
                    <img class="icon_comment" src="<%=Const.STATIC_IMG_PATH%>/wishlist/icon_comment.png"/>
                    <div class="dailyplan_post_comment_count">3
                    </div>
                </div>
            </div>

            <div class="dailyplan_item_review_wrap">
                <div class="dailyplan_item_review">MATE 리뷰 : 2.5</div>
                <img class="icon_arrow_right" src="<%=Const.STATIC_IMG_PATH%>/common/icon_arrow_right.png"/>
            </div>
        </div>
    </form>
</div>
</body>