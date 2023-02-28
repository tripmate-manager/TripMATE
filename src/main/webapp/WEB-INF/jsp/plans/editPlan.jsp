<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tripmate.domain.PlanAttributeVO" %>
<%@ page import="java.util.Set" %>
<%@ page import="com.tripmate.domain.PlanVO" %>
<%@ page import="com.tripmate.domain.PlanAddressVO" %>
<%@ page import="com.tripmate.domain.MemberDTO" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/include/commonImport.jsp" %>

<html>
<head>
    <jsp:include page="/WEB-INF/jsp/common/include/header.jsp">
        <jsp:param name="title" value="CreatePlan"/>
    </jsp:include>
    <jsp:include page="/WEB-INF/jsp/common/messagePopUp.jsp"/>
    <jsp:include page="/WEB-INF/jsp/common/checkPopUp.jsp"/>
    <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/plans/createPlan.css"/>
    <script src="<%=Const.STATIC_JS_PATH%>/plans/editPlan.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/plans/planValidationCheck.js"></script>
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

    PlanVO planVO = (PlanVO) request.getAttribute("planVO");
%>
<% if (memberInfo != null) { %>
<input type=hidden id="memberNo" value=<%=memberInfo.getMemberNo()%>>
<% } %>
<input type=hidden id="publicYn" value=<%=planVO.getPublicYn()%>>
<input type=hidden id="planNo" value=<%=planVO.getPlanNo()%>>

<div class="createplan_wrap">
    <div class="createplan_title_wrap">
        <img class="icon_arrow_left" src="<%=Const.STATIC_IMG_PATH%>/common/icon_arrow_left.png"/>
        <div class="createplan_title">My Plan</div>
    </div>
    <div class="createplan_divi_line"></div>
    <form name="createPlanForm" id="createPlanForm" method="post">
        <div class="createplan_input_wrap">
            <div class="createplan_input_default_wrap">
                <div class="input_checkbox_public_wrap">
                    <div class="createplan_sub_title">공개 여부</div>
                    <input type="checkbox" name="publicYn" id="checkboxPublic" class="checkboxPublic">
                    <label for="checkboxPublic"></label>
                </div>
                <div class="createplan_sub_title">플랜 제목</div>
                <input type="text" name="planTitle" id="planTitle" class="input_plan_title" value="<%=planVO.getPlanTitle()%>">
                <div class="createplan_sub_title">플랜 설명</div>
                <input type="text" name="planDescription" id="planDescription" class="input_plan_desc" value="<%=planVO.getPlanDescription()%>">
                <div class="createplan_sub_title">여행지</div>

                <div class="plan_address_choice_btn_wrap">
                    <input type="text" name="planAddress" id="planAddress" class="input_plan_address" disabled>
                    <div class="plan_address_choice_btn">선택</div>
                </div>

                <div class="createplan_trip_address" style="display: none">
                    <label class="label_selectbox_title">시도명</label>
                    <select name="select_option_sido" class="select_option" id="select_option_sido" onchange="address_option(select_option_sido.value)">
                        <option value="default">--시도 선택--</option>
                        <% for (String sidoName : sidoNameList) { %>
                        <option value="<%=sidoName%>"><%=sidoName%></option>
                        <% } %>
                    </select>
                    <br>
                    <label class="label_selectbox_title">시군구명</label>
                    <select name="select_option_sigungu" class="select_option" id="select_option_sigungu" disabled>
                        <option value="default">--시군구 선택--</option>
                    </select>
                    <input type="button" id="select_address_btn" value="확인">
                </div>

                <div class="createplan_address_list">
                    <% for (PlanAddressVO planAddressVO : planVO.getPlanAddressList()) { %>
                    <div class="address_item_text" value=<%=planAddressVO.getAddressNo()%>><%=planAddressVO.getSidoName()%> <%=planAddressVO.getSigunguName()%></div>
                    <% } %>
                </div>

                <div class="createplan_sub_title">여행일자</div>
                <div class="input_plan_date_wrap">
                    <input type="date" id="planStartDate" name="planStartDate" class="createplan_input_date" value=<%=planVO.getTripStartDate()%>>
                    <div class="plan_date_wave">~</div>
                    <input type="date" id="planEndDate" name="planEndDate" class="createplan_input_date" value=<%=planVO.getTripEndDate()%>>
                </div>
            </div>
            <div class="createplan_divi_line"></div>
            <div class="createplan_trip_theme_wrap">
                <div class="createplan_choice_sub_title_wrap">
                    <div class="createplan_sub_title_choice">여행 테마</div>
                    <img class="icon_arrow_down" id="theme_arrow_down_btn" src="<%=Const.STATIC_IMG_PATH%>/common/icon_arrow_down.png" style="display: none"/>
                </div>
                <div class="plan_theme_choice_wrap">
                    <div class="plan_theme_item_list">
                        <div class="plan_theme_item_list_group">
                            <% for (PlanAttributeVO planAttributeVO : planThemeList) { %>
                            <input type="checkbox" class="plan_theme_item"
                                   id="plan_theme_item_<%=planAttributeVO.getAttributeNo()%>" value="<%=planAttributeVO.getAttributeNo()%>" style="display:none"
                            <% for (PlanAttributeVO planVOAttribute : planVO.getPlanAttributeList()) {
                                if (planVOAttribute.getAttributeNo() == planAttributeVO.getAttributeNo()) { %>
                                   checked
                                <% }
                            } %>>
                            <label for="plan_theme_item_<%=planAttributeVO.getAttributeNo()%>"><%=planAttributeVO.getAttributeName()%>
                            </label>
                            <% } %>
                        </div>
                    </div>
                    <img class="icon_arrow_up" id="theme_arrow_up_btn"
                         src="<%=Const.STATIC_IMG_PATH%>/common/icon_arrow_up.png"/>
                </div>
            </div>
            <div class="createplan_divi_line">
                <div class="createplan_input_wrap">
                    <div class="createplan_choice_sub_title_wrap">
                        <div class="createplan_sub_title_choice">해시태그</div>
                        <img class="icon_arrow_down" id="hashtag_arrow_down_btn" src="<%=Const.STATIC_IMG_PATH%>/common/icon_arrow_down.png" style="display: none"/>
                    </div>
                    <div class="plan_hashtag_choice_wrap">
                        <div class="createplan_input_hashtag_wrap">
                            <img class="icon_plus_circle" src="<%=Const.STATIC_IMG_PATH%>/plans/icon_plus_circle.png"/>
                            <div class="createplan_input_hashtag">
                                <input type="text" name="hashtag" id="hashtag" class="input_createplan_hashtag"
                                       placeholder="추가">
                                <div class="createplan_add_hashtag_btn">등록</div>
                            </div>
                        </div>
                        <div class="createplan_hashtag_list" >
                            <% for (PlanAttributeVO planAttributeVO : planVO.getPlanAttributeList()) {
                                if (ConstCode.ATTRIBUTE_TYPE_CODE_HASHTAG.equals(planAttributeVO.getAttributeTypeCode())) { %>
                            <div class="hashtag_item_text"><%=planAttributeVO.getAttributeName()%></div>
                            <% }
                            } %>
                        </div>
                        <img class="icon_arrow_up" id="hashtag_arrow_up_btn"
                             src="<%=Const.STATIC_IMG_PATH%>/common/icon_arrow_up.png"/>
                    </div>
                    <div class="createplan_divi_line"></div>
                </div>
            </div>
        </div>
    </form>
    <div class="createplan_save">저장</div>
</div>
</body>
</html>
