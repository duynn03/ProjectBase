package com.example.nguyenduy.projectbase.base.listView.recycleView.adapter2;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.nguyenduy.projectbase.BR;
import com.example.nguyenduy.projectbase.base.listView.recycleView.BaseViewHolder;

public abstract class BaseAdapterRecycleView extends RecyclerView.Adapter<BaseViewHolder> implements IAdapterRecycleViewListener {

    @Override
    public int getItemViewType(int position) {
        return getViewType(position);
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BaseViewHolder(
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), onCreateViewHolder(viewType), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.getBinding().setVariable(BR.item, onBindViewHolderItem(getItemViewType(position), position));
        holder.getBinding().setVariable(BR.listener, onBindViewHolderListener(getItemViewType(position), position));
        holder.getBinding().executePendingBindings();
    }

}
