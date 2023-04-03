<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/include/commonImport.jsp" %>

<html>
<head>
    <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/common/bottomNavigationMenu.css"/>
</head>
<body>
<div class="bottom_navigation_wrap">
    <div class="bottom_navigation_menu_wrap" id="bottom_menu_home">
        <img class="icon_menu" id="icon_menu_home" src="<%=Const.STATIC_IMG_PATH%>/common/icon_menu_home.png"/>
        <img class="icon_menu" id="icon_menu_home_choice" src="<%=Const.STATIC_IMG_PATH%>/common/icon_menu_home_choice.png" style="display: none"/>
        <div class="bottom_menu_text">home</div>
    </div>
    <div class="bottom_navigation_menu_wrap" id="bottom_menu_wishlist">
        <img class="icon_menu" id="icon_menu_wishlist" src="<%=Const.STATIC_IMG_PATH%>/common/icon_menu_wishlist.png"/>
        <img class="icon_menu" id="icon_menu_wishlist_choice" src="<%=Const.STATIC_IMG_PATH%>/common/icon_menu_wishlist_choice.png" style="display: none"/>
        <div class="bottom_menu_text">wishList</div>
    </div>
    <div class="bottom_navigation_menu_wrap" id="bottom_menu_checklist">
        <img class="icon_menu" id="icon_menu_checklist" src="<%=Const.STATIC_IMG_PATH%>/common/icon_menu_checklist.png"/>
        <img class="icon_menu" id="icon_menu_checklist_choice" src="<%=Const.STATIC_IMG_PATH%>/common/icon_menu_checklist_choice.png" style="display: none"/>
        <div class="bottom_menu_text">checkList</div>
    </div>
    <div class="bottom_navigation_menu_wrap" id="bottom_menu_account">
        <img class="icon_menu" id="icon_menu_account" src="<%=Const.STATIC_IMG_PATH%>/common/icon_menu_account.png"/>
        <img class="icon_menu" id="icon_menu_account_choice" src="<%=Const.STATIC_IMG_PATH%>/common/icon_menu_account_choice.png" style="display: none"/>
        <div class="bottom_menu_text">account</div>
    </div>
</div>
</body>
</html>