package com.tripmate.service;

import com.tripmate.client.RetrofitClient;
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

    public ResponseWrapper<Boolean> getDuplicationCheckYn(final String duplicationMemberInfo, String duplicationCheckType) {
        ResponseWrapper<Boolean> result = null;

        try {
            Call<ResponseWrapper<Boolean>> data = null;

            if (ConstCode.DUPLICATION_CHECK_MEMBER_ID.equals(duplicationCheckType)) {
                data = RetrofitClient.getApiService(MemberService.class).getIdDuplicationCheckYn(duplicationMemberInfo);
            } else if (ConstCode.DUPLICATION_CHECK_NICK_NAME.equals(duplicationCheckType)) {
                data = RetrofitClient.getApiService(MemberService.class).getNickNameDuplicationCheckYn(duplicationMemberInfo);
            } else if (ConstCode.DUPLICATION_CHECK_EMAIL.equals(duplicationCheckType)) {
                data = RetrofitClient.getApiService(MemberService.class).getEmailDuplicationCheckYn(duplicationMemberInfo);
            }

            result = data.clone().execute().body();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return result;
    }
}
