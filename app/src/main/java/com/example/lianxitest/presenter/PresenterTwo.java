package com.example.lianxitest.presenter;

import com.example.lianxitest.bean.XingQingBean;
import com.example.lianxitest.model.Model;
import com.example.lianxitest.view.XiangQingActivity;

import java.lang.ref.WeakReference;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2018/12/25/025
 * author:李世强(北冥有鱼)
 * function:
 */
public class PresenterTwo {

    private final Model model;
    private final WeakReference<XiangQingActivity> xiangQingActivityWeakReference;

    public PresenterTwo(XiangQingActivity view) {
        xiangQingActivityWeakReference = new WeakReference<>(view);
        model =new Model();
    }

    public void request(String pid){
        model.getXingQingBean(pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<XingQingBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(XingQingBean xingQingBean) {
                        XingQingBean.DataBean data = xingQingBean.getData();
                        xiangQingActivityWeakReference.get().onSuccess(data);
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
