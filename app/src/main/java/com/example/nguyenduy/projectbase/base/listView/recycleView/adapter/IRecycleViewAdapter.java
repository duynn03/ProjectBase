package com.example.nguyenduy.projectbase.base.listView.recycleView.adapter;

import android.support.annotation.LayoutRes;

public interface IRecycleViewAdapter {

    @LayoutRes
    int getIdLayout(int viewType);

    BaseRecycleViewAdapter.BaseListener getListener();
}
