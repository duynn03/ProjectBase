package com.example.nguyenduy.projectbase.base.listView.recycleView.adapter;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.example.nguyenduy.projectbase.base.listView.listener.drag.DragListener;
import com.example.nguyenduy.projectbase.base.listView.listener.swipe.SwipeListener;
import com.example.nguyenduy.projectbase.base.listView.recycleView.ObjectSingleType;
import com.example.nguyenduy.projectbase.base.navigation.snackbar.SnackBarBuilder;

import java.util.Collections;
import java.util.List;

public class SingleTypeRecycleViewAdapter<T> extends BaseRecycleViewAdapter<T> implements DragListener, SwipeListener {

    private Activity activity;
    private int mIdLayoutRes;

    public SingleTypeRecycleViewAdapter(Activity activity, int layoutRes, BaseListener listener) {
        super(activity, listener);

        this.activity = activity;
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

    public void replaceItem(int position, T item) {
        if (position >= mItems.size()) return;
        mItems.remove(position);
        mItems.add(position, item);
        notifyItemChanged(position);
    }

    public List<T> getItems() {
        return mItems;
    }

    public T getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public void onItemDragged(int source, int destination) {
        move(source, destination);
    }

    /*https://github.com/chetdeva/draggablerecyclerview/blob/master/app/src/main/java/com/fueled/draggablerecyclerview/adapter/RVAdapter.java*/
    private void move(int source, int destination) {
        if (source < destination) {
            for (int i = source; i < destination; i++) {
                Collections.swap(mItems, i, i + 1);
            }
        } else {
            for (int i = source; i > destination; i--) {
                Collections.swap(mItems, i, i - 1);
            }
        }
        notifyItemMoved(source, destination);
    }

    private void showSnackbar(String message, String action, View.OnClickListener listener) {
        new SnackBarBuilder(activity.getWindow().getDecorView().findViewById(android.R.id.content)).setText(message).setAction(action, listener).setDuration(Snackbar.LENGTH_SHORT).build().show();
    }

    @Override
    public void onItemSwipedLeft(int position) {
        // chú ý: để undo thì object phải implement cloneable và override và public method clone
        ObjectSingleType objectClone = null;
        ObjectSingleType item;
        try {
            item = ((ObjectSingleType) mItems.get(position));
            objectClone = (ObjectSingleType) item.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        removeItem(position);
        T finalObjectClone = (T) objectClone;
        showSnackbar("Remove Left success Item Single Type With Position: " + position, "UNDO", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != finalObjectClone)
                    addItem(position, finalObjectClone);
            }
        });
    }

    @Override
    public void onItemSwipedRight(int position) {
        // chú ý: để undo thì object phải implement cloneable và override method
        ObjectSingleType objectClone = null;
        ObjectSingleType item = null;
        try {
            item = ((ObjectSingleType) mItems.get(position));
            objectClone = (ObjectSingleType) item.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        removeItem(position);
        T finalObjectClone = (T) objectClone;
        showSnackbar("Remove Right success Item Single Type With Position: " + position, "UNDO", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != finalObjectClone)
                    addItem(position, finalObjectClone);
            }
        });
    }


}
