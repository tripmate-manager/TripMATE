package com.tripmate.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountBookDTO {

    private String accountNo;

    @NotBlank(message = "회원 번호를 입력해주세요.")
    private String memberNo;

    @NotBlank(message = "플랜 번호를 입력해주세요.")
    private String planNo;

    @NotBlank(message = "게시글 타입 코드를 입력해주세요.")
    private String postTypeCode;

    @NotBlank(message = "여행가계부 항목명을 입력해주세요.")
    private String accountName;

    @NotBlank(message = "Day 그룹을 입력해주세요.")
    private String dayGroup;

    @NotBlank(message = "여행가계부 금액(사용 경비)을 입력해주세요.")
    private String amount;

}
