<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/include/commonImport.jsp" %>

<html>
<head>
    <jsp:include page="../common/include/header.jsp">
        <jsp:param name="title" value="SignUpResult"/>
    </jsp:include>
    <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/member/signUpResult.css"/>
</head>
<body>
<div class="signup_result__wrap">
    <div class="signup_result_title">회원가입</div>
    <div class="signup_result_message">
        <img class="signup_result_icon" src="/resources/images/icon-mail.png"/>
        <div class="signup_result_sub_message">인증 메일이 발송되었습니다</div>
        <div class="tripmategmailcom--zuR">
            메일함에서 인증 메일을 확인 바랍니다.
            <br>
            인증 메일 확인 시 회원가입이 완료됩니다.
        </div>
    </div>
    <div class="signup_result_check_btn">확인</div>
</div>
</body>
<script>
    $(".signup_result_check_btn").on('click', function () {
    });
</script>
</html>
