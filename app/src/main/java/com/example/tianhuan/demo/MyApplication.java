package com.example.tianhuan.demo;

import android.app.Application;

import com.android.volley.toolbox.ImageLoader;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by tianhuan on 17-2-16.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
