<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/include/commonImport.jsp" %>

<html>
<head>
    <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/common/messagePopUp.css"/>
</head>
<body>

<div class="popup__wrap" style="display: none">
    <div class="popup_message"></div>
    <div class="popup_close_btn" onclick="popUpClose()">확인</div>
</div>
</body>
</html>


