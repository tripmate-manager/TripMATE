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
public class AccountBookVO {

    private String planNo;

    private String dayGroup;

    private String tripTerm;

    private int dayAmountSum;

    private int planMateAmountSum;

    private int planAmountSum;

    private List<AccountVO> accountList;

}
