<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/include/commonImport.jsp" %>

<html>
<head>
    <jsp:include page="/WEB-INF/jsp/common/include/header.jsp">
        <jsp:param name="title" value="ChangeEmail"/>
    </jsp:include>
    <jsp:include page="/WEB-INF/jsp/common/messagePopUp.jsp"/>
    <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/members/changeEmail.css"/>
    <script src="<%=Const.STATIC_JS_PATH%>/members/changeEmail.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/members/validationCheck.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/common/popUp.js"></script>
</head>

<body>
<input type=hidden id="memberId" value=${signInInfo.memberId}>
<input type=hidden id="email" value=${signInInfo.email}>
<input type=hidden id="EMAIL_TYPE_CODE_EMAIL_CHANGE" value="<%=ConstCode.EMAIL_TYPE_CODE_EMAIL_CHANGE%>">

<div class="change_email_wrap">
    <div class="change_email_title_wrap">
        <img class="icon_arrow_left" src="<%=Const.STATIC_IMG_PATH%>/common/icon_arrow_left.png" onclick="history.back()"/>
        <div class="change_email_title">이메일 변경</div>
    </div>
    <div class="change_email_divi_line">
    </div>
    <div class="change_email_input_wrap">
        <div class="change_email_input_form">
            <div class="change_email_input">
                <div class="change_email_sub_title">이메일</div>
                <input type="text" name="memberEmail" id="memberEmail" class="memberEmail"
                       placeholder="example@email.com">
            </div>
            <div class="change_email_duplicate">중복확인</div>
        </div>
        <div class="change_email_valid_message" id="checkEmailMessage" style="display:none;">잘못된 이메일 형식입니다.</div>
        <div class="change_email_form_message">이메일 변경을 위해서는 이메일 인증이 필요합니다.</div>
    </div>
    <div class="change_email_complete">이메일 변경</div>
</div>
</body>
</html>
