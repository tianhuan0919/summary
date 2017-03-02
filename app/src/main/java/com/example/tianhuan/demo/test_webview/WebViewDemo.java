package com.example.tianhuan.demo.test_webview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tianhuan.demo.R;

/**
 * Created by tianhuan on 17-3-2.
 */

public class WebViewDemo extends Activity {

    private WebView mWebView;

    private Handler mHandler = new Handler();

    private TextView mTitleTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.ac_webview);

        mTitleTv = (TextView) findViewById(R.id.title_tv);
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(this, "Android_Action");
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                mTitleTv.setText(title);
            }
        });

        mWebView.loadUrl("http://192.168.111.6/web.html");
        //mWebView.loadUrl("file:///android_asset/web.html");

    }

    @JavascriptInterface
    public void jsCallAndroid(){
        Log.e("WebView#", "jsCallAndroid");
        mHandler.post(new Runnable() {
            @Override
            public void run() {
               mWebView.loadUrl("javascript:MyFunction()");
            }
        });
    }
}
