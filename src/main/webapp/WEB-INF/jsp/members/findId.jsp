<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/include/commonImport.jsp" %>

<html>
<head>
    <jsp:include page="../common/include/header.jsp">
        <jsp:param name="title" value="FindId"/>
    </jsp:include>
    <jsp:include page="/WEB-INF/jsp/common/messagePopUp.jsp"/>
    <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/members/findId.css"/>
    <script src="<%=Const.STATIC_JS_PATH%>/members/validationCheck.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/common/popUp.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/members/findId.js"></script>
</head>
<body>
<div class="find_id__wrap">
    <div class="find_id_title">아이디 찾기</div>
    <div class="find_id_rectangle">
        <div class="find_id_input">
            <input type="text" name="memberName" id="memberName" class="memberName">
        </div>
    </div>
    <div class="find_id_name_title">이름</div>
    <div class="find_id_email_title">이메일</div>
    <div class="find_id_rectangle_2">
        <div class="find_id_input">
            <input type="text" name="email" id="email" class="email" placeholder="example@email.com">
        </div>
        <div class="find_id_button">아이디 찾기</div>
        <div class="email_error_message" style="display: none;">잘못된 이메일 형식입니다.</div>
    </div>
</div>
</body>
</html>
