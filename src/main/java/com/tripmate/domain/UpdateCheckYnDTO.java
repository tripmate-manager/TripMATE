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
public class UpdateCheckYnDTO {
    @NotBlank(message = "회원 번호를 입력해주세요.")
    private String memberNo;

    @NotBlank(message = "체크리스트 항목 타입을 입력해주세요.")
    @Pattern(regexp = "^[12]0$", message = "체크리스트 타입코드는 10, 20만 입력 가능합니다.")
    private String checkListTypeCode;

    @NotBlank(message = "체크리스트 항목 번호를 입력해주세요.")
    private String materialNo;
}
