package com.example.tianhuan.demo.test_zxing;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.os.AsyncTaskCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.client.android.CaptureActivity;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import java.util.Hashtable;

/**
 */
public class HScanActivity extends CaptureActivity {

    public static final int RESULT_FAILED = RESULT_FIRST_USER + 1;
    private static final int REQUEST_CODE_ALBUM_SCAN = 123;
    private static final String TAG = "HScanActivity#";
    public static final int BOX_ID_INPUT_REQUEST_CODE = 2000;

    private boolean mFirst = true;

    private Handler mScanPromptHandler;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        initTitleBar();
    }

    /**
     *
     */
    private void initTitleBar() {
        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
        mChooseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent innerIntent = new Intent(Intent.ACTION_GET_CONTENT);
                innerIntent.setType("image/*");
                Intent wrapperIntent = Intent.createChooser(innerIntent, "请选择二维码图片");
                startActivityForResult(wrapperIntent, REQUEST_CODE_ALBUM_SCAN);
            }
        });
    }

    /**
     *
     */
    private boolean initializeOverlay() {

        if (cameraManager == null) {
            return false;
        }

        final Rect rect = cameraManager.getFramingRect();
        if (rect == null) {
            return false;
        }

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mFirst) {
            mFirst = false;
            doInitOverlayUntilSuccess();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void doInitOverlayUntilSuccess() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!initializeOverlay()) {
                    doInitOverlayUntilSuccess();
                }
            }
        }, 500);
    }

    @Override
    protected void handleDecodeExternally(Result result) {
        HScanActivity.super.handleDecodeExternally(result, getBundleWithResultInspection(result));
        /*final Bundle extra = getBundleWithResultInspection(result);
        HScanActivity.super.handleDecodeExternally(result, extra);*/
        /*if(extra == null){
            setResult(RESULT_FAILED);
            finish();
        } else {
            HScanActivity.super.handleDecodeExternally(result, extra);
        }*/
    }

    @Override
    protected Result scanningImage(String path) throws NotFoundException {

        if (TextUtils.isEmpty(path)) {
            return null;

        }
        // DecodeHintType 和EncodeHintType
        Hashtable<DecodeHintType, String> hints = new Hashtable<>();
        hints.put(DecodeHintType.CHARACTER_SET, "utf-8"); // 设置二维码内容的编码
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; // 先获取原大小
        Bitmap scanBitmap = BitmapFactory.decodeFile(path, options);
        options.inJustDecodeBounds = false; // 获取新的大小
        int sampleSize = (int) (options.outHeight / (float) 200);
        if (sampleSize <= 0) {
            sampleSize = 1;
        }
        options.inSampleSize = sampleSize;
        scanBitmap = BitmapFactory.decodeFile(path, options);

        int[] ints = new int[scanBitmap.getWidth() * scanBitmap.getHeight()];
        scanBitmap.getPixels(ints, 0, scanBitmap.getWidth(), 0, 0, scanBitmap.getWidth(), scanBitmap.getHeight());
        RGBLuminanceSource source = new RGBLuminanceSource(scanBitmap.getWidth(), scanBitmap.getHeight(), ints);
        BinaryBitmap bitmap1 = new BinaryBitmap(new HybridBinarizer(source));
        QRCodeReader reader = new QRCodeReader();

        Result result = null;

        try {
           result = reader.decode(bitmap1);
        } catch (ChecksumException | FormatException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_ALBUM_SCAN) {//从相册读取
            try {
                Uri uri = data.getData();
                final String photo_path = getPathFromUri(this, uri);

                AsyncTask<Object, Object, Result> task = new AsyncTask<Object, Object, Result>() {
                    @Override
                    protected Result doInBackground(Object... params) {
                        Result result = null;
                        try {
                            result = scanningImage(photo_path);
                        } catch (NotFoundException e) {
                        }
                        return result;
                    }

                    @Override
                    protected void onPostExecute(Result result) {
                        Bundle extra = getBundleWithResultInspection(result);
                        if (extra != null) {
                            HScanActivity.super.handleDecodeExternally(result, extra);
                        } else {
                            Toast.makeText(HScanActivity.this, "未发现二维码，轻触屏幕继续扫码", Toast.LENGTH_LONG)
                                    .show();
                        }
                    }
                };
                AsyncTaskCompat.executeParallel(task);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(HScanActivity.this, "未发现二维码，轻触屏幕继续扫码", Toast.LENGTH_LONG)
                        .show();
                /*setResult(RESULT_FAILED);
                HScanActivity.this.finish();*/
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private Bundle getBundleWithResultInspection(Result result) {
        if (result != null && !TextUtils.isEmpty(result.toString())) {
            final Bundle mapRequest = UrlParseTool.URLRequestWithBundle(result.toString());
            if (mapRequest != null && mapRequest.size() != 0) {
                return mapRequest;
            }
        }
        return null;
    }
}
