<%@ page import="com.tripmate.domain.PlanVO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/include/commonImport.jsp" %>

<html>
<head>
    <jsp:include page="/WEB-INF/jsp/common/include/header.jsp">
        <jsp:param name="title" value="MyPlan"/>
    </jsp:include>
    <jsp:include page="/WEB-INF/jsp/common/messagePopUp.jsp"/>
    <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/plans/myPlan.css"/>
    <script src="<%=Const.STATIC_JS_PATH%>/plans/myPlan.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/plans/planValidationCheck.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/common/popUp.js"></script>
</head>
<body>
<%
    List<PlanVO> planList = (List<PlanVO>) request.getAttribute("planList");
%>

<input type=hidden id="planList" value=<%=planList%>>

<div class="myplanlist_wrap">
    <form id="myPlanForm" method="get">
        <div class="myplanlist_title_wrap">
            <img class="icon_arrow_left" src="<%=Const.STATIC_IMG_PATH%>/common/icon_arrow_left.png"
                 onclick="history.back()"/>
            <div class="myplanlist_title">My Plan</div>
            <img class="icon_plus_rectangle" src="<%=Const.STATIC_IMG_PATH%>/plans/icon_plus_rectangle.png"/>
        </div>
        <div class="myplanlist_divi_line">
        </div>
        <div class="myplanlist_list_wrap">
            <% if(planList.size() == 0) { %>
            <div class="myplanlist_empty_item_wrap">
                <div class="empty_item_text">플랜을 생성해주세요</div>
                <img class="icon_plus_circle" src="<%=Const.STATIC_IMG_PATH%>/plans/icon_plus_circle.png"/>
            </div>
            <% } else {
                for(PlanVO planVO : planList) { %>
                <div class="myplanlist_plan_item_wrap">
                    <div class="myplanList_item_no" value="<%=planVO.getPlanNo()%>"></div>
                    <div class="myplanlist_item_title_wrap">
                        <div class="myplanlist_item_title"><%=planVO.getPlanTitle()%>
                        </div>
                        <img class="icon_dot_menu" src="<%=Const.STATIC_IMG_PATH%>/common/icon_dot_menu.png"/>
                    </div>
                    <div class="myplanlist_item_desc"><%=planVO.getPlanDescription()%>
                    </div>
                    <div class="myplanlist_item_address_wrap">
                        <img class="icon_location" src="<%=Const.STATIC_IMG_PATH%>/common/icon_location.png"/>
                        <div class="myplanlist_plan_location"><%=planVO.getPlanAddressList().get(0).getSidoName()%> <%=planVO.getPlanAddressList().get(0).getSigunguName()%>
                        </div>
                    </div>
                    <div class="myplanlist_trip_term_wrap">
                        <img class="icon_calendar" src="<%=Const.STATIC_IMG_PATH%>/common/icon_calendar.png"/>
                        <div class="myplanlist_trip_term">여행일자 [&nbsp;</div>
                        <div class="myplanlist_trip_term" id="trip_term">
                            <% if (planVO.getTripTerm() == 0) { %>
                            당일치기
                            <% } else { %>
                            <%=planVO.getTripTerm()%>박&nbsp;<%=planVO.getTripTerm() + 1%>일 <%
                            } %>
                        </div>
                        <div class="myplanlist_trip_term">&nbsp;]</div>
                    </div>
                </div>
            <% }
            } %>
        </div>
    </form>
</div>
</body>
</html>
