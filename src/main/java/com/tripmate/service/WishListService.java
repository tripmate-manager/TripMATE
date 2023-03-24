package com.tripmate.service;

import com.tripmate.domain.CommentDTO;
import com.tripmate.domain.CommentVO;
import com.tripmate.domain.DeleteCommentDTO;
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
    @GET("v1/wishlist/post/{postNo}")
    Call<ResponseWrapper<PostVO>> getPostInfo(@Path("postNo") String postNo);
    @POST("v1/wishlist/comment")
    Call<ResponseWrapper<String>> createComment(@Body CommentDTO commentDTO);
    @GET("v1/wishlist/comment/{postNo}")
    Call<ResponseWrapper<CommentVO>> searchCommentList(@Path("postNo") String postNo);
    @POST("v1/wishlist/comment/{commentNo}")
    Call<ResponseWrapper<Boolean>> deleteComment(@Path("commentNo") String commentNo, @Body DeleteCommentDTO deleteCommentDTO);
}
