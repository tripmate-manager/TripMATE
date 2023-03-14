<%@ page import="com.tripmate.domain.MemberDTO" %>
<%@ page import="com.tripmate.domain.InviteCodeVO" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/include/commonImport.jsp" %>

<html>
<head>
    <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/plans/planInviteCodePopUp.css"/>
</head>
<body>
<%
    MemberDTO memberInfo = null;
    session = request.getSession();
    InviteCodeVO inviteCodeVO = null;

    if (session != null) {
        memberInfo = (MemberDTO) session.getAttribute(Const.MEMBER_INFO_SESSION);
    }
    if (request.getAttribute("inviteCodeInfo") != null) {
        inviteCodeVO = (InviteCodeVO) request.getAttribute("inviteCodeInfo");
    }
%>

<% if (inviteCodeVO != null) { %>
<input type=hidden id="inviteCode" value=<%=inviteCodeVO.getInviteCode()%>>
<input type=hidden id="inviteCodeExpireDateTime" value=<%=inviteCodeVO.getInviteCodeExpireDateTime()%>>
<% } %>

<input type=hidden id="memberNo" value=<%=memberInfo.getMemberNo()%>>
<input type=hidden id="memberNo">
<div class="invitecode_popup_wrap" style="display: none">
    <div class="invitecode_popup_message">초대를 수락하시려면 인증 코드를 입력해 주세요.</div>
    <form name="inviteCodePopUpForm" id="inviteCodePopUpForm" method="post">
        <% if (inviteCodeVO != null) { %>
        <input type=hidden id="planNo" name="planNo" value=<%=inviteCodeVO.getPlanNo()%>>
        <% } %>
        <input type="text" name="inviteCode" id="inputInviteCode" class="inviteCode">
    </form>
    <div class="invitecode_popup_btn">
        <div class="invitecode_popup_btn_cancel" onclick="inviteCodePopUpCancel()">취소</div>
        <div class="invitecode_popup_btn_ok" onclick="inviteCodePopUpOk()">확인</div>
    </div>
</div>
</body>
</html>