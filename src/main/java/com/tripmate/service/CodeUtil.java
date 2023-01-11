package com.tripmate.service;

import com.tripmate.domain.CommonDetailCodeVO;
import com.tripmate.domain.ResponseWrapper;

import java.util.List;

public class CodeUtil {

    public static ResponseWrapper<CommonDetailCodeVO> searchCommonDetailCodeList(final String commCd) {
        return CodeRepository.getInstance().searchCommonDetailCodeList(commCd);
    }

    public static ResponseWrapper<CommonDetailCodeVO> getCommonDetailCode(final String commCd, final String commDtlCd) {
        return CodeRepository.getInstance().getCommonDetailCode(commCd, commDtlCd);
    }

}