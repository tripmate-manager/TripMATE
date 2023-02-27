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
public class PlanVO {
    private int planNo;

    private String planTitle;

    private String planDescription;

    private String publicYn;

    private String tripStartDate;

    private String tripEndDate;

    private int tripTerm;

    private int likeRegistrationCnt;

    private int achieveRate;

    private int views;

    private double reviewAverageScore;

    private String leadYn;

    private String registrationDateTime;

    private List<PlanAddressVO> planAddressList;

    private List<PlanAttributeVO> planAttributeList;
}