package com.example.nguyenduy.projectbase.base.listView.recycleView.adapter2;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.listView.listener.drag.DragListener;
import com.example.nguyenduy.projectbase.base.listView.listener.swipe.SwipeListener;
import com.example.nguyenduy.projectbase.base.listView.recycleView.ObjectSingleType;
import com.example.nguyenduy.projectbase.base.navigation.snackbar.SnackBarBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.nguyenduy.projectbase.base.listView.recycleView.RecycleViewConstants.Status.LOADING_RESULT;
import static com.example.nguyenduy.projectbase.base.listView.recycleView.RecycleViewConstants.Type.FOOTER;
import static com.example.nguyenduy.projectbase.base.listView.recycleView.RecycleViewConstants.Type.HEADER;
import static com.example.nguyenduy.projectbase.base.listView.recycleView.RecycleViewConstants.Type.ITEM;

public abstract class BaseSingleTypeAdapterRecycleView<T> extends BaseAdapterRecycleView implements DragListener, SwipeListener {

    private Activity activity;

    private IBaseAdapterListener headerListener;
    private IBaseAdapterListener itemListener;

    private int statusResult;
    private int pageCurrent;

    private List<T> mItems;

    public BaseSingleTypeAdapterRecycleView(Activity activity, IBaseAdapterListener headerListener, IBaseAdapterListener itemListener) {
        this.activity = activity;
        mItems = new ArrayList<>();
        this.headerListener = headerListener;
        this.itemListener = itemListener;
        statusResult = LOADING_RESULT;
        pageCurrent = 0;
    }

    @Override
    public int getViewType(int position) {
        if (position == 0)
            return HEADER;
        else if (position <= mItems.size())
            return ITEM;
        else if (position == getItemCount() - 1)
            return FOOTER;
        else
            return -1;
    }

    @Override
    public int onCreateViewHolder(int viewType) {
        switch (viewType) {
            case HEADER:
                return getLayoutIdHeader();
            case ITEM:
                return getLayoutIdItem();
            case FOOTER:
                return R.layout.fragment_recycle_view_type_footer;
            default:
                return -1;
        }
    }

    protected abstract @LayoutRes
    int getLayoutIdHeader();

    protected abstract @LayoutRes
    int getLayoutIdItem();

    @Override
    public int getItemCount() {
        return 1 + mItems.size() + 1;
    }

    @Override
    public Object onBindViewHolderItem(int viewType, int position) {
        switch (viewType) {
            case HEADER:
                return getObjectHeader();
            case ITEM:
                position = position - 1; // trừ position của header
                return mItems.get(position);
            case FOOTER:
                return statusResult;
            default:
                return null;
        }
    }

    protected abstract Object getObjectHeader();

    @Override
    public IBaseAdapterListener onBindViewHolderListener(int viewType, int position) {
        switch (viewType) {
            case HEADER:
                return headerListener;
            case ITEM:
                return itemListener;
            default:
                return null;
        }
    }

    public void setStatusResult(int statusResult) {
        this.statusResult = statusResult;
        notifyItemChanged(getItemCount() - 1);
    }

    public void setPageCurrent(int page) {
        pageCurrent = page;
    }

    public int getPageCurrent() {
        return pageCurrent;
    }

    /* remove position*/
    public int addItem(T item) {
        return addItem(mItems.size(), item);
    }

    public int addItem(int position, T item) {
        if (position < 0) return -1;
        if (position > mItems.size())
            position = mItems.size() - 1;
        mItems.add(position, item);
        notifyItemInserted(position + 1);
        return position;
    }

    public void setItems(List<T> items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    /*https://stackoverflow.com/questions/32886781/recyclerview-insert-remove-animation-deletes-unwanted-object/32890849#32890849*/
    public void removeItem(int position) {
        if (position >= mItems.size() || position <= -1) return;
        mItems.remove(position);
        notifyItemRemoved(position + 1);
        notifyItemRangeChanged(position + 1, getItemCount());
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
        notifyItemChanged(position + 1);
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
        int positionOfItem = position - 1;
        // chú ý: để undo thì object phải implement cloneable và override và public method clone
        ObjectSingleType objectClone = null;
        ObjectSingleType item;
        try {
            item = ((ObjectSingleType) mItems.get(positionOfItem));
            objectClone = (ObjectSingleType) item.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        removeItem(positionOfItem);
        T finalObjectClone = (T) objectClone;
        showSnackbar("Remove Left success Item Single Type With Position: " + positionOfItem, "UNDO", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != finalObjectClone)
                    addItem(positionOfItem, finalObjectClone);
            }
        });
    }

    @Override
    public void onItemSwipedRight(int position) {
        int positionOfItem = position - 1;
        // chú ý: để undo thì object phải implement cloneable và override và public method clone
        ObjectSingleType objectClone = null;
        ObjectSingleType item;
        try {
            item = ((ObjectSingleType) mItems.get(positionOfItem));
            objectClone = (ObjectSingleType) item.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        removeItem(positionOfItem);
        T finalObjectClone = (T) objectClone;
        showSnackbar("Remove Right success Item Single Type With Position: " + positionOfItem, "UNDO", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != finalObjectClone)
                    addItem(positionOfItem, finalObjectClone);
            }
        });
    }
}
