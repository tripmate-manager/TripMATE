package com.tripmate.service;

import com.tripmate.client.RetrofitClient;
import com.tripmate.domain.CommonDetailCodeVO;
import com.tripmate.domain.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Call;

import java.io.IOException;

@Slf4j
public class CodeRepository {
    private static class Singleton {
        private static final CodeRepository instance = new CodeRepository();
    }

    public static CodeRepository getInstance() {
        return Singleton.instance;
    }

    public ResponseWrapper<CommonDetailCodeVO> searchCommonDetailCodeList(final String commCd) {
        ResponseWrapper<CommonDetailCodeVO> result = null;

        try {
            Call<ResponseWrapper<CommonDetailCodeVO>> data = RetrofitClient.getApiService(CommonCodeService.class).searchCommonDetailCodeList(commCd);
            result = data.clone().execute().body();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return result;
    }

    public ResponseWrapper<CommonDetailCodeVO> getCommonDetailCode(final String commCd, final String commDtlCd) {
        ResponseWrapper<CommonDetailCodeVO> result = null;

        try {
            Call<ResponseWrapper<CommonDetailCodeVO>> data = RetrofitClient.getApiService(CommonCodeService.class).getCommonDetailCode(commCd, commDtlCd);
            result = data.clone().execute().body();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return result;
    }
}