<%@ page import="com.tripmate.domain.MemberDTO" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<html>
<head>
    <jsp:include page="/WEB-INF/jsp/common/include/header.jsp">
        <jsp:param name="title" value="Main"/>
    </jsp:include>
    <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/main/mainNavigationMenu.css"/>
    <script src="<%=Const.STATIC_JS_PATH%>/main/mainNavigationMenu.js"></script>
</head>
<body>
<%
    MemberDTO memberInfo = null;
    session = request.getSession();
    if (session != null) {
        memberInfo = (MemberDTO) session.getAttribute(Const.MEMBER_INFO_SESSION);
    }
%>

<% if (memberInfo != null) { %>
    <input type=hidden id="memberInfo" value=<%=memberInfo%>>
    <input type=hidden id="memberId" value=<%=memberInfo.getMemberId()%>>
    <input type=hidden id="memberNickName" value=<%=memberInfo.getNickName()%>>
<% } %>

<div class="main_menu_wrap" id="menu">
    <div class="main_menu_login_info_wrap">
        <div class="main_menu_login_text">로그인하세요 ></div>
        <div class="main_menu_login_info" style="display: none">
            <div class="main_menu_login_id"></div>
            <div class="main_menu_login_nick_name"></div>
        </div>
    </div>
    <div class="main_menu_divi_line">
    </div>
    <ul class="main_menu_item_default">
        <li class="main_menu_item" id="menu_my_plan">My Plan</li>
        <li class="main_menu_item" id="menu_my_like_plan">내가 찜한 플랜</li>
    </ul>

    <ul class="main_menu_item_login" style="display: none">
        <li class="main_menu_item" id="menu_edit_member_info">회원 정보 수정</li>
        <li class="main_menu_item" id="menu_logout">로그아웃</li>
        <li class="main_menu_item" id="menu_setting">설정</li>
    </ul>

    <div onclick="history.back();" class="close"></div>
</div>
</body>
</html>