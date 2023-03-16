<%@ page import="com.tripmate.domain.MemberDTO" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/include/commonImport.jsp" %>

<html>
<head>
    <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/plans/inviteMatePopUp.css"/>
    <script src="<%=Const.STATIC_JS_PATH%>/plans/inviteMatePopUp.js"></script>
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
<input type=hidden id="memberNo" value=<%=memberInfo.getMemberNo()%>>
<% } %>
<input type=hidden id="inviteCodeNo">

<div class="invite_mate_popup_wrap" style="display:none">
    <form name="inviteMatePopUpForm" id="inviteMatePopUpForm" method="post">
        <input type="hidden" name="planNo" id="planNo">
    </form>
    <div class="invite_planmate_tab_wrap">
        <div class="invite_planmate_tab_menu">
            <div id="tab_menu_member" class="tab_select">회원</div>
            <div id="tab_menu_nonmember">비회원</div>
        </div>
        <div class="invite_planmate_member_wrap">
            <div class="invite_member_contents_wrap">
                <select id="search_member_selectbox" name="search_member_selectbox" class="search_member_selectbox">
                    <option id="selected" value="id" selected>아이디</option>
                    <option id="option_nickname" value="nickname">닉네임</option>
                    <option id="option_email" value="email">이메일</option>
                </select>
                <input type="text" name="search_keyword" class="input_search_keyword">
                <img class="icon_search" src="<%=Const.STATIC_IMG_PATH%>/common/icon_search.png"/>
            </div>
            <div class="search_member_result_wrap"></div>
        </div>
        <div class="invite_planmate_nonmember_wrap" style="display:none">
            <div class="invite_nonmember_guide_text">플랜 가입 시 인증 코드가 필요합니다.</div>
            <div class="invite_nonmember_contents_wrap">
                <div class="invite_nonmember_auth_code_wrap">
                    <div class="invite_nonmember_auth_code_text_wrap">
                        <div class="invite_nonmember_auth_code_text">인증 코드</div>
                        <img class="icon_copy" src="<%=Const.STATIC_IMG_PATH%>/plans/icon_copy.png"/>
                    </div>
                    <div class="invite_nonmember_auth_code"></div>
                </div>
                <div class="invite_nonmember_menu_text">초대 방법을 선택해주세요</div>
                <div class="invite_nonmember_menu_url">초대 URL 복사하기</div>
                <div class="invite_nonmember_menu_kakao">카카오톡으로초대하기</div>
            </div>
        </div>
    </div>
    <div class="invite_complete" onclick="inviteMatePopUpClose()">완료</div>
</div>
</body>
</html>
