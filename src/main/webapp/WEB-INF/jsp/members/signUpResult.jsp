<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/include/commonImport.jsp" %>

<html>
<head>
    <jsp:include page="/WEB-INF/jsp/common/include/header.jsp">
        <jsp:param name="title" value="SignUpResult"/>
    </jsp:include>
    <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/members/signUpResult.css"/>
</head>
<body>
<div class="signup_result_wrap">
    <div class="signup_result_title">회원가입</div>
    <div class="signup_result_message">
        <img class="signup_result_icon" src="<%=Const.STATIC_IMG_PATH%>/members/icon_mail.png"/>
        <div class="signup_result_contents_title">인증 메일이 발송되었습니다</div>
        <div class="signup_result_contents">
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
        window.location.replace("/forward/members/signIn.trip");
    })
</script>
</html>
