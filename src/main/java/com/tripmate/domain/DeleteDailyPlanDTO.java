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
public class DeleteDailyPlanDTO {
    @NotBlank(message = "데일리플랜 번호를 입력해주세요.")
    private String dailyPlanNo;

    @NotBlank(message = "회원 번호를 입력해주세요.")
    private String memberNo;
}
