package com.tripmate.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SearchMemberDTO {
    @NotBlank(message = "검색 구분 코드를 입력해주세요.")
    @Pattern(regexp = "^[123]0$", message = "검색구분코드는 10, 20, 30만 입력 가능합니다.")
    private String searchMemberDiviCode;

    @NotBlank(message = "검색어를 입력해주세요.")
    private String searchKeyword;
}