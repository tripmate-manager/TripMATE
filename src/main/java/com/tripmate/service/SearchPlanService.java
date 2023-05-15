package com.tripmate.service;

import com.tripmate.domain.PlanBasicInfoVO;
import com.tripmate.domain.ResponseWrapper;
import com.tripmate.domain.SearchAttributeDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SearchPlanService {
    @GET("v1/search-plan/keyword")
    Call<ResponseWrapper<PlanBasicInfoVO>> searchPlanByKeyword(@Query("memberNo") String memberNo, @Query("keyword") String keyword);
    @POST("v1/search-plan/attribute")
    Call<ResponseWrapper<PlanBasicInfoVO>> searchPlanByAttribute(@Body SearchAttributeDTO searchAttributeDTO);
    @GET("v1/search-plan/popular-keyword")
    Call<ResponseWrapper<String>> searchPopularSearchKeyword();
    @GET("v1/search-plan/popular-hashtag")
    Call<ResponseWrapper<String>> searchPopularHashtag();
}
