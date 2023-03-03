<%@ page import="com.tripmate.domain.PlanVO" %>
<%@ page import="com.tripmate.domain.PlanAddressVO" %>
<%@ page import="com.tripmate.domain.PlanAttributeVO" %>
<%@ page import="com.tripmate.domain.MemberDTO" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/include/commonImport.jsp" %>
<%@ include file="/WEB-INF/jsp/plans/planMateNavigationMenu.jsp" %>

<html>
<head>
    <jsp:include page="/WEB-INF/jsp/common/include/header.jsp">
        <jsp:param name="title" value="PlanMain"/>
    </jsp:include>
    <jsp:include page="/WEB-INF/jsp/common/messagePopUp.jsp"/>
    <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/plans/planMain.css"/>
    <script src="<%=Const.STATIC_JS_PATH%>/plans/planMain.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/common/popUp.js"></script>
</head>
<body>
<%
    MemberDTO memberInfo = null;
    session = request.getSession();
    if (session != null) {
        memberInfo = (MemberDTO) session.getAttribute(Const.MEMBER_INFO_SESSION);
    }
    PlanVO planVO = (PlanVO) request.getAttribute("planVO");
%>

<div class="plan_main_wrap">
    <form id="planMainForm">
        <input type="hidden" class="plan_main_plan_no" name="planNo" value="<%=planVO.getPlanNo()%>">
        <div onclick="history.back();" class="page_cover"></div>
        <div class="plan_main_title_wrap">
            <div class="plan_main_title">
                <img class="icon_arrow_left" src="<%=Const.STATIC_IMG_PATH%>/common/icon_arrow_left.png"/>
                <img class="icon_plan_mate" src="<%=Const.STATIC_IMG_PATH%>/plans/icon_people_navy.png"/>
            </div>
            <div class="plan_main_divi_line">
            </div>
            <div class="plan_main_info_wrap">
                <div class="plan_main_info_title_wrap">
                    <div class="plan_main_plan_title"><%=planVO.getPlanTitle()%>
                    </div>
                    <% for (PlanMateVO planMateVO : planMateList) {
                        boolean isLead = Const.Y.equals(planMateVO.getLeadYn());
                        boolean isCurrentMember = memberInfo != null && planMateVO.getMemberNo() == memberInfo.getMemberNo();

                        if (isLead && isCurrentMember) { %>
                            <div class="plan_main_plan_edit">편집</div>
                    <% }
                    } %>
                </div>
                <div class="plan_main_desc_wrap"><%=planVO.getPlanDescription()%>
                </div>
                <div class="plan_main_theme_wrap">
                    <% for (PlanAttributeVO planAttributeVO : planVO.getPlanAttributeList()) {
                        if (ConstCode.ATTRIBUTE_TYPE_CODE_TRIP_THEME.equals(planAttributeVO.getAttributeTypeCode())) { %>
                            <div class="plan_main_theme_item"><%=planAttributeVO.getAttributeName()%></div>
                    <% }
                    }%>
                </div>
                <div class="plan_main_hashtag_wrap">
                    <% for (PlanAttributeVO planAttributeVO : planVO.getPlanAttributeList()) {
                        if (ConstCode.ATTRIBUTE_TYPE_CODE_HASHTAG.equals(planAttributeVO.getAttributeTypeCode())) { %>
                            <div class="plan_main_hashtag_item">#<%=planAttributeVO.getAttributeName()%></div>
                    <% }
                    } %>
                </div>
                <div class="plan_main_address_wrap">
                    <img class="icon_location" src="<%=Const.STATIC_IMG_PATH%>/common/icon_location.png"/>
                    <% for (PlanAddressVO planAddressVO : planVO.getPlanAddressList()) { %>
                        <div class="plan_main_plan_address"><%=planAddressVO.getSidoName()%> <%=planAddressVO.getSigunguName()%></div>
                    <% } %>
                </div>
                <div class="plan_main_trip_term_wrap">
                    <img class="icon_calendar" src="<%=Const.STATIC_IMG_PATH%>/common/icon_calendar.png"/>
                    <% String tripTerm = planVO.getTripTerm() == 0 ? "당일치기" : planVO.getTripTerm() + "박 " + (planVO.getTripTerm() + 1) + "일"; %>
                    <div class="plan_main_trip_term"><%=planVO.getTripStartDate()%> ~ <%=planVO.getTripEndDate()%> [ <%=tripTerm%> ]</div>
                    <img class="icon_heart_filled" src="<%=Const.STATIC_IMG_PATH%>/common/icon_heart_filled.png"/>
                </div>
            </div>
        </div>

        <div class="dailyplan_list_title_wrap">
            <div class="dailyplan_daily_title">DailyPlan</div>
            <div class="dailyplan_achieve_percent">
                <img src="<%=Const.STATIC_IMG_PATH%>/plans/icon_percent_wrap.png"/>
                <div class="dailyplan_achieve_percent_number">10</div>
            </div>
        </div>
        <div class="plan_main_divi_line"></div>

        <div class="dailyplan_list_wrap">
            <% for (int i = 1; i <= planVO.getTripTerm() + 1; i++) { %>
            <div class="dailyplan_list_item_wrap">
                <div class="dailyplan_item_day">Day<%=i%>
                </div>
                <div class="dailyplan_item_qty">N</div>
                <img class="icon_arrow_right" src="<%=Const.STATIC_IMG_PATH%>/plans/icon_arrow_right_green.png"/>
            </div>
            <% } %>
        </div>

        <div class="bottom_navigation_wrap">
            <div class="bottom_navigation_home_wrap">
                <img class="icon_menu_home" src="<%=Const.STATIC_IMG_PATH%>/common/icon_menu_home_choice.png"/>
                <div class="bottom_navigation_home">home</div>
            </div>
            <div class="bottom_navigation_wishlist_wrap">
                <img class="icon_menu_wishlist" src="<%=Const.STATIC_IMG_PATH%>/common/icon_menu_wishlist.png"/>
                <div class="bottom_navigation_wishlist">wishList</div>
            </div>
            <div class="bottom_navigation_checklist_wrap">
                <img class="icon_menu_checklist" src="<%=Const.STATIC_IMG_PATH%>/common/icon_menu_checklist.png"/>
                <div class="bottom_navigation_checklist">checkList</div>
            </div>
            <div class="bottom_navigation_account_wrap">
                <img class="icon_menu_account" src="<%=Const.STATIC_IMG_PATH%>/common/icon_menu_account.png"/>
                <div class="bottom_navigation_account">account</div>
            </div>
        </div>
    </form>
</div>
</body>
</html>
