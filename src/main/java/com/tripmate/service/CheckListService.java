package com.tripmate.service;

import com.tripmate.domain.CheckListDTO;
import com.tripmate.domain.CheckListVO;
import com.tripmate.domain.DeleteCheckListDTO;
import com.tripmate.domain.ResponseWrapper;
import com.tripmate.domain.UpdateCheckYnDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CheckListService {
    @POST("v1/checklist/{planNo}")
    Call<ResponseWrapper<Boolean>> insertCheckList(@Path("planNo") String planNo, @Body CheckListDTO checkListDTO);
    @GET("v1/checklist/{planNo}")
    Call<ResponseWrapper<CheckListVO>> searchTogetherCheckList(@Path("planNo") String planNo);
    @GET("v1/checklist/{planNo}/{memberNo}")
    Call<ResponseWrapper<CheckListVO>> searchMyCheckList(@Path("planNo") String planNo, @Path(value = "memberNo") String memberNo);
    @POST("v1/checklist/delete-checklist")
    Call<ResponseWrapper<Boolean>> deleteCheckList(@Body DeleteCheckListDTO deleteCheckListDTO);
    @PUT("v1/checklist/{materialNo}")
    Call<ResponseWrapper<Boolean>> updateCheckYn(@Path("materialNo") String materialNo, @Body UpdateCheckYnDTO updateCheckYnDTO);
}