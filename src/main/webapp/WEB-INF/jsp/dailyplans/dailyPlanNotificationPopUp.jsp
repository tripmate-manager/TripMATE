<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/include/commonImport.jsp" %>

<html>
<head>
    <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/dailyplans/dailyPlanNotificationPopUp.css"/>
</head>
<body>
<div class="notification_popup_wrap" id="notification_popup_wrap" style="display: none">
    <div class="select_wrap">
        <label class="label_selectbox_title">알림시간</label>
        <select name="select_option_time" class="select_option" id="select_option_time">
            <option value="<%=ConstCode.DAILYPLAN_NOTIFICATION_15M%>">15분 전</option>
            <option value="<%=ConstCode.DAILYPLAN_NOTIFICATION_30M%>">30분 전</option>
            <option value="<%=ConstCode.DAILYPLAN_NOTIFICATION_1H%>">1시간 전</option>
            <option value="<%=ConstCode.DAILYPLAN_NOTIFICATION_2H%>">2시간 전</option>
            <option value="<%=ConstCode.DAILYPLAN_NOTIFICATION_30M%>">3시간 전</option>
        </select>
    </div>

    <div class="notification_popup_btn">
        <div class="notification_popup_cancel" onclick="popUpCancel()">취소</div>
        <div class="notification_popup_ok" onclick="popUpOk()">확인</div>
    </div>
</div>
</body>
</html>