package com.example.lianxitest.model;

import com.example.lianxitest.bean.GoodsBean;
import com.example.lianxitest.bean.XingQingBean;
import com.example.lianxitest.constant.ServiceContent;
import com.example.lianxitest.utils.RetrofitUtils;

import java.util.Map;

import io.reactivex.Observable;

/**
 * date:2018/12/25/025
 * author:李世强(北冥有鱼)
 * function:
 */
public class Model {

    public Observable<GoodsBean> getGoodsBean(Map<String,String> map){
        return RetrofitUtils.getInstance().create(ServiceContent.class).getGoodsBean(map);
    }

    public Observable<XingQingBean> getXingQingBean(String pid){
        return RetrofitUtils.getInstance().create(ServiceContent.class).getXingQingBean(pid);
    }

}
