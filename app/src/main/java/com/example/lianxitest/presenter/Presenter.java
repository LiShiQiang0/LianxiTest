package com.example.lianxitest.presenter;

import android.util.Log;

import com.example.lianxitest.bean.GoodsBean;
import com.example.lianxitest.model.Model;
import com.example.lianxitest.ui.MainActivity;
import com.example.lianxitest.view.MainActivity_View;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2018/12/25/025
 * author:李世强(北冥有鱼)
 * function:
 */
public class Presenter {

    private final Model model;
    private final WeakReference<MainActivity_View> mainActivity_viewWeakReference;

    public Presenter(MainActivity_View view) {
        mainActivity_viewWeakReference = new WeakReference<>(view);
        model = new Model();
    }


    public void request(Map<String,String> map) {
        model.getGoodsBean(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GoodsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GoodsBean goodsBean) {
                        List<GoodsBean.DataBean> data = goodsBean.getData();
                        mainActivity_viewWeakReference.get().onSuccess(data);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
