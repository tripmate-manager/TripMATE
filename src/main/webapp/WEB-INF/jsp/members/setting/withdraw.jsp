<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include/commonImport.jsp" %>

<html>
<head>
    <jsp:include page="/WEB-INF/jsp/common/include/header.jsp">
        <jsp:param name="title" value="Withdraw"/>
    </jsp:include>
    <jsp:include page="/WEB-INF/jsp/common/messagePopUp.jsp"/>
    <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/members/setting/withdraw.css"/>
    <script src="<%=Const.STATIC_JS_PATH%>/members/setting/withdraw.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/common/popUp.js"></script>
</head>

<body>
<div class="wirhdraw_wrap">
    <img class="arrow_left_icon" src="<%=Const.STATIC_IMG_PATH%>/common/icon_arrow_left.png" onclick="history.back()"/>
    <div class="withdraw_title">회원 탈퇴</div>
    <div class="withdraw_subtitle_password">비밀번호</div>
    <input type="password" name="memberPassword" class="withdraw_input_password">
    <div class="withdraw_subtitle_check_password">비밀번호 확인</div>
    <input type="password" name="memberPassword" class="withdraw_input_check_password">
    <div class="withdraw_contents_title_wrap">
        <div class="withdraw_icon_wrap">
            <img class="withdraw_icon_1" src="<%=Const.STATIC_IMG_PATH%>/members/icon_emphasis_1.png" />
            <img class="withdraw_icon_2" src="<%=Const.STATIC_IMG_PATH%>/members/icon_emphasis_2.png" />
        </div>
        <div class="withdraw_contents_title">TripMATE 탈퇴 전 확인하세요.</div>
    </div>
    <div class="withdraw_contents_wrap">
        <div class="withdraw_contents">
            플랜메이트로 가입된 플랜에서 퇴장 처리 되며,

        </div>
        <div class="withdraw_contents-sub-1">
            사용자가 생성한 플랜은 수정할 수 없습니다.
        </div>
        <div class="withdraw_contents">
            <br />
            타인의 글에 대한 댓글은 삭제되지 않습니다.
            <br />
            <br />
            탈퇴 시 이메일 정보는 일주일간 저장됩니다.
        </div>
    </div>
    <div class="withdraw_btn">탈퇴하기</div>
</div>
</body>
</html>
