package com.tripmate.service;

import com.tripmate.domain.ChangePasswordDTO;
import com.tripmate.domain.MemberDTO;
import com.tripmate.domain.MemberMailDTO;
import com.tripmate.domain.ResponseWrapper;
import com.tripmate.domain.SignInDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface MemberService {
    @POST("v1/members")
    Call<ResponseWrapper<Integer>> signUp(@Body MemberDTO memberDTO);

    @GET("v1/members/duplication/id")
    Call<ResponseWrapper<Boolean>> isIdDuplicate(@Query("memberId") String memberId);

    @GET("v1/members/duplication/nick-name")
    Call<ResponseWrapper<Boolean>> isNickNameDuplicate(@Query("nickName") String nickName);

    @GET("v1/members/duplication/email")
    Call<ResponseWrapper<Boolean>> isEmailDuplicate(@Query("email") String email);

    @GET("v1/members/certification-mail-confirm")
    Call<ResponseWrapper> certificationMailConfirm(@Query("memberId") String memberId, @Query("key") String key, @Query("mailTypeCode") String mailTypeCode);

    @POST("v1/members/sign-in")
    Call<ResponseWrapper<MemberDTO>> signIn(@Body SignInDTO signInDTO);

    @GET("v1/members/find-id")
    Call<ResponseWrapper<String>> findId(@Query("memberName") String memberName, @Query("email") String email);

    @POST("v1/members/send-mail/certification")
    Call<ResponseWrapper<Boolean>> sendCertificationMail(@Body MemberMailDTO memberMailDTO);

    @POST("v1/members/send-mail/password")
    Call<ResponseWrapper<Boolean>> sendPasswordMail(@Body MemberMailDTO memberMailDTO);

    @PUT("v1/members/change-password")
    Call<ResponseWrapper<Boolean>> changePassword(@Body ChangePasswordDTO changePasswordDTO);

}
