package com.tripmate.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MemberDTO {
    @NonNull
    private String memberPassword;
    @NonNull
    private String memberName;
    @NonNull
    private String nickName;
    @NonNull
    private String email;
    @NonNull
    private String genderCode;
    @NonNull
    private String birthDay;
}

