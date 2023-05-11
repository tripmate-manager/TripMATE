<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/include/commonImport.jsp" %>

<html>
<head>
    <jsp:include page="/WEB-INF/jsp/common/include/header.jsp">
        <jsp:param name="title" value="ChangePassword"/>
    </jsp:include>
    <jsp:include page="/WEB-INF/jsp/common/messagePopUp.jsp"/>
    <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/members/changePassword.css"/>
    <script src="<%=Const.STATIC_JS_PATH%>/members/changePassword.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/members/validationCheck.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/common/popUp.js"></script>
</head>

<body>
<div class="change_password_wrap">
    <div class="change_password_title_wrap">
        <div class="change_password_title">비밀번호 변경</div>
    </div>
    <div class="change_password_divi_line">
    </div>
    <div class="change_password_sub_title_1">현재 비밀번호</div>
    <div class="input_change_password">
        <div class="input_present_password">
            <input type="password" name="presentMemberPassword" id="presentMemberPassword"
                   class="presentMemberPassword" autocomplete="off">
        </div>
        <div class="change_password_sub_title_2">새 비밀번호</div>
        <input type="password" name="newMemberPassword" id="newMemberPassword" class="newMemberPassword"
               placeholder="8-20자 이내 영문, 숫자, 기호 중 2가지 이상 조합" autocomplete="off">
        <div class="change_password_sub_title_3">새 비밀번호 확인</div>
        <div class="change_password_form_input_3">
            <input type="password" name="checkNewMemberPassword" id="checkNewMemberPassword"
                   class="checkNewMemberPassword" autocomplete="off">
        </div>
        <div class="change_password_message" id="checkMemberPasswordMessage" style="display:none;">비밀번호 값이 일치하지 않습니다.
        </div>
        <div class="change_password_complete" id="change_password_complete">비밀번호 변경</div>
        <div class="change_password_next" id="change_password_next">다음에 변경하기</div>
    </div>
</div>
</body>
</html>
