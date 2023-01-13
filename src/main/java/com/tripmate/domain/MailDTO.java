package com.tripmate.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MailDTO {
    private int memberNo;

    @NonNull
    private String email;

    @NonNull
    private String mailTypeCode;
}
