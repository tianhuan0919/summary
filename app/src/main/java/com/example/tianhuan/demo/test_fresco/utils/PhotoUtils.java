package com.example.tianhuan.demo.test_fresco.utils;

import android.content.Context;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;
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

    /**
     *
     */
    public static final String[] STORE_IMAGES_MEDIA = {
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.BUCKET_ID,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Images.Media.DATE_ADDED,
    };

    /**
     *
     * @return
     */
    public static CursorLoader generateAllMeidaCursorLoader(Context ctx) {
        return createImageMediaLoader(ctx, null, null, MediaStore.Images.Media.DATE_ADDED + " DESC ");
    }

    /**
     *
     * @param selection
     * @return
     */
    public static CursorLoader createImageMediaLoader(Context ctx, String selection, String[] args, String order) {
        CursorLoader cursorLoader;
        cursorLoader = new CursorLoader(
                ctx,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                STORE_IMAGES_MEDIA,
                selection,
                args,
                order);
        return cursorLoader;
    }

}
