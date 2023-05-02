<%@ page import="com.tripmate.domain.SearchPlanResultVO" %>
<%@ page import="java.util.List" %>
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
    <script src="<%=Const.STATIC_JS_PATH%>/plans/myPlan.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/common/popUp.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/common/checkPopUp.js"></script>
</head>
<body>
<%
    List<SearchPlanResultVO> searchPlanResultList = (List<SearchPlanResultVO>) request.getAttribute("searchPlanResultList");
%>

<div class="searchplan_result_wrap">
    <form id="searchPlanResultForm">
        <div class="input_search_wrap">
            <img class="icon_arrow_left" id="icon_arrow_left" src="<%=Const.STATIC_IMG_PATH%>/common/icon_arrow_left.png"/>
            <input type="text" name="searchKeyword" class="input_search" id="input_search" placeholder="검색어를 입력하세요">
            <img class="icon_search" id="icon_search" src="<%=Const.STATIC_IMG_PATH%>/common/icon_search.png"/>
        </div>
        <div class="searchplan_result_divi_line"></div>

        <label class="select_option_sort"></label>
        <select name="select_option_sort" class="select_option" id="select_option_sort">
            <option value="sort_recent">최신 순</option>
            <option value="sort_popular">인기 순</option>
            <option value="sort_oldest">오래된 순</option>
            <option value="sort_achieve_rate">달성률 순</option>
            <option value="sort_review">리뷰평점 순</option>
        </select>

        <%--        <div class="search_attribute_list">--%>
        <%--            <div class="search_attribute">가을여행</div>--%>
        <%--            <div class="search_attribute">제주맛집</div>--%>
        <%--            <div class="search_attribute">당일치기</div>--%>
        <%--        </div>--%>

        <div class="searchplan_result_list_wrap">
            <% if (searchPlanResultList != null) {
            for (SearchPlanResultVO searchPlanResult : searchPlanResultList) { %>
            <input type=hidden id="planVO" value="planVO">
            <div class="searchplan_result_plan_item_wrap">
                <div class="searchplan_result_item_contents_wrap">
                    <form id="planNoForm" method="post">
                        <div id="searchplan_result_item_no" value="<%=searchPlanResult.getPlanNo()%>"></div>
                        <input type="hidden" class="searchplan_result_item_plan_no" name="planNo"
                               value="<%=searchPlanResult.getPlanNo()%>">
                    </form>
                    <div class="searchplan_result_item_title_wrap">
                        <div class="searchplan_result_item_title"><%=searchPlanResult.getPlanTitle()%></div>
                    </div>
                    <div class="searchplan_result_item_desc"><%=searchPlanResult.getPlanDescription()%></div>
                    <div class="searchplan_result_item_wrap">
                        <img class="icon_location" src="<%=Const.STATIC_IMG_PATH%>/searchplan/icon_leader.png"/>
                        <div class="searchplan_result_plan_text"><%=searchPlanResult.getLeaderNickName()%>
                        </div>
                    </div>
                    <div class="searchplan_result_item_wrap">
                        <img class="icon_location" src="<%=Const.STATIC_IMG_PATH%>/common/icon_location.png"/>
                        <div class="searchplan_result_plan_text"><%=searchPlanResult.getSidoName()%> <%=searchPlanResult.getSigunguName()%>
                        </div>
                    </div>
                    <div class="searchplan_result_item_wrap">
                        <img class="icon_calendar" src="<%=Const.STATIC_IMG_PATH%>/common/icon_calendar.png"/>
                        <% String tripTerm = searchPlanResult.getTripTerm() == 0 ? "당일치기" : searchPlanResult.getTripTerm() + "박 " + (searchPlanResult.getTripTerm() + 1) + "일"; %>
                        <div class="searchplan_result_plan_text" id="trip_term"><%=searchPlanResult.getTripStartDate()%> ~ <%=searchPlanResult.getTripEndDate()%> [ <%=tripTerm%> ]</div>
                    </div>
                </div>
            </div>
            <% }
            } %>
        </div>
    </form>
</div>
</body>
</html>