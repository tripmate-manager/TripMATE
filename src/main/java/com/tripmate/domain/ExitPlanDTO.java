package com.tripmate.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExitPlanDTO {
    private String mateNo;

    @Min(value = 1, message = "회원 번호는 필수이며, 양수만 입력 가능합니다.")
    private String memberNo;

    @Min(value = 1, message = "플랜 번호는 필수이며, 양수만 입력 가능합니다.")
    private String planNo;
}

