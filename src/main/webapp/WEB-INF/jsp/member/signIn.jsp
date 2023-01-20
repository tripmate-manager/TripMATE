<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/include/commonImport.jsp" %>

<html>
<head>
    <jsp:include page="../common/include/header.jsp">
        <jsp:param name="title" value="SignIn"/>
    </jsp:include>
    <jsp:include page="/WEB-INF/jsp/common/messagePopUp.jsp"/>
    <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/member/signIn.css"/>
    <script src="<%=Const.STATIC_JS_PATH%>/member/signIn.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/member/validationCheck.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/common/popUp.js"></script>
</head>
<body>
<input type=hidden id="signInResult" value=${signInResult}>

<div class="signin__wrap">
    <div class="signin_title">로그인</div>
    <form name="signinForm" id="signinForm" method="post">
        <div class="signin__form">
            <div class="signin__form_input">
                <input type="text" name="memberId" id="memberId" class="memberId" placeholder="아이디">
            </div>
            <div class="signin__form_input">
                <input type="password" name="memberPassword" id="memberPassword" class="memberPassword" placeholder="비밀번호">
            </div>
            <div class="signin_btn">로그인</div>
        </div>
    </form>
    <div class="signin__menu">
        <div class="signin__menu_find" id="find_id">아이디 찾기</div>
        <div class="signin__menu_line">
        </div>
        <div class="signin__menu_find" id="find_password">비밀번호 찾기</div>
        <div class="signin__menu_line">
        </div>
        <div class="signin__menu_join">회원가입</div>
    </div>
    <div class="signin__divi_line">
    </div>
    <div class="signin__social">
        <img class="signin_naver_logo" src="<%=Const.STATIC_IMG_PATH%>/member/icon_naverlogin.png"/>
        <div class="signin_naver">네이버 로그인</div>
    </div>
    <img class="signin_kakao" src="<%=Const.STATIC_IMG_PATH%>/member/icon_kakaologin_wide.png"/>
</div>
</body>
</html>
