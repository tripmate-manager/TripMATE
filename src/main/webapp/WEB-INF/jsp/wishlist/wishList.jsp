<%@ page import="com.tripmate.domain.PostVO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.tripmate.common.util.DateUtil" %>
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
    <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/wishlist/wishList.css"/>
    <script src="<%=Const.STATIC_JS_PATH%>/wishlist/wishList.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/common/popUp.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/common/checkPopUp.js"></script>
</head>
<body>
<%
    String planNo = (String) request.getAttribute("planNo");
    List<PostVO> wishList = (List<PostVO>) request.getAttribute("wishList");
%>

<div class="wishlist_wrap">
    <form name="wishListForm" id="wishListForm" method="post">
        <% if (planNo != null) { %>
        <input type="hidden" name="planNo" value="<%=planNo%>">
        <% } %>
        <img class="icon_arrow_left" src="<%=Const.STATIC_IMG_PATH%>/common/icon_arrow_left.png" onclick="history.back()"/>
        <div class="wishlist_title_wrap">
            <div class="wishlist_title">WishList</div>
            <img class="icon_write" src="<%=Const.STATIC_IMG_PATH%>/common/icon_menu_wishlist_choice.png"/>
        </div>
        <div class="wishlist_divi_line"></div>

        <div class="wishlist_item_list_wrap">
            <% if (wishList != null) {
                for (PostVO postVO : wishList) { %>
            <div class="wishlist_item_wrap">
                <div class="wishlist_item_title_wrap">
                    <div class="wishlist_post_title"><%=postVO.getPostTitle()%>
                    </div>
                    <div class="wishlist_post_type">
                        <% if (ConstCode.POST_TYPE_CODE_LODGING.equals(postVO.getPostTypeCode())) { %>숙소
                        <% } else if (ConstCode.POST_TYPE_CODE_TOUR.equals(postVO.getPostTypeCode())) { %>관광지
                        <% } else if (ConstCode.POST_TYPE_CODE_RESTAURANT.equals(postVO.getPostTypeCode())) { %>식당
                        <% } else { %>기타<% } %>
                    </div>
                    <input type="checkbox" name="bookmarkYn" id="checkboxBookmark" class="checkboxBookmark"
                        <% if (postVO.getMappingYn().equals(Const.Y)) { %> checked <% } %>>
                    <label for="checkboxBookmark"></label>
                </div>
                <div class="wishlist_post_contents"><%=postVO.getPostContents()%></div>
                <div class="wishlist_item_info_wrap">
                    <% if (!ConstCode.POST_TYPE_CODE_ETC.equals(postVO.getPostTypeCode())) { %>
                    <img class="icon_location" src="<%=Const.STATIC_IMG_PATH%>/common/icon_location.png"/>
                    <div class="wishlist_post_address"><%=postVO.getSpotAddress()%></div>
                    <% } %>
                    <div class="wishlist_post_date"><%=DateUtil.dateTimeFormat(postVO.getRegistrationDateTime())%></div>
                    <img class="icon_comment" src="<%=Const.STATIC_IMG_PATH%>/wishlist/icon_comment.png"/>
                    <div class="wishlist_post_comment_count"><%=postVO.getCommentCount()%></div>
                </div>
            </div>
            <% }
            } %>
        </div>
    </form>
</div>
</body>