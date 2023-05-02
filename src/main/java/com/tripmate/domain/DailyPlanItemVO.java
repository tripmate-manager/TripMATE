package com.tripmate.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DailyPlanItemVO {

    private String dailyPlanNo;

    private String postNo;

    private String dailyPlanDateTime;

    private String notificationYn;

    private String reviewAverageScore;

    private String postTypeCode;

    private String postTitle;

    private String postContents;

    private String spotAddress;

    private String registrationNo;

}
