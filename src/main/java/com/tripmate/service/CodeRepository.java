package com.tripmate.service;

import com.tripmate.client.RetrofitClient;
import com.tripmate.domain.CommonDetailCodeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CodeRepository {
    private static Logger log = LoggerFactory.getLogger(CodeRepository.class);

    private static class Singleton {
        private static final CodeRepository instance = new CodeRepository();
    }

    public static CodeRepository getInstance() {
        return Singleton.instance;
    }

    public List<CommonDetailCodeVO> searchCommonDetailCodeList(final String commCd) {
        List<CommonDetailCodeVO> result = new ArrayList<>();
        try {
            Call<List<CommonDetailCodeVO>> data = RetrofitClient.getApiService(CommonCodeService.class).searchCommonDetailCodeList(commCd);
            result = data.clone().execute().body();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    public CommonDetailCodeVO getCommonDetailCode(final String commCd, final String commDtlCd) {
        CommonDetailCodeVO result = new CommonDetailCodeVO();
        try {
            Call<CommonDetailCodeVO> data = RetrofitClient.getApiService(CommonCodeService.class).getCommonDetailCode(commCd, commDtlCd);
            result = data.clone().execute().body();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }
}