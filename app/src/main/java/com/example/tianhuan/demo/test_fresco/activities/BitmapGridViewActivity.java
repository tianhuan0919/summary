package com.example.tianhuan.demo.test_fresco.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.CursorLoader;
import android.widget.GridView;

import com.example.tianhuan.demo.R;
import com.example.tianhuan.demo.test_fresco.adapters.ImageAdapter;
import com.example.tianhuan.demo.test_fresco.utils.PhotoUtils;


/**
 * Created by tianhuan on 2017/2/14.
 */

public class BitmapGridViewActivity extends Activity {

    private GridView mGridView;
    private ImageAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_bitmap_gridview);

        initView();

        CursorLoader loader = PhotoUtils.generateAllMeidaCursorLoader(this);

        mAdapter = new ImageAdapter(loader.loadInBackground(), this);

        mGridView.setAdapter(mAdapter);
    }

    private void initView() {
        mGridView = (GridView) findViewById(R.id.bitmap_gridview);
    }

}
