<%@ page import="com.tripmate.domain.PlanBasicInfoVO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tripmate.domain.MemberDTO" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/include/commonImport.jsp" %>

<html>
<head>
    <jsp:include page="/WEB-INF/jsp/common/include/header.jsp">
        <jsp:param name="title" value="SearchPlanResult"/>
    </jsp:include>
    <jsp:include page="/WEB-INF/jsp/common/messagePopUp.jsp"/>
    <jsp:include page="/WEB-INF/jsp/common/checkPopUp.jsp"/>
    <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/searchplan/searchPlanResult.css"/>
    <script src="<%=Const.STATIC_JS_PATH%>/searchplan/searchPlanResult.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/common/popUp.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/common/checkPopUp.js"></script>
</head>
<body>
<%
    MemberDTO memberInfo = null;
    session = request.getSession();
    if (session != null) {
        memberInfo = (MemberDTO) session.getAttribute(Const.MEMBER_INFO_SESSION);
    }

    List<PlanBasicInfoVO> searchPlanResultList = (List<PlanBasicInfoVO>) request.getAttribute("searchPlanResultList");
    String keyword = (String) request.getAttribute("keyword");
%>


<div class="searchplan_result_wrap">
    <form id="searchPlanResultForm" method="post">
        <% if (memberInfo != null) { %>
        <div id="session_member_no" style="display: none"><%=memberInfo.getMemberNo()%></div>
        <% } %>
        <input type=hidden id="input_member_no" name="memberNo" hidden>
        <input type=hidden id="input_plan_no" name="planNo" hidden>

        <div class="input_search_wrap">
            <img class="icon_arrow_left" id="icon_arrow_left" src="<%=Const.STATIC_IMG_PATH%>/common/icon_arrow_left.png"/>
            <% if (keyword != null) { %>
            <input type="text" name="keyword" class="input_search" id="input_search" placeholder="<%=keyword%>">
            <img class="icon_search" id="icon_search" src="<%=Const.STATIC_IMG_PATH%>/common/icon_search.png"/>
            <% } %>
        </div>
        <div class="searchplan_result_divi_line"></div>

        <label class="select_option_sort"></label>
        <select name="select_option_sort" class="select_option" id="select_option_sort">
            <option id="sort_recent" value="<%=ConstCode.SEARCH_RESULT_SORT_RECENT%>">최신 순</option>
            <option id="sort_popular" value="<%=ConstCode.SEARCH_RESULT_SORT_POPULAR%>">인기 순</option>
            <option id="sort_oldest" value="<%=ConstCode.SEARCH_RESULT_SORT_OLDEST%>">오래된 순</option>
            <option id="sort_achieve_rate" value="<%=ConstCode.SEARCH_RESULT_SORT_ACHIEVE_RATE%>">달성률 순</option>
            <option id="sort_review" value="<%=ConstCode.SEARCH_RESULT_SORT_REVIEW%>">리뷰평점 순</option>
        </select>

        <div class="searchplan_result_list_wrap">
            <% if (searchPlanResultList != null) {
                for (PlanBasicInfoVO searchPlanResultVO : searchPlanResultList) { %>
            <div class="searchplan_result_plan_item_wrap">
                <div class="searchPlanResultItemForm">
                    <div type="hidden" class="item_plan_no" name="planNo" value="<%=searchPlanResultVO.getPlanNo()%>"></div>
                    <div type="hidden" class="item_plan_title" name="planTitle" value="<%=searchPlanResultVO.getPlanTitle()%>"></div>
                    <div type="hidden" class="item_plan_desc" name="planDescription" value="<%=searchPlanResultVO.getPlanDescription()%>"></div>
                    <div type="hidden" class="item_trip_start_date" name="tripStartDate" value="<%=searchPlanResultVO.getTripStartDate()%>"></div>
                    <div type="hidden" class="item_trip_end_date" name="tripEndDate" value="<%=searchPlanResultVO.getTripEndDate()%>"></div>
                    <div type="hidden" class="item_trip_term" name="tripTerm" value="<%=searchPlanResultVO.getTripTerm()%>"></div>
                    <div type="hidden" class="item_like_registration_cnt" name="likeRegistrationCnt" value="<%=searchPlanResultVO.getLikeRegistrationCnt()%>"></div>
                    <div type="hidden" class="item_achieve_rate" name="achieveRate" value="<%=searchPlanResultVO.getAchieveRate()%>"></div>
                    <div type="hidden" class="item_like_views" name="views" value="<%=searchPlanResultVO.getViews()%>"></div>
                    <div type="hidden" class="item_review_average_score" name="reviewAverageScore" value="<%=searchPlanResultVO.getReviewAverageScore()%>"></div>
                    <div type="hidden" class="item_sido_name" name="sidoName" value="<%=searchPlanResultVO.getSidoName()%>"></div>
                    <div type="hidden" class="item_sigungu_name" name="sigunguName" value="<%=searchPlanResultVO.getSigunguName()%>"></div>
                    <div type="hidden" class="item_leader_nick_name" name="leaderNickName" value="<%=searchPlanResultVO.getLeaderNickName()%>"></div>
                    <div type="hidden" class="item_registration_date_time" name="registrationDateTime" value="<%=searchPlanResultVO.getRegistrationDateTime()%>"></div>
                    <div type="hidden" class="item_plan_like_cnt" name="planLikeCnt" value="<%=searchPlanResultVO.getPlanLikeCnt()%>"></div>
                </div>
            </div>
            <% }
            } %>
        </div>
    </form>
</div>
</body>
</html>