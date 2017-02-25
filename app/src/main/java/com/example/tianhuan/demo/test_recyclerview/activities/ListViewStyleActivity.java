package com.example.tianhuan.demo.test_recyclerview.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.tianhuan.demo.R;
import com.example.tianhuan.demo.test_recyclerview.adapters.ListViewStyleAdapter;
import com.example.tianhuan.demo.test_recyclerview.utils.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianhuan on 17-2-22.
 */

public class ListViewStyleActivity extends Activity implements View.OnClickListener{

    private RecyclerView mListStyleRecyclerView;
    private ListViewStyleAdapter mAdapter;
    private List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.ac_listview_style);

        initView();
        initData();
        mAdapter = new ListViewStyleAdapter(this, data);
        //gridview style
        /*mListStyleRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        mListStyleRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));*/

        // listview style
        mListStyleRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mListStyleRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mListStyleRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new ListViewStyleAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(ListViewStyleActivity.this, "click ： " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View v, int position) {
                Toast.makeText(ListViewStyleActivity.this, "long click ： " + position, Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.add_item_btn).setOnClickListener(this);
        findViewById(R.id.remove_item_btn).setOnClickListener(this);
    }

    private void initData() {
        data = new ArrayList<>();
        for(int i = 'A'; i< 'z'; i++){
            data.add((char)i + "");
        }
    }

    private void initView() {
        mListStyleRecyclerView = (RecyclerView) findViewById(R.id.listview_style_recyclerview);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.add_item_btn:
                mAdapter.addData(1);
                break;
            case R.id.remove_item_btn:
                mAdapter.remaveData(1);
                break;
        }

    }
}
