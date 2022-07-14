package com.example.retrofitassignment.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.retrofitassignment.Constants;
import com.example.retrofitassignment.R;
import com.example.retrofitassignment.adapter.HouseAdapter;
import com.example.retrofitassignment.databinding.ActivityMainBinding;
import com.example.retrofitassignment.model.House;
import com.example.retrofitassignment.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity implements HouseAdapter.CallBack {


    private static final String TAG = "MainActivity";
    private HouseAdapter mAdapter;
    private ActivityMainBinding mBinding;
    private MainViewModel mMainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.executePendingBindings();
        mMainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mBinding.setLifecycleOwner(this);
        setUpRcv();
        setUpObservable();
        getDataAll();
    }

    private void getDataAll() {
        mMainViewModel.requestListHouse();
    }

    private void setUpRcv() {
        mAdapter = new HouseAdapter(this, this);
        GridLayoutManager layoutManager = new GridLayoutManager(
                this, 2
        );
        mBinding.rcv.setLayoutManager(layoutManager);
        mBinding.rcv.setAdapter(mAdapter);
    }

    private void setUpObservable() {
        mMainViewModel.getListHouse().observe(this, houses -> {
            Log.d(TAG, "setUpObservable: " + houses.size());
            mAdapter.setListData(houses);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option_house, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_type_rent:
                mMainViewModel.requestListHouseForType(Constants.TYPE_RENT);
                break;

            case R.id.menu_type_buy:
                mMainViewModel.requestListHouseForType(Constants.TYPE_BUY);
                break;

            case R.id.menu_type_all:
                mMainViewModel.requestListHouse();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClickHouse(House house) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(Constants.KEY_DATA_HOUSE, house);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBinding = null;
    }
}