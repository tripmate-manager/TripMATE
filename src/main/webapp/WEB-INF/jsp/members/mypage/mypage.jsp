<%@ page import="com.tripmate.domain.MemberDTO" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include/commonImport.jsp" %>

<html>
<head>
    <jsp:include page="/WEB-INF/jsp/common/include/header.jsp">
        <jsp:param name="title" value="Mypage"/>
    </jsp:include>
    <jsp:include page="/WEB-INF/jsp/common/messagePopUp.jsp"/>
    <jsp:include page="/WEB-INF/jsp/common/checkPopUp.jsp"/>
    <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/members/mypage.css"/>
    <script src="<%=Const.STATIC_JS_PATH%>/members/validationCheck.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/members/mypage.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/common/popUp.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/common/checkPopUp.js"></script>
</head>
<body>
<%
    MemberDTO memberInfo = null;
    session = request.getSession();
    if (session != null) {
        memberInfo = (MemberDTO) session.getAttribute(Const.MEMBER_INFO_SESSION);
    }
%>

<% if (memberInfo != null) { %>
<input type=hidden id="memberInfo" value=<%=memberInfo%>>
<input type=hidden id="memberId" value=<%=memberInfo.getMemberId()%>>
<input type=hidden id="memberNickName" value=<%=memberInfo.getNickName()%>>
<% } %>

<div class="mypage_wrap">
    <div class="mypage_form_wrap">
        <div class="connect_kakao">
            <img class="kakao_circle_icon" src="<%=Const.STATIC_IMG_PATH%>/members/icon_kakao_circle.png"/>
            <div class="connect_kakao_title">카카오 연동하기</div>
            <img class="arrow_right_icon" src="<%=Const.STATIC_IMG_PATH%>/common/icon_arrow_right.png"/>
        </div>
        <div class="connect_naver">
            <img class="naver_circle_icon" src="<%=Const.STATIC_IMG_PATH%>/members/icon_naver_circle.png"/>
            <div class="connect_naver_title">네이버 연동하기</div>
            <img class="arrow_right_icon_icon" src="<%=Const.STATIC_IMG_PATH%>/common/icon_arrow_right.png"/>
        </div>
        <div class="mypage_subtitle_name">이름</div>
        <div class="mypage_name"><%=memberInfo.getMemberName()%></div>
        <div class="mypage_subtitle_nick_name">닉네임</div>
        <div class="mypage_change_nick_name_wrap">
            <input type="text" name="nickName" id="nickName" class="mypage_input_nick_name" value=<%=memberInfo.getNickName()%>>
            <div class="mypage_nick_name_duplicate">중복확인</div>
        </div>
        <div class="mypage_email_wrap">
            <div class="mypage_change_email">
                <div class="mypage_subtitle_email">이메일</div>
                <div class="mypage_email"><%=memberInfo.getEmail()%></div>
            </div>
            <div class="mypage_change_email_btn">변경</div>
        </div>
        <div class="mypage_subtitle_birthday">생년월일</div>
        <input type="text" name="birthDay" id="birthDay" class="mypage_birthday" value=<%=memberInfo.getBirthDay()%>>
        <div class="mypage_gender">
            <div class="mypage_subtitle_gender">성별</div>
            <input type="text" name="memberGenderCode" id="memberGenderCode" value="<%=memberInfo.getGenderCode()%>" hidden>
            <div class="btn-toggle">
                <div class="btn-default" type="button">남</div>
                <div class="btn-primary" type="button">여</div>
            </div>
        </div>

        <div class="mypage_change_password_wrap">
            <div class="mypage_change_password">비밀번호 변경하기</div>
            <img class="arrow_right_icon" src="<%=Const.STATIC_IMG_PATH%>/common/icon_arrow_right.png"/>
        </div>
    </div>
    <div class="mypage_subtitle_id">아이디</div>
    <div class="mypage_title_wrap">
        <img class="arrow_left_icon" src="<%=Const.STATIC_IMG_PATH%>/common/icon_arrow_left.png" onclick="history.back()"/>
        <div class="mypage_title">회원 정보 수정</div>
    </div>
    <div class="mypage_divi_line">
    </div>
    <div class="mypage_id"><%=memberInfo.getMemberId()%></div>
    <div class="mypage_subtitle_connect_sns">SNS 아이디</div>
    <div class="mypage_edit_save">저장</div>
</div>
</body>
</html>