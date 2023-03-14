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
public class PlanMateDTO {
    @NotBlank(message = "플랜번호를 입력해주세요.")
    private String planNo;

    @NotBlank(message = "회원번호를 입력해주세요.")
    private String memberNo;

    @Pattern(regexp = "^[YN]$", message = "플랜 리더(생성자) 여부는 Y, N만 입력 가능합니다.")
    private String leadYn;
}

