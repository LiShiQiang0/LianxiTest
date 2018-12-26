package com.example.lianxitest.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lianxitest.R;
import com.example.lianxitest.adapter.RecyclerAdpater;
import com.example.lianxitest.bean.GoodsBean;
import com.example.lianxitest.bean.Messages;
import com.example.lianxitest.presenter.Presenter;
import com.example.lianxitest.ui.XiangQiangActivity;
import com.example.lianxitest.view.MainActivity_View;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class XiaoLiangFragment extends Fragment implements View.OnClickListener,MainActivity_View {

    private Presenter presenter;
    private EditText shousuo;
    private Button sou;
    private RecyclerView myRelativeLayout;
    private RecyclerAdpater recyclerAdpater;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_xiao_liang, container, false);
        initView(inflate);
        return inflate;
    }



    private void initView(View inflate) {
        myRelativeLayout = inflate.findViewById(R.id.MyRelativeLayout);
        shousuo = (EditText) inflate.findViewById(R.id.shousuo);
        sou = (Button) inflate.findViewById(R.id.sou);
        sou.setOnClickListener(this);
        myRelativeLayout.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerAdpater = new RecyclerAdpater(getActivity());
        recyclerAdpater.setRecyclerAdpaterListener(new RecyclerAdpater.RecyclerAdpaterListener() {
            @Override
            public void success(int pid) {
                Messages messages = new Messages();
                messages.setPid(pid);
                EventBus.getDefault().postSticky(messages);
                startActivity(new Intent(getActivity(),XiangQiangActivity.class));
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sou:
                initData();
                break;
        }
    }
    /* product/searchProducts?keywords=笔记本&page=1*/
    private void initData() {
        String trim = shousuo.getText().toString().trim();
        presenter = new Presenter(this);
        Map<String, String> map = new HashMap<>();
        map.put("keywords", trim);
        map.put("page", "1");
        presenter.request(map);
    }

    @Override
    public void onSuccess(List<GoodsBean.DataBean> data) {
        recyclerAdpater.setData(data);
        myRelativeLayout.setAdapter(recyclerAdpater);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(presenter != null){
            presenter = null;
        }
    }
}
