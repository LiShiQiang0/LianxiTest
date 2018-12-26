package com.example.lianxitest.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.lianxitest.ui.fragment.PriceFragment;
import com.example.lianxitest.ui.fragment.ShaiXuanFragment;
import com.example.lianxitest.ui.fragment.XiaoLiangFragment;
import com.example.lianxitest.ui.fragment.ZongHeFragment;

/**
 * date:2018/12/25/025
 * author:李世强(北冥有鱼)
 * function:
 */
public class TabPagerAdapter extends FragmentPagerAdapter {
    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new ZongHeFragment();
            case 1:
                return new XiaoLiangFragment();
            case 2:
                return new PriceFragment();
            case 3:
                return new ShaiXuanFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
