package com.tripmate.service;

import com.tripmate.domain.MemberDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MemberService {
    @POST("v1/member")
    Call<Object> insertMemberInfo(@Body MemberDTO memberDTO);
}
