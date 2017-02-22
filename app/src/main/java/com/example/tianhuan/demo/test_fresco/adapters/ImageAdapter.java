package com.example.tianhuan.demo.test_fresco.adapters;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.tianhuan.demo.R;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.view.SimpleDraweeView;


/**
 * Created by candy on 2017/2/14.
 */

public class ImageAdapter extends BaseAdapter {

    private Cursor cursor;
    private Context mContext;

    public ImageAdapter(Cursor cursor, Context mContext) {
        this.cursor = cursor;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return (cursor == null || cursor.isClosed()) ? 0 : cursor.getCount();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_bitmap_gridview, null);
            holder = new ViewHolder();
            holder.squareImageView = (SimpleDraweeView) convertView.findViewById(R.id.gridview_item_img);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (!cursor.isClosed() && position < cursor.getCount()) {

            // move curcor
            cursor.moveToPosition(position);

            // get image path
            final String photoPath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            if (!TextUtils.isEmpty(photoPath)) {
                Uri uri = new Uri.Builder()
                        .scheme(UriUtil.LOCAL_FILE_SCHEME)
                        .path(photoPath)
                        .build();
                Log.e("ImageAdapter : ", photoPath);
                holder.squareImageView.setImageURI(uri);
            }

        } else {
            cursor.close();
        }
        return convertView;
    }

    class ViewHolder {
        SimpleDraweeView squareImageView;
    }
}
