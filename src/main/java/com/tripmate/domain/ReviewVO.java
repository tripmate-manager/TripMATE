package com.tripmate.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReviewVO {
    private String reviewNo;

    private String dailyPlanNo;

    private String postTypeCode;

    private String scoreLocation;

    private String scoreAmount;

    private String scoreAll;

    private String scoreFacility;

    private String scoreSanitary;

    private String scoreService;

    private String scoreFood;

    private String reviewContents;

    private String reviewAverageScore;

    private String registrationNo;

    private String nickName;

    private List<ReviewImageDTO> reviewImageList;
}
