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
public class PostDTO {
    @NotBlank(message = "플랜 번호를 입력해주세요.")
    private String planNo;

    @NotBlank(message = "회원 번호를 입력해주세요.")
    private String memberNo;

    @NotBlank(message = "위시리스트 게시글 타입을 입력해주세요.")
    @Pattern(regexp = "^[1239]0$", message = "게시글 타입코드는 10, 20, 30, 90만 입력 가능합니다.")
    private String postTypeCode;

    @NotBlank(message = "플랜 본문 내용을 입력해주세요.")
    private String postContents;

    @NotBlank(message = "플랜 제목(장소명)을 입력해주세요.")
    private String postTitle;

    private String spotAddress;

    private String spotLongitude;

    private String spotLatitude;

    private String informationUrl;

    private String amount;

    private String businessHours;

    private String mainMenu;

    private String remark;
}
