package com.tripmate.service;

import com.tripmate.domain.CommonDetailCodeVO;

import java.util.List;

public class CodeUtil {

    public static List<CommonDetailCodeVO> searchCommonDetailCodeList(final String commCd) {
        return CodeRepository.getInstance().searchCommonDetailCodeList(commCd);
    }

    public static CommonDetailCodeVO getCommonDetailCode(final String commCd, final String commDtlCd) {
        return CodeRepository.getInstance().getCommonDetailCode(commCd, commDtlCd);
    }

}