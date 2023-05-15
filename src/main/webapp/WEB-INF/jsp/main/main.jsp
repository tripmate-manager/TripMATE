<%@ page import="com.tripmate.domain.InviteCodeVO" %>
<%@ page import="com.tripmate.domain.PopularPlanVO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tripmate.domain.PlanBasicInfoVO" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/include/commonImport.jsp" %>
<%@ include file="/WEB-INF/jsp/main/mainNavigationMenu.jsp" %>

<html>
<head>
    <jsp:include page="/WEB-INF/jsp/common/include/header.jsp">
        <jsp:param name="title" value="Main"/>
    </jsp:include>
    <jsp:include page="/WEB-INF/jsp/common/messagePopUp.jsp"/>
    <jsp:include page="/WEB-INF/jsp/common/checkPopUp.jsp"/>
    <jsp:include page="/WEB-INF/jsp/plans/planInviteCodePopUp.jsp"/>
    <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/main/main.css"/>
    <script src="<%=Const.STATIC_JS_PATH%>/main/main.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/common/popUp.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/common/checkPopUp.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/plans/planInviteCodePopUp.js"></script>
</head>
<body>
<%
    int unreadNotificationCnt = 0;
    List<PlanBasicInfoVO> myPlanLikeList = new ArrayList<>();
    List<PlanBasicInfoVO> userRecommendationPlanList = new ArrayList<>();
    InviteCodeVO inviteCodeVO = null;

    List<PopularPlanVO> popularPlanList = (List<PopularPlanVO>) request.getAttribute("popularPlanList");
    if (memberInfo != null) {
        unreadNotificationCnt = (int) request.getAttribute("unreadNotificationCnt");
        myPlanLikeList = (List<PlanBasicInfoVO>) request.getAttribute("myPlanLikeList");
        userRecommendationPlanList = (List<PlanBasicInfoVO>) request.getAttribute("userRecommendationPlanList");
    }

    if (request.getAttribute("inviteCodeInfo") != null) {
        inviteCodeVO = (InviteCodeVO) request.getAttribute("inviteCodeInfo");
    }
%>

<% if (inviteCodeVO != null) { %>
<input type=hidden id="invitePlanNo" value=<%=inviteCodeVO.getPlanNo()%>>
<input type=hidden id="inviteCode" value=<%=inviteCodeVO.getInviteCode()%>>
<input type=hidden id="inviteCodeExpireDateTime" value=<%=inviteCodeVO.getInviteCodeExpireDateTime()%>>
<% } %>

<div class="main_wrap">
    <form name="mainForm" id="mainForm" method="post">
        <% if (memberInfo != null) { %>
        <div id="session_member_no" style="display: none"><%=memberInfo.getMemberNo()%></div>
        <% } %>
        <input type=hidden id="member_no" name="memberNo">
        <input type="hidden" id="plan_no" name="planNo">

        <div class="main_title_wrap">
            <img class="main_menu_button" src="<%=Const.STATIC_IMG_PATH%>/common/icon_menu.png"/>
            <div onclick="history.back();" class="page_cover"></div>
            <div class="main_title"> TripMATE </div>
            <% if (memberInfo != null) { %>
            <div class="icon_alarm_wrap">
                <% if (unreadNotificationCnt > 0) { %>
                <img class="icon_alarm" id="icon_alarm_on" src="<%=Const.STATIC_IMG_PATH%>/main/icon_alarm_on.png"/>
                <% } else { %>
                <img class="icon_alarm" id="icon_alarm_off" src="<%=Const.STATIC_IMG_PATH%>/main/icon_alarm_off.png"/>
                <% } %>
            </div>
            <% } %>
        </div>
        <div class="main_search_wrap">
            <img class="icon_search" src="<%=Const.STATIC_IMG_PATH%>/common/icon_search.png">
            <div class="main_search_text" id="main_search_text">검색어를 입력하세요</div>
        </div>

        <div class="main_sub_title">현재 인기 플랜</div>
        <% if (popularPlanList != null && popularPlanList.size() > 0) {
        for (int i = 0; i < Math.min(popularPlanList.size(), 3); i++) { %>
            <div class="popular_plan_item_wrap" onclick="planMain(<%=popularPlanList.get(i).getPlanNo()%>)">
                <div class="popular_plan_item_rectangle"></div>
                <div class="main_plan_item_nick_name_wrap">
                    <span class="main_plan_item_nick_name"><%=popularPlanList.get(i).getLeaderNickName()%></span>
                    <span class="main_plan_item_nick_name_sub">님의</span>
                </div>
                <div class="main_plan_item_title"><%=popularPlanList.get(i).getPlanTitle()%></div>
                <img class="icon_main_rank" src="<%=Const.STATIC_IMG_PATH%>/main/icon_main_rank_<%=i+1%>.png"/>

                <% if (memberInfo != null) { %>
                <div class="icon_heart_wrap">
                    <input type="checkbox" name="checkboxHeart" id="popular_plan_checkboxHeart<%=popularPlanList.get(i).getPlanNo()%>" class="checkboxHeart"
                           <% if (popularPlanList.get(i).getPlanLikeCnt() > 0) { %>checked<% } %> onclick="planLike(<%=popularPlanList.get(i).getPlanNo()%>)">
                    <label for="popular_plan_checkboxHeart<%=popularPlanList.get(i).getPlanNo()%>" class="checkboxHeart"></label>
                </div>
                <% } %>
            </div>
        <% }
        } %>

        <div class="recommend_plan_wrap">
            <% String recommendPlanTitle;
                List<PlanBasicInfoVO> recommendPlanList = new ArrayList<>();
                if (memberInfo != null) {
                    recommendPlanList = userRecommendationPlanList;

                    Date date = new Date();
                    SimpleDateFormat year = new SimpleDateFormat("yyyy");

                    int memberAge = (Integer.parseInt(year.format(date)) - Integer.parseInt(memberInfo.getBirthDay().substring(0,4))) / 10 * 10;

                    recommendPlanTitle = (memberAge == 0 ? 10 : memberAge) + "대 "
                            + (memberInfo.getGenderCode().equals(ConstCode.GENDER_CODE_MALE) ? "남성" : "여성")
                            + "에게 인기 있는 플랜";
                } else {
                    recommendPlanTitle = "추천 플랜";
                }
            %>
            <div class="main_sub_title"><%=recommendPlanTitle%></div>

            <% for (int i = 0; i < recommendPlanList.size(); i++) { %>
            <div class="recommend_plan_item_wrap" onclick="planMain(<%=recommendPlanList.get(i).getPlanNo()%>)">
                <div class="main_plan_item_nick_name_wrap">
                    <span class="main_plan_item_nick_name"><%=recommendPlanList.get(i).getLeaderNickName()%></span>
                    <span class="main_plan_item_nick_name_sub">님의</span>
                </div>
                <div class="main_plan_item_title"><%=recommendPlanList.get(i).getPlanTitle()%></div>
                <div class="main_plan_item_trip_address"><%=recommendPlanList.get(i).getSidoName()%> <%=recommendPlanList.get(i).getSigunguName()%></div>
                <% String tripTerm = recommendPlanList.get(i).getTripTerm() == 0 ? "당일치기" : recommendPlanList.get(i).getTripTerm() + "박 " + (recommendPlanList.get(i).getTripTerm() + 1) + "일"; %>
                <div class="main_plan_item_trip_period"><%=tripTerm%></div>

                <% if (memberInfo != null) { %>
                <div class="icon_heart_wrap">
                    <input type="checkbox" name="checkboxHeart" id="checkboxHeart<%=recommendPlanList.get(i).getPlanNo()%>" class="checkboxHeart"
                           <% if (recommendPlanList.get(i).getPlanLikeCnt() > 0) { %>checked<% } %> onclick="planLike(<%=recommendPlanList.get(i).getPlanNo()%>)">
                    <label for="checkboxHeart<%=recommendPlanList.get(i).getPlanNo()%>" class="checkboxHeart"></label>
                </div>
                <% } %>
                <img class="icon_location" src="<%=Const.STATIC_IMG_PATH%>/common/icon_location.png"/>
                <img class="icon_calendar" src="<%=Const.STATIC_IMG_PATH%>/common/icon_calendar.png"/>
            </div>
            <% } %>
        </div>

        <% if (memberInfo != null) { %>
        <div class="my_like_plan_wrap">
            <div class="main_sub_title_wrap_like_plan">
                <div class="main_sub_title_like_plan">내가 찜한 플랜</div>
                <div class="main_sub_title_more" id="main_sub_title_more">더보기 &gt;</div>
            </div>
            <% if (myPlanLikeList.size() > 0) {
                for (int i = 0; i < Math.min(myPlanLikeList.size(), 2); i++) { %>
            <div class="my_like_plan_item_wrap" onclick="planMain(<%=myPlanLikeList.get(i).getPlanNo()%>)">
                <div class="my_like_plan_no" value="<%=myPlanLikeList.get(i).getPlanNo()%>"></div>
                <div class="main_plan_item_nick_name_wrap">
                    <span class="main_plan_item_nick_name"><%=myPlanLikeList.get(i).getLeaderNickName()%></span>
                    <span class="main_plan_item_nick_name_sub">님의</span>
                </div>
                <div class="main_plan_item_title"><%=myPlanLikeList.get(i).getPlanTitle()%></div>
                <div class="main_plan_item_trip_address"><%=myPlanLikeList.get(i).getSidoName()%> <%=myPlanLikeList.get(i).getSigunguName()%></div>
                <% String tripTerm = myPlanLikeList.get(i).getTripTerm() == 0 ? "당일치기" : myPlanLikeList.get(i).getTripTerm() + "박 " + (myPlanLikeList.get(i).getTripTerm() + 1) + "일"; %>
                <div class="main_plan_item_trip_period"><%=tripTerm%></div>
                <div class="icon_heart_wrap">
                    <input type="checkbox" name="checkboxHeart" id="checkboxHeart<%=myPlanLikeList.get(i).getPlanNo()%>" class="checkboxHeart"
                           <% if (myPlanLikeList.get(i).getPlanLikeCnt() > 0) { %>checked<% } %> onclick="planLike(<%=myPlanLikeList.get(i).getPlanNo()%>)">
                    <label for="checkboxHeart<%=myPlanLikeList.get(i).getPlanNo()%>" class="checkboxHeart"></label>
                </div>
                <img class="icon_location" src="<%=Const.STATIC_IMG_PATH%>/common/icon_location.png"/>
                <img class="icon_calendar" src="<%=Const.STATIC_IMG_PATH%>/common/icon_calendar.png"/>
            </div>
            <% }
            } %>
        </div>
        <% } %>
    </form>
</div>
</body>
</html>
