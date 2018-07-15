package com.example.nguyenduy.projectbase.base.adapter.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseViewHolder extends RecyclerView.ViewHolder implements IBaseViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
        findViewByIds(itemView);
        initViews();
        initComponents(itemView);
        setEvents(itemView);
        prepareComplete();
    }
}
