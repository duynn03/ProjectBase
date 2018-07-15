package com.example.nguyenduy.projectbase.base.adapter.viewHolder;

import android.view.View;

public interface IBaseViewHolder {

    void findViewByIds(View view);

    void initViews();

    void initComponents(View view);

    void setEvents(View view);

    void prepareComplete();
}
