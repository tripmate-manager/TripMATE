package com.tripmate.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReviewImageDTO {
    private String reviewNo;

    private String reviewImageNo;

    private String reviewImageName;

    private String reviewImagePath;

    private String reviewImageVolume;
}
