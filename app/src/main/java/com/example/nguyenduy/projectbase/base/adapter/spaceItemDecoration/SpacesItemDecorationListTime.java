package com.example.nguyenduy.projectbase.base.adapter.spaceItemDecoration;

public class SpacesItemDecorationListTime extends BaseSpacesItemDecoration {

    private int spanCount;

    public SpacesItemDecorationListTime(int left, int top, int right, int bottom, int spanCount) {
        super(left, top, right, bottom);
        this.spanCount = spanCount;
    }

    @Override
    protected int getOutRectTop(int position) {
        if (position < spanCount) {
            return 0;
        }
        return getTop();
    }

    @Override
    protected int getOutRectLeft(int position) {
        if (position % spanCount == 0) {
            return 0;
        }
        return getLeft();
    }

    @Override
    protected int getOutRectBottom(int position) {
        return getBottom();
    }

    @Override
    protected int getOutRectRight(int position) {
        return getRight();
    }
}
