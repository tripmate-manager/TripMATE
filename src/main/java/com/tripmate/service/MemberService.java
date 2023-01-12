package com.tripmate.service;

import com.tripmate.domain.MemberDTO;
import com.tripmate.domain.ResponseWrapper;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MemberService {
    @POST("v1/member")
    Call<Object> insertMemberInfo(@Body MemberDTO memberDTO);

    @GET("v1/member/duplication/nickName")
    Call<ResponseWrapper<Boolean>> getIdDuplicationCheckYn(@Query("memberId") String memberId);

    @GET("v1/member/duplication/nickName")
    Call<ResponseWrapper<Boolean>> getNickNameDuplicationCheckYn(@Query("nickName") String nickName);

    @GET("v1/member/duplication/email")
    Call<ResponseWrapper<Boolean>> getEmailDuplicationCheckYn(@Query("email") String email);
}
