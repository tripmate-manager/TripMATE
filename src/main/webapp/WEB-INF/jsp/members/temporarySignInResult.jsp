<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/include/commonImport.jsp" %>

<html>
<head>
    <jsp:include page="/WEB-INF/jsp/common/include/header.jsp">
        <jsp:param name="title" value="TemporarySignIninResult"/>
    </jsp:include>
    <jsp:include page="/WEB-INF/jsp/common/messagePopUp.jsp"/>
    <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/members/temporarySignInResult.css"/>
    <script src="<%=Const.STATIC_JS_PATH%>/members/temporarySignInResult.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/common/popUp.js"></script>
</head>

<body>
<form name="temporaryForm" id="temporaryForm" method="post">
    <input type=hidden id="memberId" name="memberId" value=${signInInfo.memberId}>
    <input type=hidden id="memberPassword" name="memberPassword" value=${signInInfo.memberPassword}>
    <input type=hidden id="email" name="email" value=${signInInfo.email}>
    <input type=hidden id="EMAIL_TYPE_CODE_JOIN" value="<%=ConstCode.EMAIL_TYPE_CODE_JOIN%>">
</form>

<div class="temporary_wrap">
    <img class="icon_arrow_left" src="<%=Const.STATIC_IMG_PATH%>/common/icon_arrow_left.png" onclick="history.back()"/>
    <div class="temporary_title">임시 회원</div>
    <div class="temporary_contents">
        <div class="temporary_icon_wrap">
            <img class="temporary_icon1" src="<%=Const.STATIC_IMG_PATH%>/members/icon_emphasis_1.png"/>
            <img class="temporary_icon2" src="<%=Const.STATIC_IMG_PATH%>/members/icon_emphasis_2.png"/>
        </div>
        <div class="temporary_message">서비스를 이용할 수 없습니다.</div>
        <div class="temporary_message_2">
            인증 메일 확인 전엔 서비스 이용이 제한됩니다.
            <br/>
            <br/>
            메일함에서 인증 메일 확인 후 다시 로그인해주세요.
        </div>
    </div>
    <div class="temporary_email_resend_button">인증메일 재전송</div>
    <div class="temporary_email_change_button">이메일 변경</div>
</div>
</body>
</html>
