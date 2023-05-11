<%@ page import="java.util.List" %>
<%@ page import="com.tripmate.domain.MemberDTO" %>
<%@ page import="com.tripmate.domain.PlanBasicInfoVO" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/include/commonImport.jsp" %>

<html>
<head>
    <jsp:include page="/WEB-INF/jsp/common/include/header.jsp">
        <jsp:param name="title" value="MyPlanLike"/>
    </jsp:include>
    <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/plans/myPlanLike.css"/>
    <script src="<%=Const.STATIC_JS_PATH%>/plans/myPlanLike.js"></script>
</head>
<body>
<%
    List<PlanBasicInfoVO> myPlanLikeList = (List<PlanBasicInfoVO>) request.getAttribute("myPlanLikeList");
    MemberDTO memberInfo = null;
    session = request.getSession();
    if (session != null) {
        memberInfo = (MemberDTO) session.getAttribute(Const.MEMBER_INFO_SESSION);
    }
%>

<div class="myplanlike_wrap">
    <form id="myPlanLikeForm" method="post">
        <div class="myplanlike_title_wrap">
            <img class="icon_arrow_left" id="icon_arrow_left" src="<%=Const.STATIC_IMG_PATH%>/common/icon_arrow_left.png"/>
            <div class="myplanlike_title">찜한 플랜</div>
        </div>
        <div class="myplanlike_divi_line"></div>

        <% if (memberInfo != null) { %>
        <input type=hidden id="input_member_no" name="memberNo" value=<%=memberInfo.getMemberNo()%>>
        <% } %>
        <input type=hidden id="input_plan_no" name="planNo" hidden>

        <div class="myplanlike_list_wrap">
            <% if (myPlanLikeList != null) {
                for (PlanBasicInfoVO planLikeInfoVO : myPlanLikeList) { %>
            <input type=hidden id="planVO" value=<%=planLikeInfoVO%>>
            <div class="myplanlike_plan_item_wrap">
                <div class="myplanlike_item_contents_wrap">
                    <input type="hidden" class="myplanlike_item_plan_no" name="planNo" value="<%=planLikeInfoVO.getPlanNo()%>">
                    <div id="myplanlike_item_no" value="<%=planLikeInfoVO.getPlanNo()%>"></div>
                    <div class="myplanlike_item_title_wrap">
                        <div class="myplanlike_item_title"><%=planLikeInfoVO.getPlanTitle()%></div>
                    </div>
                    <div class="myplanlike_item_desc"><%=planLikeInfoVO.getPlanDescription()%></div>
                    <div class="myplanlike_item_address_wrap">
                        <img class="icon_location" src="<%=Const.STATIC_IMG_PATH%>/common/icon_location.png"/>
                        <div class="myplanlike_plan_location"><%=planLikeInfoVO.getSidoName()%> <%=planLikeInfoVO.getSigunguName()%></div>
                    </div>
                    <div class="myplanlike_trip_term_wrap">
                        <img class="icon_calendar" src="<%=Const.STATIC_IMG_PATH%>/common/icon_calendar.png"/>
                        <div class="myplanlike_trip_term">여행일자 [&nbsp;</div>
                        <% String tripTerm = planLikeInfoVO.getTripTerm() == 0 ? "당일치기" : planLikeInfoVO.getTripTerm() + "박 " + (planLikeInfoVO.getTripTerm() + 1) + "일"; %>
                        <div class="myplanlike_trip_term" id="trip_term"><%=tripTerm%>
                        </div>
                        <div class="myplanlike_trip_term">&nbsp;]</div>

                        <input type="checkbox" name="checkboxHeart" id="checkboxHeart<%=planLikeInfoVO.getPlanNo()%>" class="checkboxHeart"
                            <% if (planLikeInfoVO.getPlanLikeCnt() > 0) { %>checked<% } %>>
                        <label for="checkboxHeart<%=planLikeInfoVO.getPlanNo()%>" class="checkboxHeart"></label>
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