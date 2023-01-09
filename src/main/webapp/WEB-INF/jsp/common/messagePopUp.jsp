<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8" />
    <link rel="icon" href="/favicon.ico" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta name="theme-color" content="#000000" />
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noto+Sans%3A500%2C600"/>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro%3A500%2C600"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common/messagePopUp.css"/>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
</head>
<body>
<div class="popup__wrap" style="display: none">
    <div class="popup_message"></div>
    <div class="popup_close_btn">확인</div>
</div>
</body>
<script>
    $(".popup_close_btn").on('click', function () {
        $(".popup__wrap").hide();
    });

    function popupOpen(message) {
        $(".popup__wrap").css("position", "absolute");
        $(".popup__wrap").css("top", (($(window).height() - $(".popup__wrap").outerHeight()) / 3) + $(window).scrollTop());
        $(".popup__wrap").css("left", (($(window).width() - $(".popup__wrap").outerWidth()) / 2) + $(window).scrollLeft());
        $(".popup_message").text(message);
        $(".popup__wrap").show();
    }
</script>
</html>

