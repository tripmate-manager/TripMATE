<%@ page import="com.tripmate.domain.MemberDTO" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/include/commonImport.jsp" %>

<html>
<head>
  <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/plans/planAuthorityPopUp.css"/>
</head>
<body>
<%
  MemberDTO memberInfo = null;
  session = request.getSession();
  if (session != null) {
    memberInfo = (MemberDTO) session.getAttribute(Const.MEMBER_INFO_SESSION);
  }
%>

<input type=hidden id="planNo">
<input type=hidden id="memberNo" value=<%=memberInfo.getMemberNo()%>>
<input type=hidden id="memberNo">
<div class="authority_popup_wrap" style="display: none">
  <div class="authority_popup_message">생성자 권한을 위임할 메이트를 선택해 주세요.</div>
  <form name="authorityPopUpForm" id="authorityPopUpForm" method="post">
    <div class="select_wrap">
      <label class="label_selectbox_title">MATE</label>
      <select name="select_option_mate" class="select_option" id="select_option_mate">
        <option value="example1">example1</option>
      </select>
    </div>

    <div class="authority_popup_btn">
      <div class="authority_popup_btn_cancel" onclick="popUpCancel()">취소</div>
      <div class="authority_popup_btn_ok" onclick="popUpOk()">확인</div>
    </div>
  </form>
</div>
</body>
</html>