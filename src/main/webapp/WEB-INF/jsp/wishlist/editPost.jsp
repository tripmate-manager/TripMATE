<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.tripmate.domain.MemberDTO" %>
<%@ page import="com.tripmate.domain.PostVO" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/include/commonImport.jsp" %>

<html>
<head>
    <jsp:include page="/WEB-INF/jsp/common/include/header.jsp">
        <jsp:param name="title" value="EditPost"/>
    </jsp:include>
    <jsp:include page="/WEB-INF/jsp/common/messagePopUp.jsp"/>
    <jsp:include page="/WEB-INF/jsp/common/checkPopUp.jsp"/>
    <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/wishlist/editPost.css"/>
    <script src="<%=Const.STATIC_JS_PATH%>/wishlist/editPost.js"></script>
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
    PostVO postVO = (PostVO) request.getAttribute("postInfo");
%>

<div class="edit_post_wrap">
    <div class="edit_post_title_wrap">
        <img class="icon_arrow_left" src="<%=Const.STATIC_IMG_PATH%>/common/icon_arrow_left.png"/>
        <div class="edit_post_title">WishList</div>
    </div>
    <div class="edit_post_divi_line">
    </div>
    <% if (postVO != null) { %>
    <form name="editPostForm" id="editPostForm" method="post">
        <input type=hidden name="planNo" value=<%=postVO.getPlanNo()%>>
        <input type=hidden name="postNo" value=<%=postVO.getPostNo()%>>
        <input type=hidden name="postTypeCode" id="postTypeCode" value="<%=postVO.getPostTypeCode()%>">

        <% if (memberInfo != null) { %>
        <input type=hidden name="memberNo" value=<%=memberInfo.getMemberNo()%>>
        <% } %>
        <div class="edit_post_input_wrap">
            <div class="edit_post_type_wrap">
                <label class="post_type_selectbox_title">장소 타입</label>
                <select name="select_option_post_type" class="select_option_post_type" id="select_option_post_type"
                        disabled>
                    <option id="option selected" value="<%=postVO.getPostTypeCode()%>">
                        <% if (ConstCode.POST_TYPE_CODE_LODGING.equals(postVO.getPostTypeCode())) { %>숙소
                        <% } else if (ConstCode.POST_TYPE_CODE_TOUR.equals(postVO.getPostTypeCode())) { %>관광지
                        <% } else if (ConstCode.POST_TYPE_CODE_RESTAURANT.equals(postVO.getPostTypeCode())) { %>식당
                        <% } else { %>기타<% } %>
                    </option>
                </select>
            </div>
            <div class="edit_post_spot_info_wrap" id="input_spot_info_wrap" style="display: none">
                <div class="edit_post_sub_title">장소 정보*</div>
                <div class="edit_post_spot_title_wrap">
                    <input type="text" name="postTitle" id="spotName" class="input_post_spot_name"
                           value="<%=postVO.getPostTitle()%>" disabled>
                    <div class="edit_post_search">검색</div>
                </div>
                <input type="text" name="spotAddress" id="spotAddress" class="input_edit_post_text"
                       value="<%=postVO.getSpotAddress()%>" disabled>
            </div>

            <div class="edit_post_input_item_wrap" id="input_title_wrap" style="display: none">
                <div class="edit_post_sub_title">제목*</div>
                <input type="text" name="postTitle" id="postTitle" class="input_edit_post_text"
                       value="<%=postVO.getPostTitle()%>">
            </div>
            <div class="edit_post_input_item_wrap" id="input_remark_wrap" style="display: none">
                <div class="edit_post_sub_title">특이사항</div>
                <input type="text" name="remark" id="remark" class="input_edit_post_text"
                       value="<%=postVO.getRemark()%>">
            </div>

            <div class="edit_post_input_item_wrap" id="input_amount_wrap" style="display: none">
                <div class="edit_post_sub_title">이용요금</div>
                <input type="text" name="amount" id="amount" class="input_edit_post_text"
                       value="<%=postVO.getAmount()%>">
            </div>
            <div class="edit_post_input_item_wrap" id="input_business_hours_wrap" style="display: none">
                <% if (ConstCode.POST_TYPE_CODE_TOUR.equals(postVO.getPostTypeCode())) { %>
                <div class="edit_post_sub_title">이용 가능 시간</div>
                <% } else if (ConstCode.POST_TYPE_CODE_RESTAURANT.equals(postVO.getPostTypeCode())) { %>
                <div class="edit_post_sub_title">영업 시간</div>
                <% } %>
                <input type="text" name="businessHours" id="businessHours" class="input_edit_post_text" value="<%=postVO.getBusinessHours()%>">
            </div>

            <div class="edit_post_input_item_wrap" id="input_main_menu_wrap" style="display: none">
                <div class="edit_post_sub_title">대표 메뉴</div>
                <input type="text" name="mainMenu" id="mainMenu" class="input_edit_post_text"
                       value="<%=postVO.getMainMenu()%>">
            </div>

            <div class="edit_post_input_item_wrap" id="input_information_url_wrap">
                <div class="edit_post_sub_title">정보 URL</div>
                <input type="text" name="informationUrl" id="informationUrl" class="input_edit_post_text"
                       value="<%=postVO.getInformationUrl()%>">
            </div>
            <div class="edit_post_sub_title">본문*</div>
            <input type="text" name="postContents" id="postContents" class="input_edit_post_contents"
                   value="<%=postVO.getPostContents()%>">
        </div>
    </form>
    <% } %>
    <div class="edit_post_save" id="edit_post_save">저장</div>
</div>
</body>
</html>
