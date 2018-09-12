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

    /* remove position*/
    public int addItem(T item) {
        return addItem(mItems.size(), item);
    }

    public int addItem(int position, T item) {
        if (position > mItems.size()) position = mItems.size() - 1;
        mItems.add(position, item);
        notifyItemInserted(position);
        return position;
    }

    public void setItems(List<T> items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    /*https://stackoverflow.com/questions/32886781/recyclerview-insert-remove-animation-deletes-unwanted-object/32890849#32890849*/
    public void removeItem(int position) {
        if (position >= mItems.size()) return;
        mItems.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
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
