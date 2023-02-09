package com.tripmate.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MypageDTO {
    private int memberNo;

    @NotBlank(message = "닉네임을 입력해주세요.")
    @Pattern(regexp = "^[가-힣0-9a-zA-Z~!@#$%^&*()_+|<>?:{}]{1,20}$", message = "한영자, 숫자, 기호 입력 가능하며 1~20자의 닉네임만 입력 가능합니다.")
    private String nickName;

    @NotBlank(message = "생년월일을 입력해주세요.")
    @Pattern(regexp = "^(19|20)\\d{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])$", message="생년월일은 'yyyyMMdd' 형태로 입력가능합니다.")
    private String birthDay;

    @NotBlank(message = "성별코드를 입력해주세요.")
    @Pattern(regexp = "^[12]0$", message = "성별코드는 10, 20만 입력 가능랍니다.")
    private String genderCode;
}
