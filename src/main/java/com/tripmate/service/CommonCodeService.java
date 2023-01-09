package com.tripmate.service;

import com.tripmate.domain.CommonDetailCodeVO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface CommonCodeService {
    @GET("v1/common/codeList")
    Call<List<CommonDetailCodeVO>> searchCommonDetailCodeList(@Query("commonCode") String commCd);

    @GET("v1/common/code")
    Call<CommonDetailCodeVO> getCommonDetailCode(@Query("commonCode") String commCd,
                                                 @Query("commonDetailCode") String commDtlCd);
}