package com.example.retrofitassignment.adapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitassignment.R;
import com.example.retrofitassignment.databinding.ItemHouseBinding;
import com.example.retrofitassignment.model.House;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class HouseAdapter extends RecyclerView.Adapter<HouseAdapter.ViewHolder> {
    private List<House> mListData;
    private final Context mContext;
    private CallBack mListener;

    @SuppressLint("NotifyDataSetChanged")
    public void setListData(List<House> mListData) {
        this.mListData = mListData;
        notifyDataSetChanged();
    }


    public HouseAdapter(Context context, CallBack listener) {
        this.mContext = context;
        this.mListener = listener;
        mListData = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemHouseBinding itemView =
                ItemHouseBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        House house = mListData.get(position);
        Uri url = Uri.parse(house.getSrcImage()).buildUpon().scheme("https").build();
        Picasso.with(mContext).load(url).placeholder(R.drawable.loading_img).into(holder.mBinding.imgItemHouse);
        holder.itemView.setOnClickListener(v -> {
            mListener.onClickHouse(house);
        });
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemHouseBinding mBinding;

        public ViewHolder(@NonNull ItemHouseBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

    public interface CallBack {
        void onClickHouse(House house);
    }

}
