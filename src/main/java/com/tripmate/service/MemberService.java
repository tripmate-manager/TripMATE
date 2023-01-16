package com.tripmate.service;

import com.tripmate.domain.MemberDTO;
import com.tripmate.domain.ResponseWrapper;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MemberService {
    @POST("v1/members")
    Call<ResponseWrapper<Integer>> signUp(@Body MemberDTO memberDTO);

    @GET("v1/members/duplication/memberId")
    Call<ResponseWrapper<Boolean>> isIdDuplicate(@Query("memberId") String memberId);

    @GET("v1/members/duplication/nickName")
    Call<ResponseWrapper<Boolean>> isNickNameDuplicate(@Query("nickName") String nickName);

    @GET("v1/members/duplication/email")
    Call<ResponseWrapper<Boolean>> isEmailDuplicate(@Query("email") String email);
}
