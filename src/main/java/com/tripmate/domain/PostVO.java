package com.tripmate.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostVO {
    @NotBlank(message = "게시글 번호를 입력해주세요.")
    private String postNo;

    @NotBlank(message = "플랜 번호를 입력해주세요.")
    private String planNo;

    @NotBlank(message = "위시리스트 게시글 타입을 입력해주세요.")
    @Pattern(regexp = "^[1239]0$", message = "게시글 타입코드는 10, 20, 30, 90만 입력 가능합니다.")
    private String postTypeCode;

    @NotBlank(message = "플랜 본문 내용을 입력해주세요.")
    @Size(max = 20)
    private String postContents;

    private String mappingYn;

    private String spotAddress;

    private String spotLongitude;

    private String spotLatitude;

    private String informationUrl;

    private String amount;

    private String businessHours;

    private String mainMenu;

    private String postTitle;

    private String remark;

    private String registrationNo;

    private String registrationDateTime;

    private int commentCnt;
}
