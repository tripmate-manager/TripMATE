<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include/commonImport.jsp" %>

<html>
<head>
    <jsp:include page="../common/include/header.jsp">
        <jsp:param name="title" value="SignUp"/>
    </jsp:include>
    <jsp:include page="/WEB-INF/jsp/common/messagePopUp.jsp"/>
    <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/member/signUp.css"/>
    <script src="<%=Const.STATIC_JS_PATH%>/member/signUp.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/member/validationCheck.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/common/popUp.js"></script>
</head>

<body>
<input type=hidden id="GENDER_CODE_MALE" value="<%=ConstCode.GENDER_CODE_MALE%>">
<input type=hidden id="GENDER_CODE_FEMALE" value="<%=ConstCode.GENDER_CODE_FEMALE%>">

<div class="signup__wrap">
    <div class="signup_title">회원가입</div>
    <form name="signupForm" id="signupForm" method="post">
        <div class="signup_form">
            <div class="signup_form_item">
                <div class="signup_subtitle">아이디</div>
                <div class="signup_form_input">
                    <input type="text" name="memberId" id="memberId" class="memberId" placeholder="5-20자의 영문, 숫자 ">
                </div>
                <div class="signup_duplicate" id="signup_duplicate_id">중복확인</div>
            </div>
            <div class="signup_form_item">
                <div class="signup_subtitle">비밀번호</div>
                <div class="signup_form_input_2">
                    <input type="password" name="memberPassword" id="memberPassword" class="memberPassword"
                           placeholder="8-20자 이내 영문, 숫자, 기호 중 2가지 이상 조합">
                </div>
            </div>
            <div class="signup_form_item">
                <div class="signup_subtitle">비밀번호 확인</div>
                <div class="signup_form_input_2">
                    <input type="password" name="checkMemberPassword" id="checkMemberPassword"
                           class="checkMemberPassword">
                    <div class="signup_form_message" id="checkMemberPasswordMessage" style="display: none;">비밀번호 값이 일치하지
                        않습니다.
                    </div>
                </div>
            </div>
            <div class="signup_form_item">
                <div class="signup_subtitle">이름</div>
                <div class="signup_form_input_2">
                    <input type="text" name="memberName" id="memberName" class="memberName">
                </div>
            </div>
            <div class="signup_form_item">
                <div class="signup_subtitle">닉네임</div>
                <div class="signup_form_input">
                    <input type="text" name="nickName" id="nickName" class="nickName">
                </div>
                <div class="signup_duplicate" id="signup_duplicate_nick_name">중복확인</div>
            </div>
            <div class="signup_form_item">
                <div class="signup_subtitle">이메일</div>
                <div class="signup_form_input">
                    <input type="text" name="email" id="email" class="email"
                           placeholder="example@email.com">
                    <div class="signup_form_message" id="checkEmailMessage" style="display: none">잘못된 이메일 형식입니다.
                    </div>
                </div>
                <div class="signup_duplicate" id="signup_duplicate_email">중복확인</div>
            </div>
            <div class="signup_form_item">
                <div class="signup_subtitle">생년월일</div>
                <div class="signup_form_input_2">
                    <input type="number" name="birthDay" id="birthDay" class="birthDay" placeholder="YYYYMMDD">
                </div>
            </div>
            <div class="signup_form_gender">
                <div class="signup_subtitle">성별</div>
                <div class="btn-toggle">
                    <input type="text" name="genderCode" id="genderCode" value="<%=ConstCode.GENDER_CODE_MALE%>" hidden>
                    <button class="btn btn-default" type="button">남</button>
                    <button class="btn btn-primary active" type="button">여</button>
                </div>
            </div>
        </div>
        <div class="signup_complete">회원가입</div>
    </form>
</div>
</body>
</html>