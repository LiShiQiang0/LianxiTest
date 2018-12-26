package com.example.lianxitest.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lianxitest.R;
import com.example.lianxitest.adapter.ViewPagerAdapter;
import com.example.lianxitest.bean.Messages;
import com.example.lianxitest.bean.XingQingBean;
import com.example.lianxitest.presenter.PresenterTwo;
import com.example.lianxitest.view.XiangQingActivity;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.shareboard.SnsPlatform;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class XiangQiangActivity extends AppCompatActivity implements View.OnClickListener ,XiangQingActivity {
    //A.定义装平台的容器
    public ArrayList<SnsPlatform> platforms = new ArrayList<SnsPlatform>();
    private SHARE_MEDIA[] list = {SHARE_MEDIA.QQ};
    private UMShareAPI mUMShareAPI;
    private ImageView details_headpic;
    private ViewPager details_pager;
    private TextView details_title;
    private TextView details_price;
    private Button details_addcar;
    private PresenterTwo presenterTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang_qiang);
        initView();
        if(!EventBus.getDefault().isRegistered(true)){
            EventBus.getDefault().register(this);
        }

    }


    private void initView() {
        details_headpic = (ImageView) findViewById(R.id.details_headpic);
        details_pager = (ViewPager) findViewById(R.id.details_pager);
        details_title = (TextView) findViewById(R.id.details_title);
        details_price = (TextView) findViewById(R.id.details_price);
        details_addcar = (Button) findViewById(R.id.details_addcar);

        details_addcar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.details_addcar:
                initKKK();
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void success(Messages pid){
        int pid1 = pid.getPid();
        presenterTwo = new PresenterTwo(this);
        Log.e("lsq",pid1+"");
        presenterTwo.request(pid1+"");
    }

    @Override
    public void onSuccess(XingQingBean.DataBean data) {
        String[] split = data.getImages().split("\\|");
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(split, this);
        details_pager.setAdapter(viewPagerAdapter);
        details_title.setText(data.getTitle());
        details_price.setText(data.getPrice()+"");

    }

    private void initKKK() {

        //A.三方平台,添加到遍历的集合中
        initPlatforms();

        //A.获取UM的对象
        mUMShareAPI = UMShareAPI.get(XiangQiangActivity.this);
        //A.获取是否授权
        final boolean isauth = UMShareAPI.get(this).isAuthorize(this, platforms.get(0).mPlatform);

        if (isauth){
            Toast.makeText(XiangQiangActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
            mUMShareAPI.deleteOauth(XiangQiangActivity.this, platforms.get(0).mPlatform,authListener);
        }else{
            mUMShareAPI.doOauthVerify(XiangQiangActivity.this, platforms.get(0).mPlatform,authListener);
        }
        mUMShareAPI.getPlatformInfo(XiangQiangActivity.this, platforms.get(0).mPlatform,authListener);

    }

    //A.
    private void initPlatforms() {
        //A.集合清空
       // platforms.clear();
        //A.通过for循环,把数组数据添加到集合中
        for (SHARE_MEDIA e : list) {
            if (!e.toString().equals(SHARE_MEDIA.GENERIC.toString())) {
                platforms.add(e.toSnsPlatform());
            }
        }
    }

    //A.
    UMAuthListener authListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //授权开始的回调，可以用来处理等待框，或相关的文字提示
        }

        @Override//授权成功时回调
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            //获取用户授权后的信息
            Set<String> strings = data.keySet();
            data.get("profile_image_url");
            String temp="";
            for(String key: strings ){
                temp =temp +key +" :" +data.get(key) +"\n";
            }
            /*tv_result.setText(temp);*/
            Toast.makeText(XiangQiangActivity.this, ""+temp, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(XiangQiangActivity.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();

        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(XiangQiangActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    //A.
    @Override
    protected void onActivityResult ( int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
