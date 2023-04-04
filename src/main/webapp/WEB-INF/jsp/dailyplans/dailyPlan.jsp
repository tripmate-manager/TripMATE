<%@ page import="com.tripmate.domain.DailyPlanVO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tripmate.domain.MemberDTO" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDateTime" %>
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

    List<DailyPlanVO> dailyPlanList = (List<DailyPlanVO>) request.getAttribute("dailyPlanList");
%>

<% if (memberInfo != null) { %>
<input type=hidden id="memberNo" value=<%=memberInfo.getMemberNo()%>>
<% } %>

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

        <% if (dailyPlanList != null) {
            for (DailyPlanVO dailyPlanVO : dailyPlanList) { %>
        <div class="dailyplan_item_list_wrap">
            <div class="dailyplan_item_sub_info_wrap">
                <div class="dailyplan_item_noti_time" id="dailyplan_item_noti_time" value="<%=dailyPlanVO.getDailyPlanDateTime()%>"><%=dailyPlanVO.getDailyPlanDateTime().substring(11, 16)%></div>
                <% if (Const.Y.equals(dailyPlanVO.getNotificationYn())) { %>
                <img class="icon_bell" src="<%=Const.STATIC_IMG_PATH%>/dailyplans/icon_bell.png"/>
                <% } %>
                <img class="icon_spot" src="<%=Const.STATIC_IMG_PATH%>/dailyplans/icon_spot.png"/>
                <div class="dailyplan_item_distance">0km</div>
            </div>
            <div class="dailyplan_item_wrap">
                <div class="dailyplan_item_post_no" hidden></div>
                <div class="dailyplan_item_title_wrap">
                    <div class="dailyplan_post_title"><%=dailyPlanVO.getPostTitle()%></div>
                    <div class="dailyplan_post_type">
                        <% if (ConstCode.POST_TYPE_CODE_LODGING.equals(dailyPlanVO.getPostTypeCode())) { %>숙소
                        <% } else if (ConstCode.POST_TYPE_CODE_TOUR.equals(dailyPlanVO.getPostTypeCode())) { %>관광지
                        <% } else if (ConstCode.POST_TYPE_CODE_RESTAURANT.equals(dailyPlanVO.getPostTypeCode())) { %>식당
                        <% } else { %>기타<% } %>
                    </div>
                    <details class="flag">
                        <summary class="ellipsis"></summary>
                        <div class="report">
                            <% if (LocalDateTime.now().isBefore(LocalDateTime.parse(dailyPlanVO.getDailyPlanDateTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S")))) { %>
                                <% if (Const.Y.equals(dailyPlanVO.getNotificationYn())) { %>
                                <p id="dailyplan_item_menu_noti_edit" onclick='updateDailyPlanNotification("<%=dailyPlanVO.getDailyPlanNo()%>")'>알림 수정하기</p>
                                <p id="dailyplan_item_menu_noti_delete" onclick='deleteDailyPlanNotification("<%=dailyPlanVO.getDailyPlanNo()%>")'>알림 삭제하기</p>
                                <% } else { %>
                                <p id="dailyplan_item_menu_noti" onclick='createDailyPlanNotification("<%=dailyPlanVO.getPlanNo()%>", "<%=dailyPlanVO.getDailyPlanNo()%>", "<%=dailyPlanVO.getDailyPlanDateTime()%>")'>알림 설정하기</p>
                                <% } %>
                            <% } else { %>
                            <p id="dailyplan_item_menu_review" onclick="">리뷰 작성하기</p>
                            <% } %>
                            <p id="dailyplan_item_menu_delete" onclick='deleteDailyPlan("<%=dailyPlanVO.getDailyPlanNo()%>")'>삭제하기</p>
                        </div>
                    </details>
                </div>
                <div class="dailyplan_post_contents"><%=dailyPlanVO.getPostContents()%></div>
                <div class="dailyplan_item_info_wrap">
                    <% if (!ConstCode.POST_TYPE_CODE_ETC.equals(dailyPlanVO.getPostTypeCode())) { %>
                    <img class="icon_location" src="<%=Const.STATIC_IMG_PATH%>/common/icon_location.png"/>
                    <div class="dailyplan_post_address"><%=dailyPlanVO.getSpotAddress()%></div>
                    <% } %>
                </div>
            </div>

            <div class="dailyplan_item_review_wrap">
                <div class="dailyplan_item_review">MATE 리뷰 : <% if (dailyPlanVO.getReviewAverageScore() != null) { %><%=dailyPlanVO.getReviewAverageScore()%><% } %></div>
                <img class="icon_arrow_right" src="<%=Const.STATIC_IMG_PATH%>/common/icon_arrow_right.png"/>
            </div>
        </div>
        <% }
        } %>
    </form>
</div>
</body>