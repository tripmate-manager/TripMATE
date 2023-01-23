<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/include/commonImport.jsp" %>

<html>
<head>
    <jsp:include page="../common/include/header.jsp">
        <jsp:param name="title" value="SignIn"/>
    </jsp:include>
    <jsp:include page="/WEB-INF/jsp/common/messagePopUp.jsp"/>
    <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/members/signIn.css"/>
    <script src="<%=Const.STATIC_JS_PATH%>/members/signIn.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/members/validationCheck.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/common/popUp.js"></script>
</head>
<body>
<div class="signin_wrap">
    <div class="signin_title">로그인</div>
    <form name="signinForm" id="signinForm" method="post">
        <div class="signin_form">
            <div class="signin_form_input">
                <input type="text" name="memberId" id="memberId" class="memberId" placeholder="아이디">
            </div>
            <div class="signin_form_input">
                <input type="password" name="memberPassword" id="memberPassword" class="memberPassword" placeholder="비밀번호">
            </div>
            <div class="signin_btn">로그인</div>
        </div>
    </form>
    <div class="signin_menu">
        <div class="signin_menu_find" id="find_id">아이디 찾기</div>
        <div class="signin_menu_line">
        </div>
        <div class="signin_menu_find" id="find_password">비밀번호 찾기</div>
        <div class="signin_menu_line">
        </div>
        <div class="signin_menu_join">회원가입</div>
    </div>
    <div class="signin_divi_line">
    </div>
    <div class="signin_social">
        <img class="signin_naver_logo" src="<%=Const.STATIC_IMG_PATH%>/members/icon_naverlogin.png"/>
        <div class="signin_naver">네이버 로그인</div>
    </div>
    <img class="signin_kakao" src="<%=Const.STATIC_IMG_PATH%>/members/icon_kakaologin_wide.png"/>
</div>
</body>
</html>
