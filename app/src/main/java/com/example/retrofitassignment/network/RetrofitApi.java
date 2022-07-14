package com.example.retrofitassignment.network;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RetrofitApi {
    public RetrofitApiService retrofitApiService;
    private static RetrofitApi instance;



    private RetrofitApi() {
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
//
//        OkHttpClient httpClient = new OkHttpClient();


        Retrofit retrofit = new Retrofit
                .Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl(RetrofitApiService.BASE_URL)
                //.client(client)
                .build();

        retrofitApiService = retrofit.create(RetrofitApiService.class);
    }

    public static RetrofitApi getInstance() {
        synchronized (RetrofitApi.class) {
            if (instance == null) {
                return new RetrofitApi();
            }
        }
        return instance;
    }
}
