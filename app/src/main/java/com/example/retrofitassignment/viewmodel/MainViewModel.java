package com.example.retrofitassignment.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.retrofitassignment.model.House;
import com.example.retrofitassignment.network.RetrofitApi;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends AndroidViewModel {
    public static final String TAG = "MainViewModel";
    private MutableLiveData<List<House>> mListHouse = new MutableLiveData<>();
    private final RetrofitApi mRetrofitApi;

    public MainViewModel(@NonNull Application application) {
        super(application);
        mRetrofitApi = RetrofitApi.getInstance();
    }

    public MutableLiveData<List<House>> getListHouse() {
        return mListHouse;
    }

    public void requestListHouse() {
        Log.d(TAG, "onFailure: ");
        mRetrofitApi.retrofitApiService.getData().enqueue(new Callback<List<House>>() {
            @Override
            public void onResponse(Call<List<House>> call, Response<List<House>> response) {
                Log.d(TAG, "onFailure: ");
                    mListHouse.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<House>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public void requestListHouseForType(String type) {
        mRetrofitApi.retrofitApiService.getDataForType(type).enqueue(new Callback<List<House>>() {
            @Override
            public void onResponse(Call<List<House>> call, Response<List<House>> response) {
                mListHouse.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<House>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
