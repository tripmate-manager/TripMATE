package com.tripmate.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CheckListVO {
    private String planNo;

    private String materialNo;

    private String checkListTypeCode;

    private String materialName;

    private String checkYn;

    private String checkMemberNo;

    private String planLeaderNo;

    private String checkMemberNickName;
}