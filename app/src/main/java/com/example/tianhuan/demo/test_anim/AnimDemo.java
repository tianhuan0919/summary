package com.example.tianhuan.demo.test_anim;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.LinearLayout;

import com.example.tianhuan.demo.R;
import com.google.zxing.client.android.util.DensityUtil;

/**
 * Created by tianhuan on 17-2-20.
 */

public class AnimDemo extends Activity implements View.OnClickListener{

    private LinearLayout mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_anim_demo);

        mView = (LinearLayout) findViewById(R.id.tv_scan_prompt);
        findViewById(R.id.start_anim).setOnClickListener(this);
        findViewById(R.id.property_anim_demo_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.start_anim:
                // 指定动画重复的周期数
                //.setInterpolator(new CycleInterpolator(5))
                // 动画结束后会有回弹的效果
                //setInterpolator(new BounceInterpolator())
                // 平移效果
                //setInterpolator(new LinearInterpolator())
                //  加速效果  速度由慢变快
                // AccelerateInterpolator
                //  减速效果  速度由快变慢
                // DecelerateInterpolator
                mView.animate()
                        .setDuration(3000)
                        .translationY(DensityUtil.dip2px(AnimDemo.this, 50))
                        .setInterpolator(new AnticipateOvershootInterpolator())
                        .start();
                break;
            case R.id.property_anim_demo_btn:
                startActivity(new Intent(AnimDemo.this, PropertyAnimDemo.class));
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mView != null){
            if(mView.animate() != null){
                mView.animate().cancel();
            }
        }
        mView.setAnimation(null);
    }
}
