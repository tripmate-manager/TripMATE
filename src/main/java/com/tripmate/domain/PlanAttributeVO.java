package com.tripmate.domain;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@ToString
public class PlanAttributeVO {
    private int planNo;

    private int attributeNo;

    @NotBlank(message = "속성명을 입력해주세요.")
    private String attributeName;

    @Pattern(regexp = "^[12]0$", message = "속성타입코드는 10, 20만 입력 가능합니다.")
    private String attributeTypeCode;
}
