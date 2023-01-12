package com.tripmate.service;

import com.tripmate.client.RetrofitClient;
import com.tripmate.domain.MemberDTO;
import com.tripmate.domain.ResponseWrapper;
import com.tripmate.entity.ConstCode;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Call;

import java.io.IOException;

@Slf4j
public class MemberRepository {
    private static class Singleton {
        private static final MemberRepository instance = new MemberRepository();
    }

    public static MemberRepository getInstance() {
        return MemberRepository.Singleton.instance;
    }

    public ResponseWrapper<Boolean> isDuplicate(final String duplicationMemberInfo, String duplicationCheckType) {
        ResponseWrapper<Boolean> result = null;

        try {
            Call<ResponseWrapper<Boolean>> data = null;

            if (ConstCode.DUPLICATION_CHECK_MEMBER_ID.equals(duplicationCheckType)) {
                data = RetrofitClient.getApiService(MemberService.class).isIdDuplicate(duplicationMemberInfo);
            } else if (ConstCode.DUPLICATION_CHECK_NICK_NAME.equals(duplicationCheckType)) {
                data = RetrofitClient.getApiService(MemberService.class).isNickNameDuplicate(duplicationMemberInfo);
            } else if (ConstCode.DUPLICATION_CHECK_EMAIL.equals(duplicationCheckType)) {
                data = RetrofitClient.getApiService(MemberService.class).isEmailDuplicate(duplicationMemberInfo);
            }

            result = data.clone().execute().body();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return result;
    }

    public ResponseWrapper<Integer> signUp(final MemberDTO memberDTO) {
        ResponseWrapper<Integer> result = null;

        try {
            Call<ResponseWrapper<Integer>> data = RetrofitClient.getApiService(MemberService.class).signUp(memberDTO);

            result = data.clone().execute().body();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return result;
    }

    public ResponseWrapper sendSignUpEmail(final String duplicationMemberInfo, String duplicationCheckType) {
        ResponseWrapper<Boolean> result = null;

        try {
            Call<ResponseWrapper<Boolean>> data = null;

            if (ConstCode.DUPLICATION_CHECK_MEMBER_ID.equals(duplicationCheckType)) {
                data = RetrofitClient.getApiService(MemberService.class).isIdDuplicate(duplicationMemberInfo);
            } else if (ConstCode.DUPLICATION_CHECK_NICK_NAME.equals(duplicationCheckType)) {
                data = RetrofitClient.getApiService(MemberService.class).isNickNameDuplicate(duplicationMemberInfo);
            } else if (ConstCode.DUPLICATION_CHECK_EMAIL.equals(duplicationCheckType)) {
                data = RetrofitClient.getApiService(MemberService.class).isEmailDuplicate(duplicationMemberInfo);
            }

            result = data.clone().execute().body();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return result;
    }
}
