package com.tripmate.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MailDTO {
    @NonNull
    private int memberNo;

    @NonNull
    private String email;
}
