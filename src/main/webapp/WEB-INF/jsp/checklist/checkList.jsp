<%@ page import="com.tripmate.domain.CheckListVO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tripmate.domain.MemberDTO" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/include/commonImport.jsp" %>
<%@ include file="/WEB-INF/jsp/common/bottomNavigationMenu.jsp" %>

<html>
<head>
    <jsp:include page="/WEB-INF/jsp/common/include/header.jsp">
        <jsp:param name="title" value="CheckList"/>
    </jsp:include>
    <jsp:include page="/WEB-INF/jsp/common/messagePopUp.jsp"/>
    <jsp:include page="/WEB-INF/jsp/common/checkPopUp.jsp"/>
    <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/checklist/checkList.css"/>
    <script src="<%=Const.STATIC_JS_PATH%>/checklist/checkList.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/common/popUp.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/common/checkPopUp.js"></script>
</head>
<body>
<%
    String planNo = (String) request.getAttribute("planNo");
    List<CheckListVO> togetherCheckList = (List<CheckListVO>) request.getAttribute("togetherCheckList");
    List<CheckListVO> myCheckList = (List<CheckListVO>) request.getAttribute("myCheckList");

    MemberDTO memberInfo = null;
    session = request.getSession();
    if (session != null) {
        memberInfo = (MemberDTO) session.getAttribute(Const.MEMBER_INFO_SESSION);
    }
%>

<% if (memberInfo != null) { %>
<input type=hidden id="sessionMemberNo" value=<%=memberInfo.getMemberNo()%>>
<% } %>

<div class="checklist_wrap">
    <div class="checklist_title_wrap">
        <img class="icon_arrow_left" id="icon_arrow_left" src="<%=Const.STATIC_IMG_PATH%>/common/icon_arrow_left.png"
             onclick="history.back()"/>
        <div class="checklist_cancel" id="checklist_cancel" style="display: none">취소</div>
        <div class="checklist_title">CheckList</div>
        <div class="checklist_edit" id="checklist_edit" style="display: none">편집</div>
        <div class="checklist_save" id="checklist_save" style="display: none">완료</div>
    </div>
    <div class="checklist_list_wrap">
        <div class="checklist_tab_menu">
            <div class="tab_menu_together tab_select" id="tab_menu_together">Together</div>
            <div class="tab_menu_my" id="tab_menu_my">My</div>
        </div>

        <form id="checkListForm" method="post">
            <% if (planNo != null) { %>
            <input type="text" id="planNo" name="planNo" value=<%=planNo%> hidden>
            <% } %>
            <div class="checklist_tab_list">

                <div class="checklist_together_wrap">
                    <% String mateNo = null; %>
                    <% if (togetherCheckList != null && memberInfo != null) {
                        for (CheckListVO checkListVO : togetherCheckList) {%>
                    <input type="text" id="planLeaderNo" name="planLeaderNo" value=<%=checkListVO.getPlanLeaderNo()%> hidden>

                    <% if (Const.Y.equals(checkListVO.getCheckYn()) && !checkListVO.getCheckMemberNo().equals(mateNo)) {
                        mateNo = checkListVO.getCheckMemberNo(); %>
                        <div class="checklist_divi_nickname"><%=checkListVO.getCheckMemberNickName()%></div>
                    <% } %>

                    <div class="checklist_item_wrap">
                        <input type="checkbox" name="checklistItemName" id="checklistItemName<%=checkListVO.getMaterialNo()%>" class="checkbox"
                            <% if (Const.Y.equals(checkListVO.getCheckYn())) { %> checked <% } %>
                                value="<%=mateNo%>" onclick='clickCheckbox("<%=checkListVO.getMaterialNo()%>", "<%=checkListVO.getCheckListTypeCode()%>")'>
                        <label for="checklistItemName<%=checkListVO.getMaterialNo()%>"></label>
                        <div class="checklist_item_no" hidden><%=checkListVO.getMaterialNo()%></div>
                        <div class="checklist_item_name"><%=checkListVO.getMaterialName()%></div>
                        <input type="text" name="checkListType" value=<%=checkListVO.getCheckListTypeCode()%> hidden>
                        <img class="icon_list_delete_circle" src="<%=Const.STATIC_IMG_PATH%>/checklist/icon_list_delete_circle.png" style="display:none;"/>
                    </div>
                    <% }
                    }%>
                    <div class="checklist_add_item_wrap">
                        <img class="icon_plus_circle" src="<%=Const.STATIC_IMG_PATH%>/plans/icon_plus_circle.png"/>
                        <input type="text" name="materialName" class="input_checklist_item"
                               placeholder="추가">
                        <div class="checklist_add_item_btn"
                             onclick='addMaterial(this, "<%=ConstCode.CHECKLIST_TYPE_CODE_TOGETHER%>")'>등록
                        </div>
                    </div>
                </div>

                <div class="checklist_my_wrap" style="display: none;">
                    <% if (myCheckList != null) {
                        for (CheckListVO checkListVO : myCheckList) { %>
                    <div class="checklist_item_wrap">
                        <input type="checkbox" name="checklistItemName" id="checklistItemName<%=checkListVO.getMaterialNo()%>" class="checkbox"
                            <% if (Const.Y.equals(checkListVO.getCheckYn())) { %> checked <% } %> value="<%=mateNo%>"
                               onclick='clickCheckbox("<%=checkListVO.getMaterialNo()%>", "<%=checkListVO.getCheckListTypeCode()%>")'>
                        <label for="checklistItemName<%=checkListVO.getMaterialNo()%>"></label>
                        <div class="checklist_item_no" hidden><%=checkListVO.getMaterialNo()%></div>
                        <div class="checklist_item_name"><%=checkListVO.getMaterialName()%></div>
                        <input type="text" name="checkListType" value=<%=checkListVO.getCheckListTypeCode()%> hidden>
                        <img class="icon_list_delete_circle" src="<%=Const.STATIC_IMG_PATH%>/checklist/icon_list_delete_circle.png" style="display:none;"/>
                    </div>
                    <% }
                    }%>

                    <div class="checklist_add_item_wrap">
                        <img class="icon_plus_circle" src="<%=Const.STATIC_IMG_PATH%>/plans/icon_plus_circle.png"/>
                        <input type="text" name="materialName" class="input_checklist_item" placeholder="추가">
                        <div class="checklist_add_item_btn"
                             onclick='addMaterial(this, "<%=ConstCode.CHECKLIST_TYPE_CODE_MY%>")'>등록
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>