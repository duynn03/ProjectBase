package com.example.nguyenduy.projectbase.base.adapter.spaceItemDecoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseSpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int left;
    private int top;
    private int right;
    private int bottom;

    public BaseSpacesItemDecoration(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        outRect.left = getOutRectLeft(position);
        outRect.right = getOutRectRight(position);
        outRect.bottom = getOutRectBottom(position);
        outRect.top = getOutRectTop(position);
    }

    protected abstract int getOutRectTop(int position);

    protected abstract int getOutRectLeft(int position);

    protected abstract int getOutRectBottom(int position);

    protected abstract int getOutRectRight(int position);

    public int getTop() {
        return top;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public int getBottom() {
        return bottom;
    }
}