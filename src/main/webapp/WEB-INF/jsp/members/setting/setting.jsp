<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include/commonImport.jsp" %>

<html>
<head>
    <jsp:include page="/WEB-INF/jsp/common/include/header.jsp">
        <jsp:param name="title" value="Setting"/>
    </jsp:include>
    <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/members/setting/setting.css"/>
    <script src="<%=Const.STATIC_JS_PATH%>/members/setting/setting.js"></script>
</head>
<body>
<div class="setting_wrap">
    <div class="setting_title_wrap">
        <img class="arrow_left_icon" src="<%=Const.STATIC_IMG_PATH%>/common/icon_arrow_left.png" onclick="history.back()"/>
        <div class="setting_title">설정</div>
    </div>
    <div class="setting_divi_line"></div>
    <div class="setting_menu_withdraw">탈퇴하기</div>
    <div class="setting_divi_line"></div>
</div>
</body>
</html>
