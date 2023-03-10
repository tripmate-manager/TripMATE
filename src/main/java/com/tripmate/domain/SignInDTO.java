package com.tripmate.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SignInDTO {

    private int memberNo;

    @NotBlank(message = "아이디를 입력해주세요.")
    @Pattern(regexp = "^[0-9a-zA-Z]{5,20}$", message = "영문, 숫자로 이루어진 5자 ~ 20자의 아이디만 입력 가능합니다.")
    private String memberId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String memberPassword;

    @Email
    private String email;

    @Pattern(regexp = "^[1234]0$", message = "회원상태코드는 10, 20, 30, 40만 입력 가능합니다.")
    private String memberStatusCode;
}
