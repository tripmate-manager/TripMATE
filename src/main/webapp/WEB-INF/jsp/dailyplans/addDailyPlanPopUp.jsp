<%@ page import="com.tripmate.domain.MemberDTO" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/include/commonImport.jsp" %>

<html>
<head>
    <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/dailyplans/addDailyPlanPopUp.css"/>
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
<div class="add_dailyplan_popup_wrap" id="add_dailyplan_popup_wrap" style="display: none">
    <div class="add_dailyplan_popup_message">추가할 날짜와 시간을 선택해주세요</div>
    <div class="select_wrap">
        <label class="label_selectbox_title">날짜</label>
        <select name="select_option_mate" class="select_option" id="select_option_day">
        </select>
    </div>
    <div class="select_wrap">
        <label class="label_selectbox_title">시간</label>
        <select name="select_option_mate" class="select_option" id="select_option_time">
        </select>
    </div>

    <div class="add_dailyplan_popup_btn">
        <div class="add_dailyplan_popup_ok" onclick="popUpOk()">확인</div>
        <div class="add_dailyplan_popup_cancel" onclick="popUpCancel()">취소</div>
    </div>
</div>
</body>
</html>