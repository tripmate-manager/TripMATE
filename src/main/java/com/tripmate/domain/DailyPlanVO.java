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
public class DailyPlanVO {

    private String planNo;

    private String dayGroup;

    private String achieveRate;

    private List<DailyPlanItemVO> dailyPlanItemList;

}
