package com.example.retrofitassignment.network;

import com.example.retrofitassignment.model.House;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitApiService {
    String BASE_URL = "https://mars.udacity.com/";

    @GET("realestate")
    Call<List<House>> getData();

    @GET("realestate")
    Call<List<House>> getDataForType(@Query("filter") String type);
}
