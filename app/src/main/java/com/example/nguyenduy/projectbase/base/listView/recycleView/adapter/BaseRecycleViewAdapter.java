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

    protected List<T> mItems;

    private BaseListener mListener;

    public BaseRecycleViewAdapter(Context context, BaseListener listener) {
        mListener = listener;
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
        holder.getBinding().setVariable(BR.listener, mListener);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
