<%@ page import="com.tripmate.entity.ConstCode" %>
<%@ page import="com.tripmate.entity.Const" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<meta charset="utf-8"/>
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<meta name="theme-color" content="#000000"/>
<title>
    <%=request.getParameter("title")%>
</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noto+Sans%3A500%2C600"/>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro%3A500%2C600"/>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script>
    'use strict';
    let constCode = window.constcode || {};

    constCode.global = (function () {
        return {
            resultCodeSuccess: "<%= Const.RESULT_CODE_SUCCESS %>",
            resultCodeMessaging: "<%= Const.RESULT_CODE_MESSAGING %>",
            resultCodeValidation: "<%= Const.RESULT_CODE_VALIDATION %>",
            resultCodeWrongParameter: "<%= Const.RESULT_CODE_WRONG_PARAMETER %>",
            resultCodeUnknown: "<%= Const.RESULT_CODE_UNKNOWN %>",

            memberStatusCodeComplete: "<%= ConstCode.MEMBER_STATUS_CODE_COMPLETE %>",
            memberStatusCodeTemporary: "<%= ConstCode.MEMBER_STATUS_CODE_TEMPORARY %>",
        }
    }());
</script>