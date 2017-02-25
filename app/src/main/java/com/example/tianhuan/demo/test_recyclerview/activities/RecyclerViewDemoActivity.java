package com.example.tianhuan.demo.test_recyclerview.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tianhuan.demo.R;

/**
 * Created by tianhuan on 17-2-25.
 */

public class RecyclerViewDemoActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_recyclerview);

        findViewById(R.id.listview_style_btn).setOnClickListener(this);
        findViewById(R.id.waterfall_flow_style_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.listview_style_btn:
                startActivity(new Intent(RecyclerViewDemoActivity.this, ListViewStyleActivity.class));
                break;
            case R.id.waterfall_flow_style_btn:
                startActivity(new Intent(RecyclerViewDemoActivity.this, WaterfallFlowStyleActivity.class));
                break;
        }
    }
}
