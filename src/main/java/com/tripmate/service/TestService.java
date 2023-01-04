package com.tripmate.service;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TestService {
    @GET("test/dao")
    Call<Object> getTest();
}
