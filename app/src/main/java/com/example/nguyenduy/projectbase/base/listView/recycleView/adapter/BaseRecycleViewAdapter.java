package com.example.nguyenduy.projectbase.base.listView.recycleView.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.nguyenduy.projectbase.BR;
import com.example.nguyenduy.projectbase.base.listView.recycleView.BaseViewHolder;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;

import java.util.ArrayList;
import java.util.List;

/*https://github.com/markzhai/DataBindingAdapter*/
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
    public void onViewRecycled(@NonNull BaseViewHolder holder) {
        super.onViewRecycled(holder);
        // https://stackoverflow.com/questions/27743339/strange-behaviour-of-images-in-recyclerview
        // holder.imgSlidingIcon.setImageDrawable(null);
    }

    @Override
    public int getItemCount() {
        if (MethodUtils.isEmpty(mItems)) return 0;
        return mItems.size();
    }
}
