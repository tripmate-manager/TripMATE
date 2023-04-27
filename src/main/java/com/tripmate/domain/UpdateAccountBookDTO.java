package com.tripmate.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateAccountBookDTO {

    @NotBlank(message = "여행가계부 항목 번호를 입력해주세요.")
    private String accountNo;

    @NotBlank(message = "회원 번호를 입력해주세요.")
    private String memberNo;

    private String planNo;

    private String postNo;

    private String sortSequence;

    private String amount;

}
