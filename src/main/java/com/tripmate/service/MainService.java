package com.tripmate.service;

import com.tripmate.domain.PlanBasicInfoVO;
import com.tripmate.domain.PopularPlanVO;
import com.tripmate.domain.ResponseWrapper;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MainService {
    @GET("v1/plans/popular-plan")
    Call<ResponseWrapper<PopularPlanVO>> searchPopularPlanList();
    @GET("v1/plans/popular-plan/{memberNo}")
    Call<ResponseWrapper<PopularPlanVO>> searchPopularPlanList(@Path("memberNo") String memberNo);
    @GET("v1/plans/user-recommendation/{memberNo}")
    Call <ResponseWrapper<PlanBasicInfoVO>> searchUserRecommendationPlanList(@Path("memberNo") String memberNo);
    @GET("v1/plans/user-recommendation")
    Call <ResponseWrapper<PlanBasicInfoVO>> searchRecommendationPlanList();
}
