package com.example.nguyenduy.projectbase.base.listView.recycleView.adapter;

import android.content.Context;

import java.util.List;

public class SingleTypeRecycleViewAdapter<T> extends BaseRecycleViewAdapter<T> {

    private int mIdLayoutRes;

    public SingleTypeRecycleViewAdapter(Context context, int layoutRes, BaseListener listener) {
        super(context, listener);
        mIdLayoutRes = layoutRes;
    }

    @Override
    public int getIdLayout(int viewType) {
        return mIdLayoutRes;
    }

    public void addItem(T item) {
        mItems.add(item);
        notifyItemInserted(mItems.size() - 1);
    }

    public void addItem(int position, T item) {
        mItems.add(position, item);
        notifyItemInserted(position);
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

    public void removeItem(T item) {
        int position = mItems.indexOf(item);
        if (position <= -1) return;
        removeItem(position);
    }

    public void removeAllItem() {
        mItems.clear();
        notifyDataSetChanged();
    }

    public List<T> getItems() {
        return mItems;
    }

    public T getItem(int position) {
        return mItems.get(position);
    }

}
