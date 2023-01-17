<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/include/commonImport.jsp" %>

<html>
<head>
    <jsp:include page="../common/include/header.jsp">
        <jsp:param name="title" value="FindId"/>
    </jsp:include>
    <jsp:include page="/WEB-INF/jsp/common/messagePopUp.jsp"/>
    <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/members/findPassword.css"/>
    <script src="<%=Const.STATIC_JS_PATH%>/members/validationCheck.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/common/popUp.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/members/findPassword.js"></script>
</head>
<body>
<div class="find_password__wrap">
    <div class="find_password_title">비밀번호 찾기</div>
    <div class="find_password_rectangle">
        <div class="find_password_input">
            <input type="text" name="memberName" id="memberName" class="memberName">
        </div>
    </div>
    <div class="find_password_name_title">아이디</div>
    <div class="find_password_email_title">이메일</div>
    <div class="find_password_rectangle_2">
        <div class="find_password_input">
            <input type="text" name="email" id="email" class="email" placeholder="example@email.com">
        </div>
        <div class="find_password_button">임시 비밀번호 전송</div>
        <div class="email_error_message" style="display: none;">잘못된 이메일 형식입니다.</div>
    </div>
</body>
</html>
