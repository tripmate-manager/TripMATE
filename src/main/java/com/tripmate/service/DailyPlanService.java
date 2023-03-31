package com.tripmate.service;

import com.tripmate.domain.DailyPlanDTO;
import com.tripmate.domain.ResponseWrapper;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface DailyPlanService {
    @POST("v1/dailyplans/dailyplan")
    Call<ResponseWrapper<Boolean>> insertDailyPlan(@Body DailyPlanDTO dailyPlanDTO);
}