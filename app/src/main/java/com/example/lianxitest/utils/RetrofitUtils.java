package com.example.lianxitest.utils;

import com.example.lianxitest.api.ShopNet;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * date:2018/12/25/025
 * author:李世强(北冥有鱼)
 * function:
 */
public class RetrofitUtils {

    private static RetrofitUtils retrofitUtils;
    private final Retrofit retrofit;

    private RetrofitUtils(){
        retrofit = new Retrofit.Builder()
                .baseUrl(ShopNet.URL_SOU)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static RetrofitUtils getInstance(){
        if(retrofitUtils == null){
            synchronized (RetrofitUtils.class){
                if(retrofitUtils == null){
                    retrofitUtils = new RetrofitUtils();
                }
            }
        }
        return  retrofitUtils;
    }


    public <T> T create(Class<T> clazz){
        return  retrofit.create(clazz);
    }
}
