package com.example.nguyenduy.projectbase.base.listView.recycleView.adapter;

import android.app.Activity;

public class SingleTypeApiRecycleViewAdapter<T> extends SingleTypeRecycleViewAdapter<T> {

    public SingleTypeApiRecycleViewAdapter(Activity activity, int layoutRes, BaseListener listener) {
        super(activity, layoutRes, listener);
    }

}
