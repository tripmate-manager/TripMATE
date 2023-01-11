package com.tripmate.service;

import com.tripmate.domain.CommonDetailCodeVO;
import com.tripmate.domain.ResponseWrapper;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface CommonCodeService {
    @GET("v1/common/codes/{commonCode}")
    Call<ResponseWrapper<CommonDetailCodeVO>> searchCommonDetailCodeList(@Path("commonCode") String commCd);

    @GET("v1/common/codes/{commonCode}/{commonDetailCode}")
    Call<ResponseWrapper<CommonDetailCodeVO>> getCommonDetailCode(@Path("commonCode") String commCd,
                                                 @Path("commonDetailCode") String commDtlCd);
}