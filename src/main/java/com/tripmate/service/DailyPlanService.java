package com.tripmate.service;

import com.tripmate.domain.DailyPlanCntVO;
import com.tripmate.domain.DailyPlanDTO;
import com.tripmate.domain.DailyPlanVO;
import com.tripmate.domain.DeleteDailyPlanDTO;
import com.tripmate.domain.ResponseWrapper;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DailyPlanService {
    @POST("v1/dailyplans/dailyplan")
    Call<ResponseWrapper<Boolean>> insertDailyPlan(@Body DailyPlanDTO dailyPlanDTO);
    @POST("v1/dailyplans/dailyplan/{dailyPlanNo}")
    Call<ResponseWrapper<Boolean>> deleteDailyPlan(@Path("dailyPlanNo") String dailyPlanNo, @Body DeleteDailyPlanDTO deleteDailyPlanDTO);
    @GET("v1/dailyplans/dailyplan-count/{planNo}")
    Call<ResponseWrapper<DailyPlanCntVO>> searchDailyPlanCntByDay(@Path("planNo") String planNo);
    @GET("v1/dailyplans/dailyplan/{planNo}")
    Call<ResponseWrapper<DailyPlanVO>> searchDailyPlanListByDay(@Path("planNo") String planNo, @Query("memberNo") String memberNo, @Query("dayGroup") String dayGroup);
    @DELETE("v1/dailyplans/dailyplan/notification/{dailyPlanNo}")
    Call<ResponseWrapper<Boolean>> deleteDailyPlanNotification(@Path("dailyPlanNo") String dailyPlanNo, @Query("memberNo") String memberNo);
}