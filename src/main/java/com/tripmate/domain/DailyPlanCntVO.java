package com.tripmate.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DailyPlanCntVO {
    @NotBlank
    private String dayGroup;

    @NotBlank
    private String dailyPlanCnt;
}
