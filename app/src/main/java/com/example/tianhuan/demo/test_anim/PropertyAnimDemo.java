package com.example.tianhuan.demo.test_anim;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tianhuan.demo.R;

/**
 * Created by tianhuan on 17-2-21.
 */

public class PropertyAnimDemo extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_property_anim);

        findViewById(R.id.objectanimator_anim_img).setOnClickListener(this);
        findViewById(R.id.alpha_property_view).setOnClickListener(this);
        findViewById(R.id.animset_img).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.objectanimator_anim_img:
                rotateyAnimation(v);
                break;
            case R.id.alpha_property_view:
                colorAnimation(v);
                break;
            case R.id.animset_img:
                // 在java代码中定义动画
                //moreAnim(v);

                // 在xml定义动画
                AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(PropertyAnimDemo.this, R.anim.animator_set);
                set.setTarget(v);
                set.start();
                break;
        }
    }

    private void moreAnim(View view) {
        final AnimatorSet set = new AnimatorSet();
        final ObjectAnimator anim1 = ObjectAnimator.ofFloat(view, "scaleX", 1, 1.2f);
        final ObjectAnimator anim2 = ObjectAnimator.ofFloat(view, "scaleY", 1, 1.2f);
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(view, "rotation", 0.0f, 360f);
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(view, "rotationX", 0.0f, 360f);
        ObjectAnimator anim5 = ObjectAnimator.ofFloat(view, "rotationY", 0.0f, 360f);
        ObjectAnimator anim6 = ObjectAnimator.ofFloat(view, "alpha", 0.5f, 1.0f);
        set.playTogether(anim3, anim4, anim5, anim6);
        set.play(anim1).with(anim2).before(anim3);
        set.setDuration(5000);
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Toast.makeText(PropertyAnimDemo.this, "动画开始了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Toast.makeText(PropertyAnimDemo.this, "动画结束", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set.start();

    }

    private void colorAnimation(View view) {
        ObjectAnimator.ofInt(view, "textColor", Color.parseColor("#ff0000"), Color.parseColor("#00ff00"), Color.parseColor("#0000ff"))
                .setDuration(3000)
                .start();
    }

    private void rotateyAnimation(View view) {

        // 透明度变化
        //.ofFloat(view, "alpha", 0.0f, 1.0f)
        // 环绕Y轴旋转360度
        // ofFloat(view, "rotationY", 0.0F, 360.0F)
        // 环绕X轴旋转360度
        // ofFloat(view, "rotationX", 0.0F, 360.0F)
        // 沿着X轴移动
        //ofFloat(view, "translationX", 0, 100)
        // 沿着Y轴移动
        //ofFloat(view, "translationY", 0, 100)
        // 横向拉伸
        //ofFloat(view, "scaleX", 1, 1.5f)
        // 纵向拉伸
        //ofFloat(view, "scaleY", 1, 1.5f)
        // rotation 用于2D旋转角度  rotationX rotationY 用于3D旋转
        ObjectAnimator.ofFloat(view, "rotation", 0.0f, 360f)
                .setDuration(3000)
                .start();
    }
}
