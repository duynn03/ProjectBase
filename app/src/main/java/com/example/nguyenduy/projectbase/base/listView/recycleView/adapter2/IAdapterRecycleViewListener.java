package com.example.nguyenduy.projectbase.base.listView.recycleView.adapter2;

import android.support.annotation.LayoutRes;

public interface IAdapterRecycleViewListener {

    int getViewType(int position);

    @LayoutRes
    int onCreateViewHolder(int viewType);

    Object onBindViewHolderItem(int viewType, int position);

    IBaseAdapterListener onBindViewHolderListener(int viewType, int position);

}
