package com.tripmate.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MemberMailDTO {

    private int memberNo;

    @NotBlank(message = "메일수신자 주소를 입력하세요")
    @Email
    private String to;

    @Pattern(regexp="^[0-9a-zA-Z]{5,20}$",
            message = "영문, 숫자로 이루어진 5자 ~ 20자의 아이디만 입력 가능합니다.")
    private String memberId;

    @Size(max = 100, message = "100자 이하인 값만 입력 가능합니다.")
    private String key;

    @Pattern(regexp = "^[123]0$", message = "메일인증타입코드는 10, 20, 30만 입력 가능합니다. (10: 회원가입 인증, 20: 이메일 변경, 30: 임시비밀번호 발급)")
    private String mailTypeCode;
}
