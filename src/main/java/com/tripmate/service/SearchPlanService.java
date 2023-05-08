package com.tripmate.service;

import com.tripmate.domain.ResponseWrapper;
import com.tripmate.domain.SearchAttributeDTO;
import com.tripmate.domain.SearchPlanResultVO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SearchPlanService {
    @GET("v1/search-plan/keyword")
    Call<ResponseWrapper<SearchPlanResultVO>> searchPlanByKeyword(@Query("memberNo") String memberNo, @Query("keyword") String keyword);
    @POST("v1/search-plan/attribute")
    Call<ResponseWrapper<SearchPlanResultVO>> searchPlanByAttribute(@Body SearchAttributeDTO searchAttributeDTO);
}
