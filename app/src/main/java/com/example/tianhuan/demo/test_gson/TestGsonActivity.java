package com.example.tianhuan.demo.test_gson;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

/**
 * Created by tianhuan on 17-3-10.
 */

public class TestGsonActivity extends Activity {

    private String TAG = "TestGsonActivity#";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        volleyGet(this, "http://192.168.111.4/t.php?user_name=th&type=android");
    }

    private void volleyGet(Context ctx, String url) {
        RequestQueue mQueue = Volley.newRequestQueue(ctx);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                //
                String resultMsg = jsonObject.optString("errmsg");
                int resultCode = jsonObject.optInt("errcode");
                Log.e(TAG, "reslutMsg : " + resultMsg + "------reslutCode : " + resultCode);
                //
                TestModel model = new Gson().fromJson(jsonObject.toString(), TestModel.class);
                Log.d(TAG, model.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d(TAG, volleyError.getMessage(), volleyError);
            }
        });
        mQueue.add(jsonObjectRequest);
    }
}
