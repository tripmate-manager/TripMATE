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
public class CommentVO {
    @NotBlank(message = "댓글 번호를 입력해주세요.")
    private String commentNo;

    @NotBlank(message = "게시글 번호를 입력해주세요.")
    private String postNo;

    @NotBlank(message = "작성자 회원 번호를 입력해주세요.")
    private String commenterNo;

    @NotBlank(message = "작성자 닉네임 번호를 입력해주세요.")
    private String nickName;

    @NotBlank(message = "댓글 그룹 번호를 입력해주세요.")
    private String commentGroupNo;

    @NotBlank(message = "대댓글 그룹 번호를 입력해주세요.")
    private String commentGroupNo2;

    @NotBlank(message = "댓글 depth를 입력해주세요.")
    @Pattern(regexp = "^[123]$", message = "댓글 depth는 1, 2, 3만 입력 가능합니다.")
    private String commentDepth;

    @NotBlank(message = "댓글 내용을 입력해주세요.")
    private String commentText;

    @NotBlank
    private String registrationDateTime;

    @NotBlank
    private String useYn;
}
