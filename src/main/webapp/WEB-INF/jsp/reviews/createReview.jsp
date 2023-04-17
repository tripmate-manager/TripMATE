<%@ page import="com.tripmate.domain.MemberDTO" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/include/commonImport.jsp" %>

<html>
<head>
    <jsp:include page="/WEB-INF/jsp/common/include/header.jsp">
        <jsp:param name="title" value="CreateReview"/>
    </jsp:include>
    <jsp:include page="/WEB-INF/jsp/common/messagePopUp.jsp"/>
    <jsp:include page="/WEB-INF/jsp/common/checkPopUp.jsp"/>
    <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/reviews/createReview.css"/>
    <script src="<%=Const.STATIC_JS_PATH%>/reviews/createReview.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/common/popUp.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/common/checkPopUp.js"></script>
</head>
<body>
<%
    String postTypeCode = (String) request.getAttribute("postTypeCode");
    String dailyPlanNo = (String) request.getAttribute("dailyPlanNo");

    MemberDTO memberInfo = null;
    session = request.getSession();

    if (session != null) {
        memberInfo = (MemberDTO) session.getAttribute(Const.MEMBER_INFO_SESSION);
    }
%>

<div class="create_review_wrap">
    <div class="create_review_title_wrap">
        <img class="icon_arrow_left" src="<%=Const.STATIC_IMG_PATH%>/common/icon_arrow_left.png"/>
        <div class="create_review_title">Review</div>
    </div>
    <div class="create_review_divi_line">
    </div>
    <form id="createReviewForm" method="post" enctype="multipart/form-data">
        <% if (memberInfo != null) { %>
        <input type="text" id="member_no" name="memberNo" hidden value=<%=memberInfo.getMemberNo()%>>
        <% } %>
        <% if (dailyPlanNo != null) { %>
        <input type=text id="dailyplan_no" name="dailyPlanNo" hidden value=<%=dailyPlanNo%>>
        <% } %>
        <% if (postTypeCode != null) { %>
        <input type="text" id="post_type_code" name="postTypeCode" hidden value="<%=postTypeCode%>">
        <% } %>
        <input type="text" id="review_average_score" name="reviewAverageScore" hidden>

        <div class="create_review_input_wrap">
            <div class="create_review_input_score_wrap">
                <div class="create_review_input_score_item" id="review_score_location_wrap" style="display: none">
                    <div class="create_review_input_score_title">위치</div>
                    <input type="hidden" id="review_score_location" class="review_score" name="scoreLocation" value="0">
                    <span class="star">★★★★★
                    <span class="star_score_location">★★★★★</span>
                    <input type="range" oninput="drawStar(this)" id="star_score_location" value="0" step="1" min="0"
                           max="10">
                </span>
                </div>
                <div class="create_review_input_score_item" id="review_score_amount_wrap" style="display: none">
                    <div class="create_review_input_score_title">금액</div>
                    <input type="hidden" id="review_score_amount" class="review_score" name="scoreAmount" value="0">
                    <span class="star">★★★★★
                    <span class="star_score_amount">★★★★★</span>
                    <input type="range" oninput="drawStar(this)" id="star_score_amount" value="0" step="1" min="0"
                           max="10">
                </span>
                </div>
                <div class="create_review_input_score_item" id="review_score_all_wrap" style="display: none">
                    <div class="create_review_input_score_title">전체</div>
                    <input type="hidden" id="review_score_all" class="review_score" name="scoreAll" value="0">
                    <span class="star">★★★★★
                    <span class="star_score_all">★★★★★</span>
                    <input type="range" oninput="drawStar(this)" id="star_score_all" value="0" step="1" min="0"
                           max="10">
                </span>
                </div>
                <div class="create_review_input_score_item" id="review_score_facility_wrap" style="display: none">
                    <div class="create_review_input_score_title">편의시설</div>
                    <input type="hidden" id="review_score_facility" class="review_score" name="scoreFacility" value="0">
                    <span class="star">★★★★★
                    <span class="star_score_facility">★★★★★</span>
                    <input type="range" oninput="drawStar(this)" id="star_score_facility" value="0" step="1" min="0"
                           max="10">
                </span>
                </div>
                <div class="create_review_input_score_item" id="review_score_sanitary_wrap" style="display: none">
                    <div class="create_review_input_score_title">위생</div>
                    <input type="hidden" id="review_score_sanitary" class="review_score" name="scoreSanitary" value="0">
                    <span class="star">★★★★★
                    <span class="star_score_sanitary">★★★★★</span>
                    <input type="range" oninput="drawStar(this)" id="star_score_sanitary" value="0" step="1" min="0"
                           max="10">
                </span>
                </div>
                <div class="create_review_input_score_item" id="review_score_service_wrap" style="display: none">
                    <div class="create_review_input_score_title">서비스</div>
                    <input type="hidden" id="review_score_service" class="review_score" name="scoreService" value="0">
                    <span class="star">★★★★★
                    <span class="star_score_service">★★★★★</span>
                    <input type="range" oninput="drawStar(this)" id="star_score_service" value="0" step="1" min="0"
                           max="10">
                </span>
                </div>
                <div class="create_review_input_score_item" id="review_score_food_wrap" style="display: none">
                    <div class="create_review_input_score_title">음식</div>
                    <input type="hidden" id="review_score_food" class="review_score" name="scoreFood" value="0">
                    <span class="star">★★★★★
                    <span class="star_score_food">★★★★★</span>
                    <input type="range" oninput="drawStar(this)" id="star_score_food" value="0" step="1" min="0"
                           max="10">
                </span>
                </div>
            </div>
            <div class="create_review_input_score_title">리뷰를 작성해주세요</div>
            <input type="text" id="create_review_input_contents" class="create_review_input_contents"
                   name="reviewContents">

            <div class="create_review_input_img_title">이미지 첨부</div>
            <div class="create_review_input_img_list" id="review_input_img_list">
                <div class="create_review_input_img_wrap">
                    <img src="<%=Const.STATIC_IMG_PATH%>/reviews/icon_camera.png" class="create_review_input_img"/>
                    <label for="img_select" class="create_review_input_img"></label>
                    <input id="img_select" type="file" accept="image/jpg, image/jpeg, image/pjpeg, image/png, image/gif, image/bmp"
                           name="multipartFile" multiple/>
                    <div class="create_review_img_count_wrap">
                        <div class="create_review_input_img_count" id="review_img_input_count">0</div>
                        <div class="create_review_input_img_count">/5</div>
                    </div>
                </div>
            </div>
        </div>
        <div class="create_review_save" id="review_save">저장</div>
    </form>
</div>
</body>