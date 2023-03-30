<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.tripmate.domain.MemberDTO" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/include/commonImport.jsp" %>

<html>
<head>
    <jsp:include page="/WEB-INF/jsp/common/include/header.jsp">
        <jsp:param name="title" value="CreatePost"/>
    </jsp:include>
    <jsp:include page="/WEB-INF/jsp/common/messagePopUp.jsp"/>
    <jsp:include page="/WEB-INF/jsp/common/checkPopUp.jsp"/>
    <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/wishlist/createPost.css"/>
    <script src="<%=Const.STATIC_JS_PATH%>/wishlist/createPost.js"></script>
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
    String planNo = (String) request.getAttribute("planNo");
%>

<div class="create_post_wrap">
    <div class="create_post_title_wrap">
        <img class="icon_arrow_left" src="<%=Const.STATIC_IMG_PATH%>/common/icon_arrow_left.png"/>
        <div class="create_post_title">WishList</div>
    </div>
    <div class="create_post_divi_line">
    </div>
    <div class="create_post_input_wrap">
        <div class="create_post_type_wrap">
            <label class="post_type_selectbox_title">장소 타입</label>
            <select name="select_option_post_type" class="select_option_post_type" id="select_option_post_type">
                <option id="option_lodging selected" value="<%=ConstCode.POST_TYPE_CODE_LODGING%>">숙소</option>
                <option id="option_tour" value="<%=ConstCode.POST_TYPE_CODE_TOUR%>">관광지</option>
                <option id="option_restaurant" value="<%=ConstCode.POST_TYPE_CODE_RESTAURANT%>">식당</option>
                <option id="option_etc" value="<%=ConstCode.POST_TYPE_CODE_ETC%>">기타</option>
            </select>
        </div>
        <form name="createPostForm" id="createPostForm" method="post">
            <input type=hidden name="postNo" id="postNo">

            <% if (memberInfo != null) { %>
            <input type=hidden name="memberNo" value=<%=memberInfo.getMemberNo()%>>
            <% } %>
            <% if (planNo != null) { %>
            <input type="hidden" name="planNo" value="<%=planNo%>">
            <% } %>
            <input type=hidden id="postTypeCode" name="postTypeCode">
            <div class="create_post_spot_info_wrap" id="input_spot_info_wrap">
                <div class="create_post_sub_title">장소 정보*</div>
                <div class="create_post_spot_title_wrap">
                    <input type="text" name="postTitle" id="spotName" class="input_post_spot_name" placeholder="장소명">
                    <div class="create_post_search">검색</div>
                </div>
                <input type="text" name="spotAddress" id="spotAddress" class="input_create_post_text" placeholder="위치">
            </div>

            <div class="create_post_input_item_wrap" id="input_title_wrap" style="display: none">
                <div class="create_post_sub_title">제목*</div>
                <input type="text" name="postTitle" id="postTitle" class="input_create_post_text" placeholder="직접입력">
            </div>
            <div class="create_post_input_item_wrap" id="input_remark_wrap" style="display: none">
                <div class="create_post_sub_title">특이사항</div>
                <input type="text" name="remark" id="remark" class="input_create_post_text">
            </div>

            <div class="create_post_input_item_wrap" id="input_amount_wrap" style="display: none">
                <div class="create_post_sub_title">이용요금</div>
                <input type="text" name="amount" id="amount" class="input_create_post_text">
            </div>
            <div class="create_post_input_item_wrap" id="input_business_hours_wrap" style="display: none">
                <div class="create_post_sub_title" id="businessHours_title">이용 가능 시간</div>
                <input type="text" name="businessHours" id="businessHours" class="input_create_post_text">
            </div>

            <div class="create_post_input_item_wrap" id="input_main_menu_wrap" style="display: none">
                <div class="create_post_sub_title">대표 메뉴</div>
                <input type="text" name="mainMenu" id="mainMenu" class="input_create_post_text">
            </div>

            <div class="create_post_input_item_wrap" id="input_information_url_wrap">
                <div class="create_post_sub_title">정보 URL</div>
                <input type="text" name="informationUrl" id="informationUrl" class="input_create_post_text">
            </div>
            <div class="create_post_sub_title">본문*</div>
            <input type="text" name="postContents" id="postContents" class="input_create_post_contents">
    </div>
    <div class="create_post_save">저장</div>
    </form>
</div>
</body>
</html>
