package com.example.retrofitassignment.model;

import com.squareup.moshi.Json;

import java.io.Serializable;

public class House implements Serializable {
    private @Json(name = "id")
    String mID;
    private @Json(name = "price")
    int mPrice;
    private @Json(name = "type")
    String mType;
    private @Json(name = "img_src")
    String mSrcImage;

    public String getID() {
        return mID;
    }

    public int getPrice() {
        return mPrice;
    }

    public String getType() {
        return mType;
    }

    public String getSrcImage() {
        return mSrcImage;
    }
}
