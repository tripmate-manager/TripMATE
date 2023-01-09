package com.tripmate.domain;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class CommonDetailCodeVO {
    private String commCd;
    private String commDtlCd;
    private String commDtlNm;
    private String grpCd1;
    private String grpCd2;
    private String grpCd3;
    private long sortSeq;
    private String useYn;
    private long regNo;
    private Date regDtime;
    private long uptNo;
    private Date uptDtime;
}