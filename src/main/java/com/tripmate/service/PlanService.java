package com.tripmate.service;

import com.tripmate.domain.PlanAddressVO;
import com.tripmate.domain.PlanAttributeVO;
import com.tripmate.domain.PlanDTO;
import com.tripmate.domain.PlanMateVO;
import com.tripmate.domain.PlanVO;
import com.tripmate.domain.ResponseWrapper;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PlanService {
    @GET("v1/plans/plan-attributes")
    Call<ResponseWrapper<PlanAttributeVO>> selectPlanAttributeList(@Query("attributeTypeCode") String attributeTypeCode);
    @GET("v1/plans/trip-address")
    Call<ResponseWrapper<PlanAddressVO>> selectAddressList();
    @GET("v1/plans/trip-address/{sidoName}")
    Call<ResponseWrapper<PlanAddressVO>> selectAddressList(@Path("sidoName") String sidoName);
    @POST("v1/plans/create-plan")
    Call<ResponseWrapper<Integer>> createPlan(@Body PlanDTO planDTO);
    @GET("v1/plans/{memberNo}")
    Call<ResponseWrapper<PlanVO>> searchMemberPlanList(@Path("memberNo") String memberNo);
    @GET("v1/plans/plan-detail/{planNo}")
    Call<ResponseWrapper<PlanVO>> getPlanInfo(@Path("planNo") String planNo);
    @GET("v1/plans/plan-mate/{planNo}")
    Call <ResponseWrapper<PlanMateVO>> searchPlanMateList(@Path("planNo") String planNo);
    @PUT("v1/plans/{planNo}")
    Call<ResponseWrapper<Boolean>> updatePlan(@Path("planNo") String planNo, @Body PlanDTO planDTO);
}
