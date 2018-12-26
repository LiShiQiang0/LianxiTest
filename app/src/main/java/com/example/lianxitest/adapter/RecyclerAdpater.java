package com.example.lianxitest.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lianxitest.R;
import com.example.lianxitest.bean.GoodsBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2018/12/25/025
 * author:李世强(北冥有鱼)
 * function:
 */
public class RecyclerAdpater extends RecyclerView.Adapter<RecyclerAdpater.ViewHolder> {

    private List<GoodsBean.DataBean> dataBeanList;
    private Context context;


    public RecyclerAdpater( Context context) {
        this.context = context;
        dataBeanList  = new ArrayList<>();
    }

    public void setData(List<GoodsBean.DataBean> dataBeanLists){
        if(dataBeanLists != null){
            dataBeanList.clear();
            dataBeanList.addAll(dataBeanLists);
            notifyDataSetChanged();
        }
    }


    @NonNull
    @Override
    public RecyclerAdpater.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdpater.ViewHolder viewHolder, int i) {
        viewHolder.mySimpleDraweeView.setImageURI(dataBeanList.get(i).getImages().split("\\|")[0]);
        viewHolder.title.setText(dataBeanList.get(i).getTitle());
        viewHolder.price.setText(dataBeanList.get(i).getPscid()+"");
        final  GoodsBean.DataBean dataBean = dataBeanList.get(i);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerAdpaterListeners.success(dataBean.getPid());
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final TextView price;
        private final SimpleDraweeView mySimpleDraweeView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
             mySimpleDraweeView = itemView.findViewById(R.id.MySimpleDraweeView);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
        }
    }

    public interface RecyclerAdpaterListener{
        void success(int pid);
    }

    private RecyclerAdpaterListener RecyclerAdpaterListeners;

    public void setRecyclerAdpaterListener(RecyclerAdpaterListener RecyclerAdpaterListenerss){
        RecyclerAdpaterListeners = RecyclerAdpaterListenerss;
    }
}
