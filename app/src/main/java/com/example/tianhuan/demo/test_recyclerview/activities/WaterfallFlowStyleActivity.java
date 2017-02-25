package com.example.tianhuan.demo.test_recyclerview.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.tianhuan.demo.R;
import com.example.tianhuan.demo.test_recyclerview.adapters.WaterfallFlowStyleAdpter;
import com.example.tianhuan.demo.test_recyclerview.utils.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianhuan on 17-2-25.
 */

public class WaterfallFlowStyleActivity extends Activity {

    private RecyclerView mRecyclerView;
    private List<String> data;
    private WaterfallFlowStyleAdpter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_waterfall_flow);

        mRecyclerView = (RecyclerView) findViewById(R.id.waterfall_flow_recyclerview);
        initData();
        mAdapter = new WaterfallFlowStyleAdpter(this, data);
        //mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);

    }

    private void initData() {
        data = new ArrayList<>();
        for(int i = 'A'; i< 'z'; i++){
            data.add((char)i + "");
        }
    }
}
