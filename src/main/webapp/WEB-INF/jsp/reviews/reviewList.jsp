<%@ page import="java.util.List" %>
<%@ page import="com.tripmate.domain.ReviewVO" %>
<%@ page import="com.tripmate.domain.MemberDTO" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/include/commonImport.jsp" %>

<html>
<head>
    <jsp:include page="/WEB-INF/jsp/common/include/header.jsp">
        <jsp:param name="title" value="ReviewList"/>
    </jsp:include>
    <jsp:include page="/WEB-INF/jsp/common/messagePopUp.jsp"/>
    <jsp:include page="/WEB-INF/jsp/common/checkPopUp.jsp"/>
    <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/reviews/reviewList.css"/>
    <script src="<%=Const.STATIC_JS_PATH%>/reviews/reviewList.js"></script>
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

    List<ReviewVO> reviewList = (List<ReviewVO>) request.getAttribute("reviewList");
%>
<% if (memberInfo != null) { %>
<input type="text" id="memberNo" value=<%=memberInfo.getMemberNo()%> hidden>
<% } %>

<div class="reviewlist_wrap">
    <div class="reviewlist_title_wrap">
        <img class="icon_arrow_left" src="<%=Const.STATIC_IMG_PATH%>/common/icon_arrow_left.png"
             onclick="history.back()"/>
        <div class="reviewlist_title">Review</div>
    </div>
    <div class="reviewlist_divi_line">
    </div>

    <form id="reviewListForm" name="reviewListForm" method="post">
        <% if (reviewList != null) {
            for (ReviewVO reviewVO : reviewList) { %>
        <div class="reviewlist_list_wrap">
            <input id="post_type_code" value="<%=reviewVO.getPostTypeCode()%>" hidden>
            <div class="reviewlist_item_wrap">
                <div class="reviewlist_item_title_wrap">
                    <div class="reviewlist_item_ninkname"><%=reviewVO.getNickName()%>
                    </div>
                    <div class="reviewlist_item_avg_score"><%=reviewVO.getReviewAverageScore()%>
                    </div>
                    <% if (memberInfo != null && String.valueOf(memberInfo.getMemberNo()).equals(reviewVO.getRegistrationNo())) { %>
                    <details class="flag">
                        <summary class="ellipsis"></summary>
                        <div class="report">
                            <p class="review_menu_delete" onclick="editReview()">수정하기</p>
                            <p class="review_menu_delete"
                               onclick='deleteReview("<%=reviewVO.getReviewNo()%>", "<%=reviewVO.getDailyPlanNo()%>")'>
                                삭제하기</p>
                        </div>
                    </details>
                    <% } %>
                </div>
                <% if (reviewVO.getReviewImageList().size() > 0) { %>
                <div class="reviewlist_item_img_wrap">
                    <img class="reviewlist_item_img"
                         src="/review/display.trip?filename=<%=reviewVO.getReviewImageList().get(0).getReviewImageName()%>"/>
                </div>
                <% } %>
                <p class="reviewlist_item_contents"><%=reviewVO.getReviewContents()%>
                </p>

                <div class="reviewlist_item_score_wrap" id="review_score_location_wrap" style="display: none">
                    <div class="reviewlist_item_score_title">위치</div>
                    <span class="star">★★★★★
                    <span class="star_score_location" value="<%=reviewVO.getScoreLocation()%>">★★★★★</span>
                    <input type="range" class="star_score_range" id="star_score_location" value="5" step="1" min="0"
                           max="10">
                </span>
                </div>
                <div class="reviewlist_item_score_wrap" id="review_score_amount_wrap" style="display: none">
                    <div class="reviewlist_item_score_title">금액</div>
                    <span class="star">★★★★★
                    <span class="star_score_amount" value="<%=reviewVO.getScoreAmount()%>">★★★★★</span>
                    <input type="range" class="star_score_range" id="star_score_amount" value="5" step="1" min="0"
                           max="10">
                </span>
                </div>
                <div class="reviewlist_item_score_wrap" id="review_score_all_wrap" style="display: none">
                    <div class="reviewlist_item_score_title">전체</div>
                    <span class="star">★★★★★
                    <span class="star_score_all" value="<%=reviewVO.getScoreAll()%>">★★★★★</span>
                    <input type="range" class="star_score_range" id="star_score_all" value="5" step="1" min="0"
                           max="10">
                </span>
                </div>
                <div class="reviewlist_item_score_wrap" id="review_score_facility_wrap" style="display: none">
                    <div class="reviewlist_item_score_title">편의시설</div>
                    <span class="star">★★★★★
                    <span class="star_score_facility">★★★★★</span>
                    <input type="range" class="star_score_range" id="star_score_facility"
                           value="<%=reviewVO.getScoreFacility()%>" step="1" min="0" max="10">
                </span>
                </div>
                <div class="reviewlist_item_score_wrap" id="review_score_sanitary_wrap" style="display: none">
                    <div class="reviewlist_item_score_title">위생</div>
                    <span class="star">★★★★★
                    <span class="star_score_sanitary" value="<%=reviewVO.getScoreSanitary()%>">★★★★★</span>
                    <input type="range" class="star_score_range" id="star_score_sanitary" value="5" step="1" min="0"
                           max="10">
                </span>
                </div>
                <div class="reviewlist_item_score_wrap" id="review_score_service_wrap" style="display: none">
                    <div class="reviewlist_item_score_title">서비스</div>
                    <span class="star">★★★★★
                    <span class="star_score_service" value="<%=reviewVO.getScoreService()%>">★★★★★</span>
                    <input type="range" class="star_score_range" id="star_score_service" value="5" step="1" min="0"
                           max="10">
                </span>
                </div>
                <div class="reviewlist_item_score_wrap" id="review_score_food_wrap" style="display: none">
                    <div class="reviewlist_item_score_title">음식</div>
                    <span class="star">★★★★★
                    <span class="star_score_food" value="<%=reviewVO.getScoreFood()%>">★★★★★</span>
                    <input type="range" class="star_score_range" id="star_score_food" value="5" step="1" min="0"
                           max="10">
                </span>
                </div>
            </div>
        </div>
        <% }
        } %>
    </form>
</div>
</body>