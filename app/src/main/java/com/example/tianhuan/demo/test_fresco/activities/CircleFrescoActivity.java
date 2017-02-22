package com.example.tianhuan.demo.test_fresco.activities;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import com.example.tianhuan.demo.R;
import com.example.tianhuan.demo.test_fresco.utils.ImageLoader;
import com.example.tianhuan.demo.test_fresco.utils.PhotoUtils;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by tianhuan on 17-2-16.
 */

public class CircleFrescoActivity extends Activity {

    // 圆形图片
    private SimpleDraweeView mCircleView;
    // 带边框效果
    private SimpleDraweeView mBorderCircleView;
    // 左边圆弧效果
    private SimpleDraweeView mLeftCircleView;
    // 动画效果
    private SimpleDraweeView mFadeView;
    // 加载res下图片
    private SimpleDraweeView mLocalView;
    // 加载gif图片
    private SimpleDraweeView mGifView;
    // 高斯模糊效果
    private SimpleDraweeView mBlurView;

    // img url
    private static final String imgUrl = "http://ww3.sinaimg.cn/large/610dc034jw1f6m4aj83g9j20zk1hcww3.jpg";
    private static final String gifUrl = "http://img4.178.com/acg1/201506/227753817857/227754566617.gif";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_circle_fresco);

        initView();
        setViews();
    }

    private void setViews() {
        Uri uri = Uri.parse(imgUrl);
        mCircleView.setImageURI(uri);
        mBorderCircleView.setImageURI(uri);
        mLeftCircleView.setImageURI(uri);
        mFadeView.setImageURI(uri);
        // 加载res 下的图片
        String localImageUrl = "res:///" + R.drawable.beautiful;
        Uri localUri = Uri.parse("res:///" + R.drawable.beautiful);
        mLocalView.setImageURI(localUri);
        PhotoUtils.loadImage(mGifView, gifUrl);

        ImageLoader.loadImageBlur(mBlurView, localImageUrl);
    }

    private void initView() {
        mCircleView = (SimpleDraweeView) findViewById(R.id.circle_fresco_img);
        mBorderCircleView = (SimpleDraweeView) findViewById(R.id.border_circle_fresco);
        mLeftCircleView = (SimpleDraweeView) findViewById(R.id.left_circle_fresco_img);
        mFadeView = (SimpleDraweeView) findViewById(R.id.anim_fade_fresco);
        mLocalView = (SimpleDraweeView) findViewById(R.id.local_fresco_img);
        mGifView = (SimpleDraweeView) findViewById(R.id.gif_fresco_img);
        mBlurView = (SimpleDraweeView) findViewById(R.id.blur_fresco_img);
    }
}
