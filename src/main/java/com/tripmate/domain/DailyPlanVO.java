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
public class DailyPlanVO {

    @NotBlank
    private String dailyPlanNo;

    @NotBlank
    private String planNo;

    @NotBlank
    private String postNo;

    @NotBlank
    private String dayGroupNo;

    @NotBlank
    private String dailyPlanDateTime;

    @NotBlank
    private String notificationYn;

    private String reviewAverageScore;

    private String postTypeCode;

    @NotBlank
    private String postTitle;

    @NotBlank
    private String postContents;

    private String spotAddress;

    @NotBlank
    private String postRegistrationNo;
}
