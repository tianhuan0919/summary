package com.example.tianhuan.demo.test_recyclerview.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.tianhuan.demo.R;

/**
 * Created by tianhuan on 17-2-22.
 */

public class HorizontalDividerItemDecoration extends RecyclerView.ItemDecoration {

    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

    private static final int[] ATTRS = new int[] { android.R.attr.listDivider };

    private int mOrientation;
    private Drawable mDivider;

    public HorizontalDividerItemDecoration(Context ctx, int mOrientation) {
        /*int[] attrs = new int[]{R.style.list_divider};
        final TypedArray a = ctx.obtainStyledAttributes(attrs);*/
        final TypedArray a = ctx.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();

        //mDivider = ctx.getDrawable(R.drawable.list_divider);
        setOrientation(mOrientation);
    }

    public void setOrientation(int orientation){
        if(orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST){
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent) {
        super.onDraw(c, parent);
        if(mOrientation == VERTICAL_LIST){
            drawVertical(c, parent);
        } else{
            drawHorizontal(c, parent);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        if(mOrientation == VERTICAL_LIST){
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        }else{
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        }
    }

    public void drawVertical(Canvas c, RecyclerView parent){

        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for(int i=0; i<childCount; i++){
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }

    }

    public void drawHorizontal(Canvas c, RecyclerView parent){
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();
        final int childCount = parent.getChildCount();
        for(int i=0; i<childCount; i++){
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }
}
