package com.example.tianhuan.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.view.Window;

/**
 * Created by tianhuan on 17-2-18.
 */

public class ViewStubDemo extends Activity implements View.OnClickListener {

    private ViewStub mViewStub;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.ac_viewstub_demo);

        initView();
    }

    private void initView() {
        mViewStub = (ViewStub) findViewById(R.id.viewstub_view);

        findViewById(R.id.open_detail_info).setOnClickListener(this);
        findViewById(R.id.close_detail_info).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.open_detail_info:
                /*if(view == null){
                    view = mViewStub.inflate();
                } else {
                    view.setVisibility(View.VISIBLE);
                }*/
                mViewStub.setVisibility(View.VISIBLE);
                break;
            case R.id.close_detail_info:
                mViewStub.setVisibility(View.GONE);
                break;
        }
    }
}
