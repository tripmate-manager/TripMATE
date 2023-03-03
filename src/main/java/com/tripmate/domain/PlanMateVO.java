package com.tripmate.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PlanMateVO {
    @NotBlank(message = "플랜번호를 입력해주세요.")
    private int planNo;

    @NotBlank(message = "회원번호를 입력해주세요.")
    private int memberNo;

    @NotBlank(message = "플랜리더여부를 입력해주세요.")
    @Pattern(regexp = "^[YN]$", message = "플랜 리더(생성자) 여부는 Y, N만 입력 가능합니다.")
    private String leadYn;

    private String memberId;

    private String memberName;

    private String nickName;
}