<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/include/commonImport.jsp" %>
<%@ include file="/WEB-INF/jsp/common/messagePopUp.jsp" %>

<html>
<head>
    <jsp:include page="../common/include/header.jsp">
        <jsp:param name="title" value="SignUp"/>
    </jsp:include>
    <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/member/signUp.css"/>
    <script src="<%=Const.STATIC_JS_PATH%>/common/validationCheck.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/common/popUp.js"></script>
</head>

<body>
<div class="signup__wrap">
    <div class="signup_title">회원가입</div>
    <form name="signupForm" id="signupForm" method="post">
        <div class="signup_form">
            <div class="signup_form_item">
                <div class="signup_subtitle">아이디</div>
                <div class="signup_form_input">
                    <input type="text" name="memberId" id="memberId" class="memberId" placeholder="5-20자의 영문, 숫자 ">
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
                    <input type="password" name="checkMemberPassword" id="checkMemberPassword"
                           class="checkMemberPassword">
                    <div class="signup_form_message" id="checkMemberPasswordMessage" style="display: none;">비밀번호 값이 일치하지
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
                    <div class="signup_form_message" id="checkEmailMessage" style="display: none">잘못된 이메일 형식입니다.
                    </div>
                </div>
                <div class="signup_duplicate" id="signup_duplicate_email">중복확인</div>
            </div>
            <div class="signup_form_item">
                <div class="signup_subtitle">생년월일</div>
                <div class="signup_form_input_2">
                    <input type="number" name="birthDay" id="birthDay" class="birthDay" placeholder="YYYYMMDD">
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
<%--<jsp:include page="../common/messagePopUp.jsp"/>--%>
<script>
    $(function () {
        let duplicateIdCheckYn = false;
        let duplicateNickNmCheckYn = false;
        let duplicateEmailCheckYn = false;

        $("#memberId").change(function () {
            duplicateIdCheckYn = false;
        });

        $("#memberName").change(function () {
            duplicateNickNmCheckYn = false;
        });

        $("#memberName").change(function () {
            duplicateEmailCheckYn = false;
        });

        $("#email").on('keyup', function () {
            const inputEmail = $(this).val();

            if (!emailValidationCheck(inputEmail)) {
                $("#checkEmailMessage").show();
            } else {
                $("#checkEmailMessage").hide();
            }
        });

        $("#memberPassword, #checkMemberPassword").on('keyup', function () {
            if ($("#checkMemberPassword").val() !== $("#memberPassword").val()) {
                $("#checkMemberPasswordMessage").show();
            } else {
                $("#checkMemberPasswordMessage").hide();
            }
        });


        $(".btn-toggle").on('click', function () {
            const genderCd = $(this).find("#genderCode");
            $(this).find('.btn').toggleClass('active');

            if (genderCd.val() === <%= ConstCode.GENDER_CODE_MALE %>) {
                $(this).find(".btn").toggleClass('btn-primary');
                genderCd.val(<%= ConstCode.GENDER_CODE_FEMALE %>);
            } else {
                genderCd.val(<%= ConstCode.GENDER_CODE_MALE %>);
            }

            $(this).find(".btn").toggleClass('btn-default');
        });

        function signUpFormCheck() {
            if (!blankCheck($("#memberId"))) {
                popUpOpen('아이디를 입력해 주세요.');
                return false;
            }

            if (!blankCheck($("#memberPassword")) || !blankCheck($("#checkMemberPassword"))) {
                popUpOpen('비밀번호를 입력해 주세요.');
                return false;
            }

            if (!blankCheck($("#memberName"))) {
                popUpOpen('이름을 입력해 주세요.');
                return false;
            }

            if (!blankCheck($("#nickName"))) {
                popUpOpen('닉네임을 입력해 주세요.');
                return false;
            }

            if (!blankCheck($("#email"))) {
                popUpOpen('이메일을 입력해 주세요.');
                return false;
            }

            if (!blankCheck($("#birthDay"))) {
                popUpOpen('생년월일을 입력해 주세요.');
                return false;
            }

            return true;
        }

        function signUpValidationCheck() {
            if (!idValidationCheck($("#memberId").val())) {
                popUpOpen('아이디는 영문, 숫자로 이루어진 5~20자의 아이디만 입력 가능합니다.');
                return false;
            }

            if (!passwordValidationCheck($("#memberPassword").val())) {
                popUpOpen('비밀번호는 영문, 숫자, 특수기호가 적어도 1개 이상씩 포함된 8~20자의 비밀번호만 입력 가능합니다.');
                return false;
            }

            if (!nameValidationCheck($("#memberName").val())) {
                popUpOpen('이름은 영문이나 한글로 이루어진 2~20자의 이름만 입력 가능합니다.');
                return false;
            }

            if (!emailValidationCheck($("#email").val())) {
                popUpOpen('이메일이 형식에 맞지 않습니다.');
                return false;
            }

            if (!birthDayValidationCheck($("#birthDay").val())) {
                popUpOpen('생년월일은 YYYYMMDD 형태만 입력 가능합니다.');
                return false;
            }

            return true;
        }

        $("#signup_duplicate_id").on('click', function () {
            $.ajax({
                url: "/member/duplication/memberId.trip",
                type: "get",
                dataType: 'json',
                data: {
                    memberId: $('#memberId').val()
                },
                success: function (result) {
                    if (result == true) {
                        popUpOpen('사용 가능한 아이디입니다.')
                        duplicateIdCheckYn = true;
                    } else {
                        popUpOpen('이미 사용 중인 아이디입니다.')
                        duplicateIdCheckYn = false;
                    }
                },
                error: function (error) {
                }
            })
        });

        $("#signup_duplicate_nick_name").on('click', function () {
            $.ajax({
                url: "/member/duplication/nickName.trip",
                type: "get",
                dataType: 'json',
                data: {
                    nickName: $('#nickName').val()
                },
                success: function (result) {
                    if (result == true) {
                        popUpOpen('사용 가능한 닉네임입니다.')
                        duplicateNickNmCheckYn = true;
                    } else {
                        popUpOpen('이미 사용 중인 닉네임입니다.')
                        duplicateNickNmCheckYn = false;
                    }
                },
                error: function (error) {
                }
            })
        });

        $("#signup_duplicate_email").on('click', function () {
            $.ajax({
                url: "/member/duplication/email.trip",
                type: "get",
                dataType: 'json',
                data: {
                    email: $('#email').val()
                },
                success: function (result) {
                    if (result == true) {
                        popUpOpen('사용 가능한 이메일입니다.')
                        duplicateEmailCheckYn = true;
                    } else {
                        popUpOpen('이미 사용 중인 이메일입니다.')
                        duplicateEmailCheckYn = false;
                    }
                },
                error: function (error) {
                }
            })
        });

        $(".signup_complete").on('click', function () {
            if (signUpFormCheck()) {
                if (signUpValidationCheck()) {

                    if (duplicateIdCheckYn != true) {
                        popUpOpen('아이디 중복확인이 필요합니다.')
                        return false;
                    }

                    if (duplicateNickNmCheckYn != true) {
                        popUpOpen('닉네임 중복확인이 필요합니다.')
                        return false;
                    }

                    if (duplicateEmailCheckYn != true) {
                        popUpOpen('이메일 중복확인이 필요합니다.')
                        return false;
                    }

                    $("#signupForm").attr("action", "/member/signUp.trip").submit();

                    return true;
                }
            }
            return false;
        });
    });
</script>
</body>
</html>