package com.tripmate.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ApiResultEnum {
    SUCCESS("0000", "success"),
    UNKNOWN("9999", "요청 처리중 오류가 발생했습니다. 잠시 후 다시 시도하세요.")
    ;

    private final String code;
    private final String message;
}
