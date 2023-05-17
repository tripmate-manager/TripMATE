<%@ page import="com.tripmate.domain.PostVO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tripmate.common.util.DateUtil" %>
<%@ page import="com.tripmate.domain.MemberDTO" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/include/commonImport.jsp" %>
<%@ include file="/WEB-INF/jsp/common/bottomNavigationMenu.jsp" %>

<html>
<head>
    <jsp:include page="/WEB-INF/jsp/common/include/header.jsp">
        <jsp:param name="title" value="WishList"/>
    </jsp:include>
    <jsp:include page="/WEB-INF/jsp/common/messagePopUp.jsp"/>
    <jsp:include page="/WEB-INF/jsp/common/checkPopUp.jsp"/>
    <jsp:include page="/WEB-INF/jsp/dailyplans/addDailyPlanPopUp.jsp"/>
    <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/wishlist/wishList.css"/>
    <script src="<%=Const.STATIC_JS_PATH%>/wishlist/wishList.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/common/popUp.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/common/checkPopUp.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/dailyplans/addDailyPlanPopUp.js"></script>
</head>
<body>
<%
    MemberDTO memberInfo = null;
    session = request.getSession();

    if (session != null) {
        memberInfo = (MemberDTO) session.getAttribute(Const.MEMBER_INFO_SESSION);
    }

    String planNo = (String) request.getAttribute("planNo");
    String tripStartDate = (String) request.getAttribute("tripStartDate");
    String tripTerm = (String) request.getAttribute("tripTerm");
    List<PostVO> wishList = (List<PostVO>) request.getAttribute("wishList");
%>

<div class="wishlist_wrap">
    <img class="icon_arrow_left" id="icon_arrow_left" src="<%=Const.STATIC_IMG_PATH%>/common/icon_arrow_left.png"/>
    <div class="wishlist_title_wrap">
        <div class="wishlist_title">WishList</div>
        <img class="icon_write" src="<%=Const.STATIC_IMG_PATH%>/common/icon_menu_wishlist_choice.png"/>
    </div>
    <div class="wishlist_divi_line"></div>

    <% if (wishList != null) { %>
    <form name="wishListForm" id="wishListForm" method="post">
        <% if (memberInfo != null) { %>
        <input type=hidden id="member_no" name="memberNo" value="<%=memberInfo.getMemberNo()%>" hidden>
        <% } %>
        <% if (planNo != null && tripStartDate != null && tripTerm != null) { %>
        <input type="hidden" name="planNo" id="wishlist_plan_no" value="<%=planNo%>">
        <input type="hidden" name="tripStartDate" id="wishlist_trip_start_date" value="<%=tripStartDate%>">
        <input type="hidden" name="tripTerm" id="wishlist_trip_term" value="<%=tripTerm%>">
        <% } %>
        <input type="hidden" name="postNo" class="wishlist_post_no" id="wishlist_post_no">

        <% for (PostVO postVO : wishList) { %>
        <div class="wishlist_item_list_wrap">
            <div class="wishlist_item_wrap">
                <div id="wishlist_item_post_no" value="<%=postVO.getPostNo()%>" hidden></div>
                <div class="wishlist_item_title_wrap">
                    <div class="wishlist_post_title"><%=postVO.getPostTitle()%>
                    </div>
                    <div class="wishlist_post_type">
                        <% if (ConstCode.POST_TYPE_CODE_LODGING.equals(postVO.getPostTypeCode())) { %>숙소
                        <% } else if (ConstCode.POST_TYPE_CODE_TOUR.equals(postVO.getPostTypeCode())) { %>관광지
                        <% } else if (ConstCode.POST_TYPE_CODE_RESTAURANT.equals(postVO.getPostTypeCode())) { %>식당
                        <% } else { %>기타<% } %>
                    </div>
                </div>
                <div class="wishlist_post_contents"><%=postVO.getPostContents()%>
                </div>
                <div class="wishlist_item_info_wrap">
                    <% if (!ConstCode.POST_TYPE_CODE_ETC.equals(postVO.getPostTypeCode())) { %>
                    <img class="icon_location" src="<%=Const.STATIC_IMG_PATH%>/common/icon_location.png"/>
                    <div class="wishlist_post_address"><%=postVO.getSpotAddress()%>
                    </div>
                    <% } %>
                    <div class="wishlist_post_date"><%=DateUtil.dateTimeFormat(postVO.getRegistrationDateTime())%>
                    </div>
                    <img class="icon_comment" src="<%=Const.STATIC_IMG_PATH%>/wishlist/icon_comment.png"/>
                    <div class="wishlist_post_comment_count"><%=postVO.getCommentCnt()%>
                    </div>
                </div>
            </div>
            <% if (postVO.getMappingYn().equals(Const.Y)) { %>
                <img class="checkboxBookmark" id="dailyPlanBookmark<%=postVO.getPostNo()%>" onclick='bookMark("<%=postVO.getPostNo()%>")' src="<%=Const.STATIC_IMG_PATH%>/wishlist/icon_bookmark_filled.png"/>
            <% } else { %>
                <img class="checkboxBookmark" id="dailyPlanBookmark<%=postVO.getPostNo()%>" onclick='bookMark("<%=postVO.getPostNo()%>")' src="<%=Const.STATIC_IMG_PATH%>/wishlist/icon_bookmark_unfilled.png"/>
            <% } %>
        </div>
        <% } %>
    </form>
    <% } %>
</div>
</body>