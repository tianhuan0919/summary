package com.example.tianhuan.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.tianhuan.demo.test_anim.AnimDemo;
import com.example.tianhuan.demo.test_fresco.activities.BitmapGridViewActivity;
import com.example.tianhuan.demo.test_fresco.activities.CircleFrescoActivity;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.zxing.client.android.CaptureActivity;
import com.google.zxing.client.android.Intents;


public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setViewListener();
    }

    private void setViewListener() {
        findViewById(R.id.circle_fresco_btn).setOnClickListener(this);
        findViewById(R.id.clear_fresco_cache).setOnClickListener(this);
        findViewById(R.id.viewstub_demo).setOnClickListener(this);
        findViewById(R.id.zxing_demo).setOnClickListener(this);
        findViewById(R.id.local_album).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.circle_fresco_btn:
                startActivity(new Intent(MainActivity.this, CircleFrescoActivity.class));
                break;
            case R.id.clear_fresco_cache:
                // 清除内存缓存 + 磁盘缓存
                Fresco.getImagePipeline().clearCaches();
                /*// 清空磁盘缓存
                Fresco.getImagePipeline().clearDiskCaches();
                // 清空内存缓存
                Fresco.getImagePipeline().clearMemoryCaches();*/
                break;
            case R.id.local_album:
                startActivity(new Intent(MainActivity.this, BitmapGridViewActivity.class));
                break;
            case R.id.viewstub_demo:
                startActivity(new Intent(MainActivity.this, ViewStubDemo.class));
                break;
            case R.id.zxing_demo:
                CaptureActivity.openToScan(MainActivity.this);
                break;
            case R.id.anim_demo:
                startActivity(new Intent(MainActivity.this, AnimDemo.class));
                break;
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case CaptureActivity.REQUEST_CODE_SCAN:
                if (data != null) {
                    Log.d("##MainActivity : ", data.getStringExtra(Intents.Scan.RESULT));
                } else {
                    Toast.makeText(MainActivity.this, "获取的内容为空", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
