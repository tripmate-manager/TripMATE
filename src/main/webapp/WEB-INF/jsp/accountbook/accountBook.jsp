<%@ page import="com.tripmate.domain.MemberDTO" %>
<%@ page import="com.tripmate.domain.AccountBookVO" %>
<%@ page import="com.tripmate.domain.AccountVO" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/include/commonImport.jsp" %>
<%@ include file="/WEB-INF/jsp/common/bottomNavigationMenu.jsp" %>

<html>
<head>
    <jsp:include page="/WEB-INF/jsp/common/include/header.jsp">
        <jsp:param name="title" value="AccountBook"/>
    </jsp:include>
    <jsp:include page="/WEB-INF/jsp/common/messagePopUp.jsp"/>
    <jsp:include page="/WEB-INF/jsp/common/checkPopUp.jsp"/>
    <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/accountbook/accountBook.css"/>
    <script src="<%=Const.STATIC_JS_PATH%>/accountbook/accountBook.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/common/popUp.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/common/checkPopUp.js"></script>
</head>
<body>
<%
    String planNo = (String) request.getAttribute("planNo");
    AccountBookVO accountBookVO = (AccountBookVO) request.getAttribute("accountList");

    MemberDTO memberInfo = null;
    session = request.getSession();
    if (session != null) {
        memberInfo = (MemberDTO) session.getAttribute(Const.MEMBER_INFO_SESSION);
    }
%>

<div class="accountbook_wrap">

    <div class="accountbook_title_wrap">
        <img class="icon_arrow_left" id="icon_arrow_left" src="<%=Const.STATIC_IMG_PATH%>/common/icon_arrow_left.png"/>
        <div class="accountbook_cancel" id="accountbook_cancel" style="display: none">취소</div>
        <div class="accountbook_title">여행가계부</div>
        <div class="accountbook_edit" id="accountbook_edit">편집</div>
        <div class="accountbook_save" id="accountbook_save" style="display: none">완료</div>
    </div>

    <form id="accountBookForm" method="post">
        <% if (memberInfo != null) { %>
        <input type=hidden id="sessionMemberNo" name="memberNo" value=<%=memberInfo.getMemberNo()%>>
        <% } %>

        <% if (planNo != null) { %>
        <input type="text" id="planNo" name="planNo" value=<%=planNo%> hidden>
        <% } %>

        <% if (accountBookVO != null) { %>
        <div class="accountbook_day_list_wrap">
            <div class="accountbook_day_wrap">
                <input type="text" id="dayGroup" name="dayGroup" value=<%=accountBookVO.getDayGroup()%> hidden>
                <% for (int day = 1; day <= Integer.parseInt(accountBookVO.getTripTerm()) + 1; day++) { %>
                <div class="accountbook_day_item <% if (String.valueOf(day).equals(accountBookVO.getDayGroup())) { %>select_day<%}%>" value="<%=day%>">Day<%=day%></div>
                <% } %>
            </div>
        </div>

        <div class="accountbook_list_wrap">
        <% if (accountBookVO.getAccountList().size() > 0) {
           for (AccountVO accountVO : accountBookVO.getAccountList()) { %>
            <div class="accountbook_list">
                <div class="accountbook_item_wrap" draggable="true">
                    <input type=hidden id="accountbook_item_no" name="accountNo" value=<%=accountVO.getAccountNo()%>>

                    <img class="icon_sorting" src="<%=Const.STATIC_IMG_PATH%>/accountbook/icon_sorting.png" style="display:none;"/>
                    <div class="accountbook_item_type">
                        <% if (ConstCode.POST_TYPE_CODE_LODGING.equals(accountVO.getPostTypeCode())) { %>숙소
                        <% } else if (ConstCode.POST_TYPE_CODE_TOUR.equals(accountVO.getPostTypeCode())) { %>관광지
                        <% } else if (ConstCode.POST_TYPE_CODE_RESTAURANT.equals(accountVO.getPostTypeCode())) { %>식당
                        <% } else { %>기타<% } %>
                    </div>
                    <div class="accountbook_item_name"><%=accountVO.getAccountName()%></div>
                    <div class="accountbook_input_wrap">
                        <input type="number" name="accountName" class="accountbook_input" id="accountbook_input" placeholder="<%=accountVO.getAmount() == null ? "" : accountVO.getAmount()%>">
                        <div class="account_input_won">원</div>
                    </div>
                    <img class="icon_list_delete_circle"
                         src="<%=Const.STATIC_IMG_PATH%>/checklist/icon_list_delete_circle.png" style="display:none;"/>
                </div>
            </div>
            <% }
            } %>

            <div class="add_account_item_wrap">
                <img class="icon_plus_circle" src="<%=Const.STATIC_IMG_PATH%>/plans/icon_plus_circle.png"/>
                <input type="text" name="accountName" class="input_account_item" placeholder="추가" disabled>
            </div>

            <div class="add_account_wrap" style="display: none">
                <div class="add_account_wrap">
                    <label class="label_selectbox_title">타입</label>
                    <select name="select_post_type" class="select_post_type" id="select_post_type">
                        <option id="option_lodging" value="<%=ConstCode.POST_TYPE_CODE_LODGING%>">숙소</option>
                        <option id="option_tour" value="<%=ConstCode.POST_TYPE_CODE_TOUR%>">관광지</option>
                        <option id="option_restaurant" value="<%=ConstCode.POST_TYPE_CODE_RESTAURANT%>">식당</option>
                        <option id="option_etc" value="<%=ConstCode.POST_TYPE_CODE_ETC%>">기타</option>
                    </select>
                </div>
                <input type="text" name="accountName" class="account_desc_title" id="account_desc_title" placeholder="경비 설명">
                <div class="add_account_input_wrap">
                    <div class="account_input_title">사용 경비</div>
                    <input type="number" name="accountName" class="accountbook_add_input" id="accountbook_add_input">
                    <div class="account_input_won">원</div>
                </div>
                <div class="add_account_btn" id="add_account_btn">등록</div>
            </div>
        </div>

        <div class="total_wrap">
            <div class="total_day_rectangle">
            </div>
            <div class="day_total_wrap">
                <div class="day_total_title">Day1</div>
                <div class="day_total_won"><%=accountBookVO.getDayAmountSum()%>원</div>
            </div>

            <div class="total_person_wrap">
                <div class="total_account_wrap">
                    <div class="total_title">여행경비 총합</div>
                    <div class="total_won"><%=accountBookVO.getPlanAmountSum()%>원</div>
                </div>
                <div class="total_account_wrap">
                    <div class="total_title">1인당 경비</div>
                    <div class="total_won"><%=accountBookVO.getPlanMateAmountSum()%>원</div>
                </div>
            </div>
        </div>
        <% } %>
    </form>
</div>
</body>
</html>