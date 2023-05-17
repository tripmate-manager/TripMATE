<%@ page import="com.tripmate.domain.NotificationVO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tripmate.domain.MemberDTO" %>
<%@ page import="com.tripmate.common.util.DateUtil" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/include/commonImport.jsp" %>

<html>
<head>
    <jsp:include page="/WEB-INF/jsp/common/include/header.jsp">
        <jsp:param name="title" value="NotificationList"/>
    </jsp:include>
    <jsp:include page="/WEB-INF/jsp/common/messagePopUp.jsp"/>
    <jsp:include page="/WEB-INF/jsp/common/checkPopUp.jsp"/>
    <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/main/notificationList.css"/>
    <script src="<%=Const.STATIC_JS_PATH%>/main/notificationList.js"></script>
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
    List<NotificationVO> notificationList = (List<NotificationVO>) request.getAttribute("notificationList");
%>

<div class="alertlist_wrap">
    <div class="alertlist_title_wrap">
        <img class="icon_arrow_left" src="<%=Const.STATIC_IMG_PATH%>/common/icon_arrow_left.png"/>
        <div class="alertlist_title">알림</div>
    </div>
    <form name="notificationForm" id="notificationForm" method="post">
        <input type=hidden id="formPlanNo" name="planNo">
        <div class="alertlist_list_wrap">
            <% if (notificationList != null) { %>
            <input type=hidden id="inputPostNo" name="postNo">
            <% for (NotificationVO notification : notificationList) { %>
            <div class="alertlist_item_wrap" <% if (notification.getReadDateTime() == null) { %> id="unread_item" <% } %>>
                <input type=hidden id="notificationNo"name="notificationNo" value="<%=notification.getNotificationNo()%>">
                <input type=hidden id="notificationTypeCode" name="notificationTypeCode" value="<%=notification.getNotificationTypeCode()%>">
                <input type=hidden id="dailyPlanNo" name="dailyPlanNo" value="<%=notification.getDailyPlanNo()%>">
                <input type=hidden id="postNo" value="<%=notification.getPostNo()%>">
                <input type=hidden id="memberNo" name="memberNo" value="<%=memberInfo.getMemberNo()%>">
                <input type=hidden id="planNo" value="<%=notification.getPlanNo()%>">
                <input type=hidden id="useYn" value="<%=notification.getUseYn()%>">
                <div class="alertlist_list_item_time"><%=DateUtil.dateTimeFormat(notification.getNotificationDateTime())%>
                </div>

                <% if (ConstCode.NOTIFICATION_TYPE_CODE_TRIP_SCHEDULE.equals(notification.getNotificationTypeCode())) { %>
                <div class="alertlist_list_item_title" >[경주 여행] 17:00 대릉원 일정 알림</div>
                <% } else if (ConstCode.NOTIFICATION_TYPE_CODE_CHANGE_LEADER.equals(notification.getNotificationTypeCode())) { %>
                <div class="alertlist_list_item_title">‘<%=notification.getPlanTitle()%>’의
                    리더가 <%=notification.getLeaderName()%>님으로 변경되었습니다.
                </div>
                <% } else if (ConstCode.NOTIFICATION_TYPE_CODE_INVITATION.equals(notification.getNotificationTypeCode())) { %>
                <div class="alertlist_list_item_title"><%=notification.getSenderName()%>님이
                    ‘<%=notification.getPlanTitle()%>’ 플랜에 초대했습니다.
                </div>
                <% } %>
            </div>
            <% }
            }%>
        </div>
    </form>
</div>
</body>
</html>