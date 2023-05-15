package com.tripmate.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PopularPlanVO {

    private int planNo;

    private String planTitle;

    private String leaderNickName;

    private int planLikeCnt;

}
