<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/include/commonImport.jsp" %>

<html>
<head>
    <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/main/mainNavigationMenu.css"/>
    <jsp:include page="/WEB-INF/jsp/common/messagePopUp.jsp"/>
</head>
<body>
<div class="mainnavimenulogout-uTR" id="menu">
    <div class="auto-group-oeqh-2Y3">
        <div class="item--A8T">로그인하세요</div>
        <img class="ic-sharp-arrow-back-ios-new-fqu" src="<%=Const.STATIC_IMG_PATH%>/common/icon_arrow_right.png"/>
    </div>
    <div class="line-3-bDm">
    </div>
    <div class="my-plan-7xo">My Plan</div>
    <div class="item--qdu">내가 찜한 플랜</div>

    <div onclick="history.back();" class="close"></div>
</div>
</body>
</html>