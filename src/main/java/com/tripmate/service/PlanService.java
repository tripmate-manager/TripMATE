package com.tripmate.service;

import com.tripmate.domain.CreatePlanDTO;
import com.tripmate.domain.PlanAddressVO;
import com.tripmate.domain.PlanAttributeVO;
import com.tripmate.domain.PlanVO;
import com.tripmate.domain.ResponseWrapper;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PlanService {
    @GET("v1/plans/plan-attributes")
    Call<ResponseWrapper<PlanAttributeVO>> selectPlanAttributeList(@Query("attributeTypeCode") String attributeTypeCode);
    @GET("v1/plans/trip-address")
    Call<ResponseWrapper<PlanAddressVO>> selectAddressList();
    @GET("v1/plans/trip-address/{sidoName}")
    Call<ResponseWrapper<PlanAddressVO>> selectAddressList(@Path("sidoName") String sidoName);
    @POST("v1/plans")
    Call<ResponseWrapper<Boolean>> createPlan(@Body CreatePlanDTO createPlanDTO);
    @GET("v1/plans/")
    Call<ResponseWrapper<PlanVO>> searchMemberPlanList(@Query("memberNo") String memberNo);
}
