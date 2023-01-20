package com.tripmate.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tripmate.entity.Const;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

public class RetrofitClient {
    public static <S> S getApiService(Class<S> serviceClass) {
        return getInstance().create(serviceClass);
    }

    private static Retrofit getInstance() {
        Gson gson = new GsonBuilder().setLenient().create();

        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(1, TimeUnit.SECONDS)
                                                              .readTimeout(30, TimeUnit.SECONDS)
                                                              .writeTimeout(15, TimeUnit.SECONDS)
                                                              .build();

        return new Retrofit.Builder().client(okHttpClient)
                                     .baseUrl(Const.API_URL)
                                     .addConverterFactory(GsonConverterFactory.create(gson))
                                     .build();
    }
}
