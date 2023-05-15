package com.tripmate.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PlanBasicInfoVO {
    private int planNo;

    private String planTitle;

    private String planDescription;

    private String tripStartDate;

    private String tripEndDate;

    private int tripTerm;

    private int likeRegistrationCnt;

    private int achieveRate;

    private int views;

    private double reviewAverageScore;

    private String sidoName;

    private String sigunguName;

    private String leaderNickName;

    private String registrationDateTime;

    private int planLikeCnt;
}

