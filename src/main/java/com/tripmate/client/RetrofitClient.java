package com.tripmate.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tripmate.common.properties.PropertiesManager;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = PropertiesManager.getProperty("tripmate-api.url");

    public static <S> S getApiService(Class<S> serviceClass) {
        return getInstance().create(serviceClass);
    }

    private static Retrofit getInstance() {
        Gson gson = new GsonBuilder().setLenient().create();

        return new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
    }
}
