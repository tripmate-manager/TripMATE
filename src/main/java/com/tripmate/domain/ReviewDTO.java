package com.tripmate.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReviewDTO {
    private String reviewNo;

    @NotBlank(message = "회원 번호를 입력해주세요.")
    private String memberNo;

    @NotBlank(message = "데일리플랜 번호를 입력해주세요.")
    private String dailyPlanNo;

    @NotBlank(message = "게시글 타입을 입력해주세요.")
    @Pattern(regexp = "^[1239]0$", message = "게시글 타입코드는 10, 20, 30, 90만 입력 가능합니다.")
    private String postTypeCode;

    private String scoreLocation;

    private String scoreAmount;

    private String scoreAll;

    private String scoreFacility;

    private String scoreSanitary;

    private String scoreService;

    private String scoreFood;

    private String reviewContents;

    private String reviewAverageScore;

    private List<ReviewImageDTO> reviewImageList;
}
