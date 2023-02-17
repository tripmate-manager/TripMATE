package com.tripmate.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreatePlanDTO {
    private int memberNo;

    private int planNo;

    @NotBlank(message = "플랜 제목을 입력해주세요.")
    @Size(max = 20)
    private String planTitle;

    @NotBlank(message = "플랜 설명을 입력해주세요.")
    @Size(max = 100)
    private String planDescription;

    @NotBlank(message = "플랜 공개 여부를 입력해주세요.")
    @Pattern(regexp = "^[YN]$", message = "플랜 공개 여부는 Y, N만 입력 가능합니다.")
    private String publicYn;

    @NotBlank(message = "여행 시작 일자를 입력해주세요.")
    @Pattern(regexp = "(19|20)\\d{2}.(0[1-9]|1[012]).(0[1-9]|[12][0-9]|3[01])", message = "여행 시작 일자는 'yyyy.MM.dd' 형태로 입력가능합니다.")
    private String tripStartDate;

    @NotBlank(message = "여행 종료 일자를 입력해주세요.")
    @Pattern(regexp = "(19|20)\\d{2}.(0[1-9]|1[012]).(0[1-9]|[12][0-9]|3[01])", message = "여행 종료 일자는 'yyyy.MM.dd' 형태로 입력가능합니다.")
    private String tripEndDate;

    @NotEmpty(message = "여행지를 입력해주세요.")
    private List<Integer> planAddressList;

    private List<Integer> planThemeList;

    private List<String> planHashtagList;
}