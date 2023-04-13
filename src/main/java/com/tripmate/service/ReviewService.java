package com.tripmate.service;

import com.tripmate.domain.DeleteReviewDTO;
import com.tripmate.domain.ResponseWrapper;
import com.tripmate.domain.ReviewDTO;
import com.tripmate.domain.ReviewVO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ReviewService {
    @POST("v1/reviews/{dailyPlanNo}")
    Call<ResponseWrapper<String>> insertReview(@Path("dailyPlanNo") String dailyPlanNo, @Body ReviewDTO reviewDTO);
    @GET("v1/reviews/{dailyPlanNo}")
    Call<ResponseWrapper<ReviewVO>> searchReviewList(@Path("dailyPlanNo") String dailyPlanNo);
    @POST("v1/reviews/delete-review")
    Call<ResponseWrapper<Boolean>> deleteReview(@Body DeleteReviewDTO deleteReviewDTO);
}
