package com.tripmate.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AccountVO {

    private String accountNo;

    private String dailyPlanNo;

    private String postNo;

    private String postTypeCode;

    private String accountName;

    private String sortSequence;

    private String amount;

}
