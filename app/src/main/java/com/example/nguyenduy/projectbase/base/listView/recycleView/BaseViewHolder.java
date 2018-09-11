package com.example.nguyenduy.projectbase.base.listView.recycleView;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

public class BaseViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

    private final T mBinding;

    public BaseViewHolder(@NonNull T binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public T getBinding() {
        return mBinding;
    }

}
