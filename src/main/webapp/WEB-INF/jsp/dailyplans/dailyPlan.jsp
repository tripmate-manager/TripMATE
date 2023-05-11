<%@ page import="com.tripmate.domain.DailyPlanVO" %>
<%@ page import="com.tripmate.domain.MemberDTO" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="com.tripmate.domain.DailyPlanItemVO" %>
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
    <jsp:include page="/WEB-INF/jsp/dailyplans/dailyPlanNotificationPopUp.jsp"/>
    <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/dailyplans/dailyPlan.css"/>
    <script src="<%=Const.STATIC_JS_PATH%>/dailyplans/dailyPlan.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/common/popUp.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/common/checkPopUp.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/dailyplans/dailyPlanNotificationPopUp.js"></script>
</head>
<body>
<%
    MemberDTO memberInfo = null;
    session = request.getSession();

    if (session != null) {
        memberInfo = (MemberDTO) session.getAttribute(Const.MEMBER_INFO_SESSION);
    }

    String dayGroup = (String) request.getAttribute("dayGroup");
    DailyPlanVO dailyPlanVO = (DailyPlanVO) request.getAttribute("dailyPlanVO");
%>

<div class="dailyplan_wrap">
    <img class="icon_arrow_left" src="<%=Const.STATIC_IMG_PATH%>/common/icon_arrow_left.png"/>
    <div class="dailyplan_title_wrap">
        <div class="dailyplan_title">Day <%=dayGroup%></div>
        <div class="dailyplan_rate_wrap">
            <div class="dailyplan_rate_box">
                <div class="dailyplan_rate_number">
                    <% String achieveRate = dailyPlanVO.getAchieveRate() == null ? "0" : dailyPlanVO.getAchieveRate(); %>
                    <%=achieveRate%>
                </div>
                <div class="dailyplan_rate_percent">%</div>
            </div>
        </div>
    </div>
    <div class="dailyplan_divi_line"></div>

    <form name="dailyplanForm" id="dailyplanForm" method="post">
        <input type="hidden" name="dailyPlanNo" class="dailyplan_no" id="dailyplan_no">
        <input type="hidden" name="postTypeCode" class="postTypeCode" id="dailyplan_post_type_code">
        <input type="hidden" name="dayGroup" class="day_group" id="day_group" value="<%=dayGroup%>">
        <input type="hidden" name="planNo" class="plan_no" id="plan_no" value="<%=dailyPlanVO.getPlanNo()%>">
        <% if (memberInfo != null) { %>
        <div id="session_member_no" style="display: none"><%=memberInfo.getMemberNo()%></div>
        <% } %>
        <input type=hidden id="member_no" id="member_no" name="memberNo" hidden>

        <% if (dailyPlanVO.getDailyPlanItemList().size() > 0) {
        for (DailyPlanItemVO dailyPlanItem : dailyPlanVO.getDailyPlanItemList()) { %>
        <div class="dailyplan_item_list_wrap">
            <div class="dailyplan_item_sub_info_wrap">
                <div class="dailyplan_item_noti_time" id="dailyplan_item_noti_time" value="<%=dailyPlanItem.getDailyPlanDateTime()%>"><%=dailyPlanItem.getDailyPlanDateTime().substring(11, 16)%></div>
                <% if (LocalDateTime.now().isBefore(LocalDateTime.parse(dailyPlanItem.getDailyPlanDateTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S")))
                        && Const.Y.equals(dailyPlanItem.getNotificationYn())) { %>
                <img class="icon_bell" src="<%=Const.STATIC_IMG_PATH%>/dailyplans/icon_bell.png"/>
                <% } %>
                <img class="icon_spot" src="<%=Const.STATIC_IMG_PATH%>/dailyplans/icon_spot.png"/>
                <div class="dailyplan_item_distance">0km</div>
            </div>
            <div class="dailyplan_item_wrap">
                <div class="dailyplan_item_post_no" hidden></div>
                <div class="dailyplan_item_title_wrap">
                    <div class="dailyplan_post_title"><%=dailyPlanItem.getPostTitle()%></div>
                    <div class="dailyplan_post_type">
                        <% if (ConstCode.POST_TYPE_CODE_LODGING.equals(dailyPlanItem.getPostTypeCode())) { %>숙소
                        <% } else if (ConstCode.POST_TYPE_CODE_TOUR.equals(dailyPlanItem.getPostTypeCode())) { %>관광지
                        <% } else if (ConstCode.POST_TYPE_CODE_RESTAURANT.equals(dailyPlanItem.getPostTypeCode())) { %>식당
                        <% } else { %>기타<% } %>
                    </div>
                    <details class="flag">
                        <summary class="ellipsis"></summary>
                        <div class="report">
                            <% if (LocalDateTime.now().isBefore(LocalDateTime.parse(dailyPlanItem.getDailyPlanDateTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S")))) { %>
                            <% if (Const.Y.equals(dailyPlanItem.getNotificationYn())) { %>
                            <p id="dailyplan_item_menu_noti_edit" onclick='updateDailyPlanNotification("<%=dailyPlanVO.getPlanNo()%>", "<%=dailyPlanItem.getDailyPlanNo()%>", "<%=dailyPlanItem.getDailyPlanDateTime()%>")'>알림 수정하기</p>
                            <p id="dailyplan_item_menu_noti_delete" onclick='deleteDailyPlanNotification("<%=dailyPlanItem.getDailyPlanNo()%>")'>알림 삭제하기</p>
                            <% } else { %>
                            <p id="dailyplan_item_menu_noti" onclick='createDailyPlanNotification("<%=dailyPlanVO.getPlanNo()%>", "<%=dailyPlanItem.getDailyPlanNo()%>", "<%=dailyPlanItem.getDailyPlanDateTime()%>")'>알림 설정하기</p>
                            <% } %>
                            <% } else { %>
                            <p id="dailyplan_item_menu_review" onclick='createReview("<%=dailyPlanItem.getDailyPlanNo()%>", "<%=dailyPlanItem.getPostTypeCode()%>")'>리뷰 작성하기</p>
                            <% } %>
                            <p id="dailyplan_item_menu_delete" onclick='deleteDailyPlan("<%=dailyPlanItem.getDailyPlanNo()%>")'>삭제하기</p>
                        </div>
                    </details>
                </div>
                <div class="dailyplan_post_contents"><%=dailyPlanItem.getPostContents()%></div>
                <div class="dailyplan_item_info_wrap">
                    <% if (!ConstCode.POST_TYPE_CODE_ETC.equals(dailyPlanItem.getPostTypeCode())) { %>
                    <img class="icon_location" src="<%=Const.STATIC_IMG_PATH%>/common/icon_location.png"/>
                    <div class="dailyplan_post_address"><%=dailyPlanItem.getSpotAddress()%></div>
                    <% } %>
                </div>
            </div>

            <div class="dailyplan_item_review_wrap">
                <div class="dailyplan_item_review">MATE 리뷰 : <% if (dailyPlanItem.getReviewAverageScore() != null) { %><%=dailyPlanItem.getReviewAverageScore()%><% } %></div>
                <img class="icon_arrow_right" onclick="viewReviewList(<%=dailyPlanItem.getDailyPlanNo()%>, <%=dailyPlanItem.getPostTypeCode()%>)" src="<%=Const.STATIC_IMG_PATH%>/common/icon_arrow_right.png"/>
            </div>
        </div>
        <% }
        } %>
    </form>
</div>
</body>