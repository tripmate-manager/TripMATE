package com.tripmate.domain;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class CommonDetailCodeVO {
    private String commonCode;
    private String commonDetailCode;
    private String commonDetailName;
    private String groupCode1;
    private String groupCode2;
    private String groupCode3;
    private long sortSeq;
    private String useYn;
}