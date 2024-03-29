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
<script src="<%=Const.STATIC_JS_PATH%>/common/common.js"></script>
<script>
    'use strict';
    let constCode = window.constCode || {};

    constCode.global = (function () {
        return {
            resultCodeSuccess: "<%= Const.RESULT_CODE_SUCCESS %>",
            resultCodeUnknown: "<%= Const.RESULT_CODE_UNKNOWN %>",

            memberStatusCodeComplete: "<%= ConstCode.MEMBER_STATUS_CODE_COMPLETE %>",
            memberStatusCodeTemporary: "<%= ConstCode.MEMBER_STATUS_CODE_TEMPORARY %>",
            memberStatusCodeIssueTemporaryPassword: "<%= ConstCode.MEMBER_STATUS_CODE_ISSUE_TEMPORARY_PASSWORD %>",

            memberGenderCodeMale: "<%= ConstCode.GENDER_CODE_MALE %>",
            memberGenderCodeFemale: "<%= ConstCode.GENDER_CODE_FEMALE %>",

            memberSearchDiviCodeId:"<%=ConstCode.MEMBER_SEARCH_DIVI_CODE_ID%>",
            memberSearchDiviCodeNickName:"<%=ConstCode.MEMBER_SEARCH_DIVI_CODE_NICK_NAME%>",
            memberSearchDiviCodeEmail:"<%=ConstCode.MEMBER_SEARCH_DIVI_CODE_EMAIL%>",

            inviteTypeCodeMember: "<%=ConstCode.INVITE_TYPE_CODE_MEMBER%>",
            inviteTypeCodeNonMember: "<%=ConstCode.INVITE_TYPE_CODE_NONMEMBER%>",

            notificationTypeCodeTripSchedule: "<%=ConstCode.NOTIFICATION_TYPE_CODE_TRIP_SCHEDULE%>",
            notificationTypeCodeChangeLeader: "<%=ConstCode.NOTIFICATION_TYPE_CODE_CHANGE_LEADER%>",
            notificationTypeCodeInvitation: "<%=ConstCode.NOTIFICATION_TYPE_CODE_INVITATION%>",

            Y: "<%= Const.Y %>",
            N: "<%= Const.N %>",

            nonmemberInvitationUrl: "<%=Const.NONMEMBER_INVITATION_URL%>",

            postTypeCodeLodging: "<%=ConstCode.POST_TYPE_CODE_LODGING%>",
            postTypeCodeTour: "<%=ConstCode.POST_TYPE_CODE_TOUR%>",
            postTypeCodeRestaurant: "<%=ConstCode.POST_TYPE_CODE_RESTAURANT%>",
            postTypeCodeEtc: "<%=ConstCode.POST_TYPE_CODE_ETC%>",

            notification15Minutes: "<%=ConstCode.DAILYPLAN_NOTIFICATION_15M%>",
            notification30Minutes: "<%=ConstCode.DAILYPLAN_NOTIFICATION_30M%>",
            notification1Hours: "<%=ConstCode.DAILYPLAN_NOTIFICATION_1H%>",
            notification2Hours: "<%=ConstCode.DAILYPLAN_NOTIFICATION_2H%>",
            notification3Hours: "<%=ConstCode.DAILYPLAN_NOTIFICATION_3H%>",

            searchResultSortRecent: "<%=ConstCode.SEARCH_RESULT_SORT_RECENT%>",
            searchResultSortPopular: "<%=ConstCode.SEARCH_RESULT_SORT_POPULAR%>",
            searchResultSortOldest: "<%=ConstCode.SEARCH_RESULT_SORT_OLDEST%>",
            searchResultSortAchieveRate: "<%=ConstCode.SEARCH_RESULT_SORT_ACHIEVE_RATE%>",
            searchResultSortReview: "<%=ConstCode.SEARCH_RESULT_SORT_REVIEW%>",
        }
    }());
</script>