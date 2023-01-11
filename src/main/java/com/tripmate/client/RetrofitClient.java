package com.tripmate.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tripmate.entity.Const;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static <S> S getApiService(Class<S> serviceClass) {
        return getInstance().create(serviceClass);
    }

    private static Retrofit getInstance() {
        Gson gson = new GsonBuilder().setLenient().create();

        return new Retrofit.Builder().baseUrl("http://127.0.0.1:8888/api/").addConverterFactory(GsonConverterFactory.create(gson)).build();
    }
}
