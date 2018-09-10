package com.example.nguyenduy.projectbase.base.listView.recycleView.adapter;

import android.content.Context;

public class SingleTypeRecycleViewAdapter<T> extends BaseRecycleViewAdapter<T> {

    protected int mIdLayoutRes;
    protected BaseListener mListener;

    public SingleTypeRecycleViewAdapter(Context context, int layoutRes, BaseListener listener) {
        super(context);
        mListener = listener;
        mIdLayoutRes = layoutRes;
    }

    @Override
    public int getIdLayout(int viewType) {
        return mIdLayoutRes;
    }

    @Override
    public BaseListener getListener() {
        return mListener;
    }


}
