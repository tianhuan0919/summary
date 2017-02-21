package com.example.tianhuan.demo.utils;

import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

/**
 * Created by tianhuan on 17-2-17.
 */

public class PhotoUtils {

    public static void loadImage(SimpleDraweeView simpleDraweeView, String url){
        loadImage(simpleDraweeView, url, new BasePostprocessor() {
        });
    }

    public static void loadImage(SimpleDraweeView simpleDraweeView, String url, BasePostprocessor processor) {
        if (TextUtils.isEmpty(url)) {
            return;
        }

        Uri uri = Uri.parse(url);
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setRotationOptions(RotationOptions.autoRotate())
                .setPostprocessor(processor)
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(simpleDraweeView.getController())
                .build();
        simpleDraweeView.setController(controller);
    }

}
