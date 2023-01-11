<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>

<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta name="theme-color" content="#000000" />
    <title>messagePopUp</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noto+Sans%3A500%2C600"/>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro%3A500%2C600"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common/messagePopUp.css"/>
</head>
<body>
<div class="popup__wrap" style="display: none">
    <div class="popup_message"></div>
    <div class="popup_close_btn" onclick="popUpClose()">확인</div>
</div>
</body>
</html>

