package com.example.retrofitassignment.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.retrofitassignment.Constants;
import com.example.retrofitassignment.R;
import com.example.retrofitassignment.databinding.ActivityDetailBinding;
import com.example.retrofitassignment.model.House;
import com.example.retrofitassignment.viewmodel.MainViewModel;
import com.squareup.picasso.Picasso;


public class DetailActivity extends AppCompatActivity {


    private static final String TAG = "DetailActivity";
    private ActivityDetailBinding mBinding;
    private MainViewModel mMainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        mBinding.executePendingBindings();
        mMainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mBinding.setLifecycleOwner(this);
        setUpObservable();
        getData();
    }

    @SuppressLint("SetTextI18n")
    private void getData() {
        Intent intent = getIntent();
        House house = (House) intent.getSerializableExtra(Constants.KEY_DATA_HOUSE);
        Uri url = Uri.parse(house.getSrcImage()).buildUpon().scheme("https").build();
        Picasso.with(this).load(url).placeholder(R.drawable.loading_img).into(mBinding.imgHouseDetail);
        mBinding.tvPrice.setText("Price: " + "$" + house.getPrice());
        mBinding.tvType.setText("Type: " + house.getType());
        mBinding.tvId.setText("Id:" + house.getID());
    }

    private void setUpObservable() {

    }
}