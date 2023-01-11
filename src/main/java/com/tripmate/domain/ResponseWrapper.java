package com.tripmate.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@ToString
@Getter
public class ResponseWrapper<T> {
    private String code;
    private String message;
    private List<T> data;
}
