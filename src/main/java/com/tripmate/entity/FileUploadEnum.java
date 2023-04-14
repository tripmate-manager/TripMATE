package com.tripmate.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum FileUploadEnum {
    FILE_UPLOAD_EXCEPTION("1000", "파일 업로드 처리 중 오류가 발생하였습니다."),
    FILE_SIZE_EXCEPTION("1001", "5MB 이상의 파일은 업로드 불가합니다."),
    FILE_EXTENSION_EXCEPTION("1002", "지원하지 않는 확장자입니다.")
    ;

    private final String code;
    private final String message;
}
