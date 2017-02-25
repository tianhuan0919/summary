package com.example.tianhuan.demo.test_recyclerview.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tianhuan.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianhuan on 17-2-25.
 */

public class WaterfallFlowStyleAdpter extends RecyclerView.Adapter<WaterfallFlowStyleAdpter.MyViewHolder> {

    private Context mCtx;
    private List<String> mData;
    private List<Integer> mHeights;

    public WaterfallFlowStyleAdpter(Context mCtx, List<String> mData) {
        this.mCtx = mCtx;
        this.mData = mData;

        mHeights = new ArrayList<>();
        for (int i = 0; i < mData.size(); i++)
        {
            mHeights.add( (int) (100 + Math.random() * 300));
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mCtx).inflate(R.layout.item_waterfall_flow, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        ViewGroup.LayoutParams lp = holder.tv.getLayoutParams();
        lp.height = mHeights.get(position);

        holder.tv.setLayoutParams(lp);
        holder.tv.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends ViewHolder {
        private TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.id_num);
        }
    }
}
