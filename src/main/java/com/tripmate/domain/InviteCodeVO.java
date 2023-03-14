package com.tripmate.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class InviteCodeVO {
    private int inviteCodeNo;

    private String planNo;

    private String inviteTypeCode;

    private String inviteCode;

    private String inviteCodeExpireDateTime;
}
