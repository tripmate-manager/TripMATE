package com.tripmate.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tripmate.service.TestService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "http://local.tripmate.com:8888/api/";

    public static TestService getApiService() {
        return getInstance().create(TestService.class);
    }

    private static Retrofit getInstance() {
        Gson gson = new GsonBuilder().setLenient().create();

        return new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
    }
}
