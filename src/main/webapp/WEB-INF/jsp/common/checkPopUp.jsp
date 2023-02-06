<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/include/commonImport.jsp" %>

<html>
<head>
    <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/common/checkPopUp.css"/>
</head>
<body>
<div class="check_popup_wrap" style="display: none">
    <div class="check_popup_message"></div>
    <form name="checkPopUpForm" id="checkPopUpForm" method="post">
        <div class="check_popup_btn">
            <div class="check_popup_btn_cancel" onclick="popUpCancel()">취소</div>
            <div class="check_popup_btn_ok" onclick="popUpOk()">확인</div>
        </div>
    </form>
</div>
</body>
</html>
