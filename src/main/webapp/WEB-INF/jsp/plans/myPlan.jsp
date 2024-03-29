<%@ page import="com.tripmate.domain.PlanVO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tripmate.domain.MemberDTO" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/include/commonImport.jsp" %>

<html>
<head>
    <jsp:include page="/WEB-INF/jsp/common/include/header.jsp">
        <jsp:param name="title" value="MyPlan"/>
    </jsp:include>
    <jsp:include page="/WEB-INF/jsp/common/messagePopUp.jsp"/>
    <jsp:include page="/WEB-INF/jsp/common/checkPopUp.jsp"/>
    <jsp:include page="/WEB-INF/jsp/plans/planAuthorityPopUp.jsp"/>
    <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/plans/myPlan.css"/>
    <script src="<%=Const.STATIC_JS_PATH%>/plans/myPlan.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/common/popUp.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/common/checkPopUp.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/plans/planAuthorityPopUp.js"></script>
</head>
<body>
<%
    List<PlanVO> planList = (List<PlanVO>) request.getAttribute("planList");
    MemberDTO memberInfo = null;
    session = request.getSession();
    if (session != null) {
        memberInfo = (MemberDTO) session.getAttribute(Const.MEMBER_INFO_SESSION);
    }
%>

<div class="myplanlist_wrap">
    <form id="myPlanForm">
        <div class="myplanlist_title_wrap">
            <img class="icon_arrow_left" src="<%=Const.STATIC_IMG_PATH%>/common/icon_arrow_left.png"/>
            <div class="myplanlist_title">My Plan</div>
            <img class="icon_plus_rectangle" src="<%=Const.STATIC_IMG_PATH%>/plans/icon_plus_rectangle.png"/>
        </div>
        <div class="myplanlist_divi_line"></div>

        <div class="myplanlist_list_wrap">
            <% if (planList.size() == 0) { %>
            <div class="myplanlist_empty_item_wrap">
                <div class="empty_item_text">플랜을 생성해주세요</div>
                <img class="icon_plus_circle" src="<%=Const.STATIC_IMG_PATH%>/plans/icon_plus_circle.png"/>
            </div>
            <% } else {
                for (PlanVO planVO : planList) { %>
            <input type=hidden id="planVO" value=<%=planVO%>>
            <div class="myplanlist_plan_item_wrap">
                <div class="myplanlist_item_contents_wrap">
                    <form id="planNoForm" method="post">
                        <input type=hidden id="memberNo" name="memberNo" value=<%=memberInfo.getMemberNo()%>>
                        <div id="myplanList_item_no" value="<%=planVO.getPlanNo()%>"></div>
                        <input type="hidden" class="myplanlist_item_plan_no" name="planNo" value="<%=planVO.getPlanNo()%>">
                    </form>
                    <div class="myplanlist_item_title_wrap">
                        <div class="myplanlist_item_title"><%=planVO.getPlanTitle()%></div>
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
                        <% String tripTerm = planVO.getTripTerm() == 0 ? "당일치기" : planVO.getTripTerm() + "박 " + (planVO.getTripTerm() + 1) + "일"; %>
                        <div class="myplanlist_trip_term" id="trip_term"><%=tripTerm%>
                        </div>
                        <div class="myplanlist_trip_term">&nbsp;]</div>
                    </div>
                </div>
                <details class="flag">
                    <summary class="ellipsis"></summary>
                    <div class="report">
                        <p id="myplanlist_item_menu_edit" onclick="itemMenuEdit(<%=planVO.getPlanNo()%>)">플랜 수정하기</p>
                        <p id="myplanlist_item_menu_exit" onclick='itemMenuExit(<%=planVO.getPlanNo()%>, "<%=planVO.getLeadYn()%>")'>플랜 나가기</p>
                    </div>
                </details>
            </div>
            <% }
            } %>
        </div>
    </form>
</div>
</body>
</html>