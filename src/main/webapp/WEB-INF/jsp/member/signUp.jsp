<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/messagePopUp.jsp" %>

<html>

<head>
    <meta charset="utf-8"/>
    <link rel="icon" href="/favicon.ico"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta name="theme-color" content="#000000"/>
    <title>SignUp</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noto+Sans%3A500%2C600"/>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro%3A500%2C600"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/member/signUp.css"/>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
</head>

<body>

<div class="signup__wrap">
    <div class="signup_title">회원가입</div>
    <form name="signupForm" id="signupForm" method="post">
        <div class="signup_form">
            <div class="signup_form_item">
                <div class="signup_subtitle">아이디</div>
                <div class="signup_form_input">
                    <input type="text" name="memberId" id="memberId" class="memberId" placeholder="5-20자의 영문, 숫자, 기호">
                </div>
                <div class="signup_duplicate" id="signup_duplicate_id">중복확인</div>
            </div>
            <div class="signup_form_item">
                <div class="signup_subtitle">비밀번호</div>
                <div class="signup_form_input_2">
                    <input type="password" name="memberPassword" id="memberPassword" class="memberPassword"
                           placeholder="8-20자 이내 영문, 숫자, 기호 중 2가지 이상 조합">
                </div>
            </div>
            <div class="signup_form_item">
                <div class="signup_subtitle">비밀번호 확인</div>
                <div class="signup_form_input_2">
                    <input type="password" name="checkmemberPassword" id="checkmemberPassword" class="checkmemberPassword">
                    <div class="signup_form_message" id="checkmemberPasswordMessage" style="display: none;">비밀번호 값이 일치하지
                        않습니다.
                    </div>
                </div>
            </div>
            <div class="signup_form_item">
                <div class="signup_subtitle">이름</div>
                <div class="signup_form_input_2">
                    <input type="text" name="memberName" id="memberName" class="memberName">
                </div>
            </div>
            <div class="signup_form_item">
                <div class="signup_subtitle">닉네임</div>
                <div class="signup_form_input">
                    <input type="text" name="nickName" id="nickName" class="nickName">
                </div>
                <div class="signup_duplicate" id="signup_duplicate_nick_name">중복확인</div>
            </div>
            <div class="signup_form_item">
                <div class="signup_subtitle">이메일</div>
                <div class="signup_form_input">
                    <input type="text" name="email" id="email" class="email"
                           placeholder="example@email.com">
                    <div class="signup_form_message" id="checkemailMessage" style="display: none">잘못된 이메일 형식입니다.
                    </div>
                </div>
                <div class="signup_duplicate" id="signup_duplicate_email">중복확인</div>
            </div>
            <div class="signup_form_item">
                <div class="signup_subtitle">생년월일</div>
                <div class="signup_form_input_2">
                    <input type="number" name="birthDay" id="birthDay" class="birthDay" placeholder="YYMMDD">
                </div>
            </div>
            <div class="signup_form_gender">
                <div class="signup_subtitle">성별</div>
                <div class="btn-toggle">
                    <input type="text" name="genderCode" id="genderCode" value="10" hidden>
                    <button class="btn btn-default" type="button">남</button>
                    <button class="btn btn-primary active" type="button">여</button>
                </div>
            </div>
        </div>
        <div class="signup_complete">회원가입</div>
    </form>
</div>

<script>
    $(function () {

        $("#email").on('keyup', function () {
            const inputEmail = $(this).val();
            const regExp = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.[a-zA-Z]{2,4}$/;

            if (!regExp.test(inputEmail)) {
                $("#checkemailMessage").show();
                return false;
            } else {
                $("#checkemailMessage").hide();
            }
            return true;
        });

        $("#checkmemberPassword, #memberPassword").on('keyup', function () {

            if ($("#checkmemberPassword").val() !== $("#memberPassword").val()) {
                $("#checkmemberPasswordMessage").show();
                return false;
            } else {
                $("#checkmemberPasswordMessage").hide();
            }
            return true;
        });


        $(".btn-toggle").on('click', function () {
            const genderCd = $(this).find("#genderCode");
            $(this).find('.btn').toggleClass('active');

            if (genderCd.val() === '10') {
                $(this).find(".btn").toggleClass('btn-primary');
                genderCd.val('20');
            } else {
                genderCd.val('10');
            }

            $(this).find(".btn").toggleClass('btn-default');
        });

        function popupOpen(message) {
            $(".popup__wrap").css("position", "absolute");
            $(".popup__wrap").css("top", (($(window).height() - $(".popup__wrap").outerHeight()) / 3) + $(window).scrollTop());
            $(".popup__wrap").css("left", (($(window).width() - $(".popup__wrap").outerWidth()) / 2) + $(window).scrollLeft());
            $(".popup_message").text(message);
            $(".popup__wrap").show();
        }

        $(".signup_complete").on('click', function () {
            const inputId = $('#memberId').val()
            const inputPwd = $('#memberPassword').val()
            const inputCheckPwd = $('#checkmemberPassword').val()
            const inputmemberName = $('#memberName').val()
            const inputNickNm = $('#nickName').val()
            const inputEmail = $('#email').val()
            const inputBday = $('#birthDay').val()

            if (inputId === '' || inputPwd === '' || inputCheckPwd === '' || inputmemberName === ''
                || inputNickNm === '' || inputEmail || inputBday === '') {

                popupOpen('필수 항목을 입력해 주세요.');

                return false;
            }

            $("#signupForm").attr("action", "/member/callapi-signup.trip").submit();
        });
    });
</script>
</body>
</html>