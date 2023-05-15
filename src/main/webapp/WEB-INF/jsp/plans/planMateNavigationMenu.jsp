<%@ page import="com.tripmate.domain.PlanMateVO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<html>
<head>
    <jsp:include page="/WEB-INF/jsp/common/include/header.jsp">
        <jsp:param name="title" value="planMain"/>
    </jsp:include>
    <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/plans/planMateNavigationMenu.css"/>
    <script src="<%=Const.STATIC_JS_PATH%>/plans/planMateNavigationMenu.js"></script>
</head>
<body>
<%
    List<PlanMateVO> planMateList = (List<PlanMateVO>) request.getAttribute("planMateList");
    boolean isPlanMate = (boolean) request.getAttribute("isPlanMate");
%>

<div class="planmate_menu_wrap" id="menu">
    <div class="planmate_menu_title_wrap">
        <div class="planmate_menu_title_text">플랜메이트</div>
        <div class="planmate_menu_count"><%=planMateList.size()%></div>
    </div>
    <div class="planmate_menu_divi_line">
    </div>
    <% if (isPlanMate) { %>
    <div class="planmate_menu_contents_wrap">
        <div class="planmate_menu_invite_wrap">
            <img class="icon_plus_circle" src="<%=Const.STATIC_IMG_PATH%>/plans/icon_plus_circle.png"/>
            <div class="planmate_menu_invite">초대하기</div>
        </div>
        <% for (PlanMateVO planMateVO : planMateList) { %>
        <div class="planmate_menu_mate_wrap">
            <div class="planmate_menu_nickname"><%=planMateVO.getNickName()%></div>
            <% if (Const.Y.equals(planMateVO.getLeadYn())) { %>
            <img class="icon_check_circle" src="<%=Const.STATIC_IMG_PATH%>/plans/icon_check_circle.png"/>
            <% } %>
            <div class="planmate_menu_name"><%=planMateVO.getMemberName()%></div>
        </div>
        <% } %>
    </div>
    <% } %>
    <div onclick="history.back();" class="close"></div>
</div>
</body>
</html>
