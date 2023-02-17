package com.tripmate.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PlanAddressVO {
    private int planNo;

    private int addressNo;

    private String sidoName;

    private String sigunguName;
}
