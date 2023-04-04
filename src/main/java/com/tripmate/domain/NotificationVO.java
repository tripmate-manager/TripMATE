package com.tripmate.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class NotificationVO {
    private int notificationNo;

    private int planNo;

    private String planTitle;

    private String leaderName;

    private int dailyPlanNo;

    private String postTitle;

    private String notificationTypeCode;

    private int senderNo;

    private String senderName;

    private int receiverNo;

    private String readDateTime;

    private String notificationDateTime;

    private String useYn;
}