package com.example.lianxitest.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.MapView;
import com.example.lianxitest.R;
import com.example.lianxitest.adapter.TabPagerAdapter;
import com.example.lianxitest.bean.GoodsBean;
import com.example.lianxitest.presenter.Presenter;
import com.example.lianxitest.view.MainActivity_View;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView text;
    private MapView mMapView = null;
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();

    private Button dingwei;
    private TabLayout tabLayout;
    private TabLayout TabLayout;
    private ViewPager MyViewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }



    //获取控件
    private void initView() {
        dingwei = findViewById(R.id.dingwei);
        tabLayout = findViewById(R.id.TabLayout);

        dingwei.setOnClickListener(this);
        TabLayout = (TabLayout) findViewById(R.id.TabLayout);
        MyViewPager = (ViewPager) findViewById(R.id.MyViewPager);



    }

    private void initMap() {
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        //注册监听函数
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);
        //可选，是否需要位置描述信息，默认为不需要，即参数为false
        //如果开发者需要获得当前点的位置信息，此处必须为true
        option.setIsNeedLocationDescribe(true);
        //可选，设置是否需要地址信息，默认不需要
        option.setIsNeedAddress(true);
        //可选，默认false,设置是否使用gps
        option.setOpenGps(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setLocationNotify(true);
        mLocationClient.setLocOption(option);
        mLocationClient.start();


    }

    //联动
    private void initEvent() {
        TabPagerAdapter tabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager());
        MyViewPager.setAdapter(tabPagerAdapter);
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.setupWithViewPager(MyViewPager);
        tabLayout.getTabAt(0).setText("综合");
        tabLayout.getTabAt(1).setText("销量");
        tabLayout.getTabAt(2).setText("价格");
        tabLayout.getTabAt(3).setText("筛选");
    }




    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取地址相关的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明
            String locationDescribe = location.getLocationDescribe();    //获取位置描述信息
            String addr = location.getAddrStr();    //获取详细地址信息

        }
    }

    //点击定位
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dingwei:
                //定位的方法
                initMap();
                startActivity(new Intent(MainActivity.this, MapActivity.class));
                break;

        }
    }
}
