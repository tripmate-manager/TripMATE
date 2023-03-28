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
public class DeleteCommentDTO {
    @NotBlank(message = "댓글 번호를 입력해주세요.")
    private String commentNo;

    @NotBlank(message = "댓글 작성자의 회원번호를 입력해주세요.")
    private String commenterNo;
}