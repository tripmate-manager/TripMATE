<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta name="theme-color" content="#000000"/>
    <title>SignUpResult</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro%3A500%2C600"/>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noto+Sans%3A500%2C600"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/member/signUpResult.css"/>
</head>
<body>
<div class="signup_result__wrap">
    <div class="signup_result_title">회원가입</div>
    <div class="signup_result_message">
        <img class="signup_result_icon" src="/resources/images/icon-mail.png"/>
        <div class="signup_result_sub_message">인증 메일이 발송되었습니다</div>
        <div class="tripmategmailcom--zuR">
            메일함에서 (<%=Const.TRIP_MATE_MAIL_SENDER%>>) 인증 메일을 확인 바랍니다.
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
