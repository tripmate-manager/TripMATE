package com.tripmate.service;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TestService {
    @GET("v1/test/dao")
    Call<Object> getTest();
}
