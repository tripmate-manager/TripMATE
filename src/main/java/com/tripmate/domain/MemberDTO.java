package com.tripmate.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MemberDTO {
    private int memberNo;

    @NotBlank(message = "아이디를 입력해주세요.")
    @Pattern(regexp="^[0-9a-zA-Z]{5,20}$",
            message = "영문, 숫자로 이루어진 5자 ~ 20자의 아이디만 입력 가능합니다.")
    private String memberId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "영문, 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호만 입력 가능합니다.")
    private String memberPassword;

    @NotBlank(message = "이름을 입력해주세요.")
    @Pattern(regexp = "^[가-힣]{2,20}|[a-zA-Z]{2,20}$", message = "영문이나 한글로 이루어진 2~20자의 이름만 입력 가능합니다.")
    private String memberName;

    @NotBlank(message = "닉네임을 입력해주세요.")
    @Pattern(regexp = "^[가-힣0-9a-zA-Z~!@#$%^&*()_+|<>?:{}]{1,20}$", message = "한영자 숫자 기호 입력 가능하며 1~20자의 닉네임만 입력 가능합니다.")
    private String nickName;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;

    @NotBlank(message = "성별코드를 입력해주세요.")
    @Pattern(regexp = "^[12]0$", message = "성별코드는 10, 20만 입력 가능합니다.")
    private String genderCode;

    @NotBlank(message = "생년월일을 입력해주세요.")
    @Pattern(regexp = "^(19|20)\\d{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])$", message="생년월일은 'yyyyMMdd' 형태로 입력가능합니다.")
    private String birthDay;

    @Pattern(regexp = "^[123]0$", message = "회원상태코드는 10, 20, 30만 입력 가능합니다.")
    private String memberStatusCode;

    private int signInRequestCnt;
}

