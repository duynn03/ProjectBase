package com.example.nguyenduy.projectbase.base.listView.recycleView.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.example.nguyenduy.projectbase.BR;

import com.example.nguyenduy.projectbase.base.listView.recycleView.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecycleViewAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> implements IRecycleViewAdapter {

    private Context mContext;

    private List<T> mItems;

    public BaseRecycleViewAdapter(Context context) {
        mContext = context;
        mItems = new ArrayList<>();
    }

    public interface BaseListener {

    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BaseViewHolder(
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), getIdLayout(viewType), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.getBinding().setVariable(BR.item, mItems.get(position));
        holder.getBinding().setVariable(BR.listener, getListener());
        holder.getBinding().executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void addItem(T item) {
        mItems.add(item);
        notifyDataSetChanged();
    }

    public void addItem(int position, T item) {
        mItems.add(position, item);
        notifyDataSetChanged();
    }

    public void setItems(List<T> items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }

    public void removeAllItem() {
        mItems.clear();
        notifyDataSetChanged();
    }
}
