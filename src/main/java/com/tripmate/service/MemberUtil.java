package com.tripmate.service;

import com.tripmate.domain.MemberDTO;
import com.tripmate.domain.ResponseWrapper;
import com.tripmate.entity.ApiResultEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberUtil {
    public static boolean isDuplicate(final String duplicationMemberInfo, String duplicationCheckType) {
        ResponseWrapper<Boolean> response = MemberRepository.getInstance().isDuplicate(duplicationMemberInfo, duplicationCheckType);
        boolean result = false;

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

    public static int signUp(final MemberDTO memberDTO) {
        ResponseWrapper<Integer> response = MemberRepository.getInstance().signUp(memberDTO);
        int result = 0;

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
