package com.example.lianxitest.constant;

import com.example.lianxitest.bean.GoodsBean;
import com.example.lianxitest.bean.XingQingBean;

import java.util.Map;

import io.reactivex.Observable;

import retrofit2.http.GET;

import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * date:2018/12/25/025
 * author:李世强(北冥有鱼)
 * function:
 */
public interface ServiceContent {
    /* product/searchProducts?keywords=笔记本&page=1*/
    @GET("product/searchProducts")
    Observable<GoodsBean> getGoodsBean(@QueryMap Map<String,String> map);

    @GET("product/getProductDetail")
    Observable<XingQingBean> getXingQingBean(@Query("pid") String pid);
}
