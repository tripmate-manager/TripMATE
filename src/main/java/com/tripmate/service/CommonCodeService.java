package com.tripmate.service;

import com.tripmate.domain.CommonDetailCodeVO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface CommonCodeService {
    @GET("v1/common/codes/{commonCode}")
    Call<List<CommonDetailCodeVO>> searchCommonDetailCodeList(@Path("commonCode") String commCd);

    @GET("v1/common/codes/{commonCode}/{commonDetailCode}")
    Call<CommonDetailCodeVO> getCommonDetailCode(@Path("commonCode") String commCd,
                                                 @Path("commonDetailCode") String commDtlCd);
}