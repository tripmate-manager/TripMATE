package com.tripmate.domain;

import lombok.Data;

import java.util.Date;

@Data
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

    @Override
    public String toString() {
        return "CommonDetailCodeVO{" +
            "commCd='" + commCd + '\'' +
            ", commDtlCd='" + commDtlCd + '\'' +
            ", commDtlNm='" + commDtlNm + '\'' +
            ", grpCd1='" + grpCd1 + '\'' +
            ", grpCd2='" + grpCd2 + '\'' +
            ", grpCd3='" + grpCd3 + '\'' +
            ", sortSeq=" + sortSeq +
            ", useYn='" + useYn + '\'' +
            ", regNo=" + regNo +
            ", regDtime=" + regDtime +
            ", uptNo=" + uptNo +
            ", uptDtime=" + uptDtime +
            '}';
    }
}