package com.tripmate.service;

import com.tripmate.domain.CommonDetailCodeVO;
import com.tripmate.domain.ResponseWrapper;
import com.tripmate.entity.ApiResultEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;

@Slf4j
public class CodeUtil {

    public static List<CommonDetailCodeVO> searchCommonDetailCodeList(final String commCd) {
        ResponseWrapper<CommonDetailCodeVO> response = CodeRepository.getInstance().searchCommonDetailCodeList(commCd);

        if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
            return response.getData();
        } else {
            log.warn(response.getCode() + " : " + response.getMessage());

            return Collections.emptyList();
        }
    }

    public static CommonDetailCodeVO getCommonDetailCode(final String commCd, final String commDtlCd) {
        ResponseWrapper<CommonDetailCodeVO> response = CodeRepository.getInstance().getCommonDetailCode(commCd, commDtlCd);
        CommonDetailCodeVO result = null;

        if (ApiResultEnum.SUCCESS.getCode().equals(response.getCode())) {
            if (response.getData().size() != 1) {
                log.warn("response's data size is not 1");
            }

            result = response.getData().get(0);
        } else {
            log.warn(response.getCode() + " : " + response.getMessage());
        }

        return result;
    }

}