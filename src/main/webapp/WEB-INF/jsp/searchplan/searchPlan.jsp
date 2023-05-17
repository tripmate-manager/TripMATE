<%@ page import="com.tripmate.domain.MemberDTO" %>
<%@ page import="com.tripmate.domain.PlanAttributeVO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Set" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/include/commonImport.jsp" %>

<html>
<head>
    <jsp:include page="/WEB-INF/jsp/common/include/header.jsp">
        <jsp:param name="title" value="searchPlan"/>
    </jsp:include>
    <jsp:include page="/WEB-INF/jsp/common/messagePopUp.jsp"/>
    <jsp:include page="/WEB-INF/jsp/common/checkPopUp.jsp"/>
    <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/searchplan/searchPlan.css"/>
    <script src="<%=Const.STATIC_JS_PATH%>/searchplan/searchPlan.js"></script>
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

    List<PlanAttributeVO> planThemeList = (List<PlanAttributeVO>) request.getAttribute("planThemeList");
    Set<String> sidoNameList = (Set<String>) request.getAttribute("sidoNameList");
    List<String> popularSearchKeywordList = (List<String>) request.getAttribute("popularSearchKeywordList");
    List<String> popularHashtagList = (List<String>) request.getAttribute("popularHashtagList");
%>

<div class="searchplan_wrap">
    <form name="searchPlanForm" id="searchPlanForm" method="post">
        <% if (memberInfo != null) { %>
        <div id="session_member_no" style="display: none"><%=memberInfo.getMemberNo()%></div>
        <% } %>
        <input type=hidden id="input_member_no" name="memberNo" hidden>

        <div class="input_search_wrap">
            <img class="icon_arrow_left" id="icon_arrow_left"
                 src="<%=Const.STATIC_IMG_PATH%>/common/icon_arrow_left.png"/>
            <input type="text" name="keyword" class="input_search" id="input_search" placeholder="검색어를 입력하세요">
            <img class="icon_search" id="icon_search" src="<%=Const.STATIC_IMG_PATH%>/common/icon_search.png"/>
        </div>
        <div class="searchplan_tab_menu">
            <div class="tab_menu_text tab_select" id="tab_menu_text">일반 검색</div>
            <div class="tab_menu_attribute" id="tab_menu_attribute">속성 검색</div>
        </div>
        <div class="searchplan_contents_text_wrap">
            <% if (popularSearchKeywordList != null && popularSearchKeywordList.size() > 0) { %>
            <div class="recommend_word_title">추천 검색어</div>
            <div class="recommend_word_wrap">
                <% for (String searchKeyword : popularSearchKeywordList) { %>
                <div class="recommend_word"><%=searchKeyword%></div>
                <% } %>
            </div>
            <% } %>
            <% if (popularHashtagList != null && popularHashtagList.size() > 0) { %>
            <div class="popular_hashtag_title">해시태그 인기 키워드</div>
            <div class="popular_hashtag_wrap">
                <% for (String hashtag : popularHashtagList) { %>
                <div class="popular_hashtag"># <%=hashtag%></div>
                <% } %>
            </div>
            <% } %>
        </div>

        <div class="searchplan_contents_attribute_wrap" style="display: none;">
            <div class="searchplan_input_default_wrap">
                <div class="searchplan_sub_title">연령대</div>
                <div class="attribute_age_wrap">
                    <div class="attribute_age_line1">
                        <input type="radio" name="age" class="attribute_age_item" id="attribute_age_10"
                               value="10" style="display: none">
                        <label for="attribute_age_10">10대</label>
                        <input type="radio" name="age" class="attribute_age_item" id="attribute_age_20"
                               value="20" style="display: none">
                        <label for="attribute_age_20">20대</label>
                        <input type="radio" name="age" class="attribute_age_item" id="attribute_age_30"
                               value="30" style="display: none">
                        <label for="attribute_age_30">30대</label>
                    </div>
                    <div class="attribute_age_line2">
                        <input type="radio" name="age" class="attribute_age_item" id="attribute_age_40"
                               value="40" style="display: none">
                        <label for="attribute_age_40">40대</label>
                        <input type="radio" name="age" class="attribute_age_item" id="attribute_age_50"
                               value="50" style="display: none">
                        <label for="attribute_age_50">50대</label>
                        <input type="radio" name="age" class="attribute_age_item" id="attribute_age_60"
                               value="60" style="display: none">
                        <label for="attribute_age_60">60대 이상</label>
                    </div>
                </div>

                <div class="attribute_gender_wrap">
                    <div class="searchplan_sub_title">성별</div>
                    <div class="btn-toggle">
                        <input type="text" name="genderCode" id="genderCode" value="<%=ConstCode.GENDER_CODE_MALE%>"
                               hidden>
                        <button class="btn-default" type="button">남</button>
                        <button class="btn-primary active" type="button">여</button>
                    </div>
                </div>
                <div class="attribute_personnel_wrap">
                    <div class="searchplan_sub_title">인원</div>
                    <div class="attribute_personnel">
                        <img class="icon_minus" id="personnel_minus"
                             src="<%=Const.STATIC_IMG_PATH%>/searchplan/icon_minus_circle.png"/>
                        <input type="number" name="personnel" class="input_personnel" id="input_personnel" value="0"
                               placeholder="0">
                        <img class="icon_plus" id="personnel_plus"
                             src="<%=Const.STATIC_IMG_PATH%>/plans/icon_plus_circle.png"/>
                    </div>
                </div>
            </div>
            <div class="searchplan_divi_line"></div>

            <div class="searchplan_choice_wrap">
                <div class="searchplan_choice_sub_title_wrap">
                    <div class="searchplan_sub_title">여행지</div>
                    <img class="icon_arrow_down" id="trip_address_arrow_down_btn"
                         src="<%=Const.STATIC_IMG_PATH%>/common/icon_arrow_down.png"/>
                </div>
                <div class=trip_address_choice_wrap" id="trip_address_choice_wrap" style="display: none">
                    <div class="attribute_trip_address_wrap">
                        <div class="attribute_trip_address_btn" id="trip_address_btn">지역 선택</div>
                        <div class="attribute_spot_btn" id="spot_btn">장소 검색</div>
                    </div>
                    <div class="searchplan_trip_address" id="trip_address_choice" style="display: none">
                        <div class="select_address_wrap">
                            <label class="label_selectbox_title">시도명</label>
                            <select name="select_option_sido" class="select_option" id="select_option_sido"
                                    onchange="address_option(select_option_sido.value)">
                                <option value="default">--시도 선택--</option>
                                <% for (String sidoName : sidoNameList) { %>
                                <option value="<%=sidoName%>"><%=sidoName%>
                                </option>
                                <% } %>
                            </select>
                        </div>
                        <br>
                        <div class="select_address_wrap">
                            <label class="label_selectbox_title">시군구명</label>
                            <select name="select_option_sigungu" class="select_option" id="select_option_sigungu"
                                    disabled>
                                <option value="default">--시군구 선택--</option>
                            </select>
                        </div>
                        <input type="button" id="select_address_btn" value="확인">
                        <div class="searchplan_address_list" id="searchplan_address_list" style="display:none;"></div>
                    </div>
                    <div class="searchplan_choice" id="spot_choice" style="display: none">
                        <div class="searchplan_spot_list" style="display:none;"></div>
                    </div>
                    <img class="icon_arrow_up" id="trip_address_arrow_up_btn"
                         src="<%=Const.STATIC_IMG_PATH%>/common/icon_arrow_up.png"/>
                </div>
            </div>
            <div class="searchplan_divi_line"></div>

            <div class="searchplan_choice_wrap">
                <div class="searchplan_choice_sub_title_wrap">
                    <div class="searchplan_sub_title">여행 기간</div>
                    <img class="icon_arrow_down" id="trip_term_arrow_down_btn"
                         src="<%=Const.STATIC_IMG_PATH%>/common/icon_arrow_down.png"/>
                </div>
                <div class="trip_term_choice_wrap" id="trip_term_choice_wrap" style="display: none">
                    <div class="trip_term_choice">
                        <div class="searchplan_trip_term" id="searchplan_trip_term" style="display: none"></div>
                        <input name="tripTerm" id="input_tripTerm" hidden disabled>
                        <label class="label_selectbox_title"></label>
                        <select class="select_option" id="select_option_trip_term">
                            <option value="default">--여행 기간--</option>
                            <option value="0">당일치기</option>
                            <option value="1">1박 2일</option>
                            <option value="2">2박 3일</option>
                            <option value="3">3박 4일</option>
                            <option value="4">4박 5일</option>
                            <option value="5">5박 6일</option>
                            <option value="6">6박 7일</option>
                            <option value="7">1주</option>
                            <option value="14">2주</option>
                            <option value="21">3주</option>
                            <option value="30">한 달</option>
                            <option value="60">2개월</option>
                            <option value="90">3개월</option>
                            <option value="91">3개월 이상</option>
                        </select>
                    </div>
                    <img class="icon_arrow_up" id="trip_term_arrow_up_btn"
                         src="<%=Const.STATIC_IMG_PATH%>/common/icon_arrow_up.png"/>
                </div>
            </div>
            <div class="searchplan_divi_line"></div>

            <div class="searchplan_choice_wrap">
                <div class="searchplan_trip_theme_wrap">
                    <div class="searchplan_choice_sub_title_wrap">
                        <div class="searchplan_sub_title">여행 테마</div>
                        <img class="icon_arrow_down" id="theme_arrow_down_btn"
                             src="<%=Const.STATIC_IMG_PATH%>/common/icon_arrow_down.png"/>
                    </div>
                    <div class="plan_theme_choice_wrap" id="plan_theme_choice_wrap" style="display: none">
                        <div class="plan_theme_item_list">
                            <div class="plan_theme_item_list_group">
                                <% for (PlanAttributeVO planAttributeVO : planThemeList) { %>
                                <input type="checkbox" class="plan_theme_item"
                                       id="plan_theme_item_<%=planAttributeVO.getAttributeNo()%>"
                                       value="<%=planAttributeVO.getAttributeNo()%>" name="planThemeList" style="display:none">
                                <label for="plan_theme_item_<%=planAttributeVO.getAttributeNo()%>"><%=planAttributeVO.getAttributeName()%>
                                </label>
                                <% } %>
                            </div>
                        </div>
                        <img class="icon_arrow_up" id="theme_arrow_up_btn"
                             src="<%=Const.STATIC_IMG_PATH%>/common/icon_arrow_up.png"/>
                    </div>
                </div>
            </div>
            <div class="searchplan_divi_line"></div>
        </div>
    </form>
    <div class="searchplan_attribute" id="searchplan_attribute" style="display: none">적용하기</div>
</div>
</body>
</html>