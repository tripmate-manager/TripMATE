package com.tripmate.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NotificationDTO {

    @NotBlank(message = "플랜 번호를 입력해주세요.")
    private String planNo;

    private String dailyPlanNo;

    @NotBlank(message = "알림타입코드를 입력해주세요.")
    @Pattern(regexp = "^[123]0$", message = "알림타입코드는 10, 20, 30만 입력 가능합니다.")
    private String notificationTypeCode;

    @NotBlank(message = "발신자 회원번호를 입력해주세요.")
    private String senderNo;

    private String receiverNo;

    private String notificationDateTime;
}
