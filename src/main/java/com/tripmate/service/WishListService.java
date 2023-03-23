package com.tripmate.service;

import com.tripmate.domain.PostDTO;
import com.tripmate.domain.PostVO;
import com.tripmate.domain.ResponseWrapper;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WishListService {
    @POST("v1/wishlist/create-post")
    Call<ResponseWrapper<String>> createPost(@Body PostDTO postDTO);
    @GET("v1/wishlist/{planNo}")
    Call<ResponseWrapper<PostVO>> searchWishList(@Path("planNo") String planNo);
}
