package com.tripmate.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SignInDTO {
    @NonNull
    @NotBlank(message = "아이디를 입력해주세요.")
    @Pattern(regexp = "^[0-9a-zA-Z]{5,20}$", message = "영문, 숫자로 이루어진 5자 ~ 20자의 아이디만 입력 가능합니다.")
    private String memberId;

    @NonNull
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String memberPassword;

    @Email
    private String email;
}
