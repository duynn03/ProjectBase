package com.example.nguyenduy.projectbase.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseAdapterRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public BaseAdapterRecyclerView(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    protected View initLayout(int id, ViewGroup parent) {
        return mLayoutInflater.inflate(id, parent, false);
    }

    protected Context getContext() {
        return mContext;
    }
}
