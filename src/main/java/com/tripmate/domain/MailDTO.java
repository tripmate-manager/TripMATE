package com.tripmate.domain;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MailDTO {
    @NonNull
    private int memberNo;

    @NonNull
    private String email;

    @NonNull
    private String mailTypeCode;
}
