package com.tripmate.service;

import com.tripmate.domain.PlanBasicInfoVO;
import com.tripmate.domain.PopularPlanVO;
import com.tripmate.domain.ResponseWrapper;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MainService {
    @GET("v1/plans/popular-plan")
    Call<ResponseWrapper<PopularPlanVO>> searchPopularPlanList();
    @GET("v1/plans/popular-plan/{memberNo}")
    Call<ResponseWrapper<PopularPlanVO>> searchPopularPlanList(@Path("memberNo") String memberNo);
    @GET("v1/search-plan/user-recommendation")
    Call<ResponseWrapper<PlanBasicInfoVO>> searchUserRecommendationPlanList(@Query("memberNo") String memberNo);
}
