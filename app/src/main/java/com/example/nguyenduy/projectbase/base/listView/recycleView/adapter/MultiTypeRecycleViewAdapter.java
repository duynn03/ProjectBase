package com.example.nguyenduy.projectbase.base.listView.recycleView.adapter;

import android.content.Context;
import android.support.v4.util.ArrayMap;

import com.example.nguyenduy.projectbase.utils.method.MethodUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MultiTypeRecycleViewAdapter extends BaseRecycleViewAdapter<Object> {

    private List<Integer> mViewTypes;

    private ArrayMap<Integer, Integer> mViewTypeAndIdLayoutRess;

    public MultiTypeRecycleViewAdapter(Context context, Map<Integer, Integer> viewTypeAndIdLayoutRes, BaseListener listener) {
        super(context, listener);
        mViewTypes = new ArrayList<>();
        mViewTypeAndIdLayoutRess = new ArrayMap<>();

        if (viewTypeAndIdLayoutRes != null && !viewTypeAndIdLayoutRes.isEmpty()) {
            mViewTypeAndIdLayoutRess.putAll(viewTypeAndIdLayoutRes);
        }
    }

    public void addViewTypeAndIdLayoutRes(int viewType, int idLayoutRes) {
        mViewTypeAndIdLayoutRess.put(viewType, idLayoutRes);
    }

    @Override
    public int getItemViewType(int position) {
        return mViewTypes.get(position);
    }

    @Override
    public int getIdLayout(int viewType) {
        return mViewTypeAndIdLayoutRess.get(viewType);
    }

    public void addItem(Object item, int viewType) {
        mItems.add(item);
        mViewTypes.add(viewType);
        notifyItemInserted(mItems.size() - 1);
    }

    public void addItem(int position, Object item, int viewType) {
        mItems.add(position, item);
        mViewTypes.add(position, viewType);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mItems.remove(position);
        mViewTypes.remove(position);
        notifyItemRemoved(position);
    }

    public void removeItem(Object item) {
        int position = mItems.indexOf(item);
        if (position <= -1) return;
        removeItem(position);
    }

    public void removeAllItem(int viewType) {
        if (MethodUtils.isEmpty(mViewTypes)) return;
        for (int position = 0; position < mViewTypes.size(); position++) {
            if (viewType == mViewTypes.get(position)) {
                removeItem(position);
            }
        }
    }

    public void removeAllItem() {
        if (MethodUtils.isEmpty(mItems)) return;
        mItems.clear();
        mViewTypes.clear();
        notifyDataSetChanged();
    }

    public void setItems(List items, int viewType) {
        if (MethodUtils.isEmpty(items)) {
            addItem(null, viewType);
        } else {
            mItems.addAll(items);
            for (int i = 0; i < items.size(); ++i) {
                mViewTypes.add(viewType);
            }
            notifyDataSetChanged();
        }
    }

    public void setItems(int position, List items, int viewType) {
        if (MethodUtils.isEmpty(items)) {
            addItem(position, null, viewType);
        } else {
            mItems.addAll(position, items);
            for (int i = 0; i < items.size(); i++) {
                mViewTypes.add(position + i, viewType);
            }
            notifyItemRangeChanged(position, items.size() - position);
        }
    }

    public interface ViewType {
        int getViewType(Object item);
    }

    public void setItems(List items, ViewType viewType) {
        mItems.addAll(items);
        for (int i = 0; i < items.size(); ++i) {
            mViewTypes.add(viewType.getViewType(items.get(i)));
        }
        notifyDataSetChanged();
    }
    /* mMultiTypeAdapter.addAll(data, new MultiTypeAdapter.MultiViewTyper() {
        @Override
        public int getViewType(Object item) {
            if (item instanceof EmployerViewModel) {
                return VIEW_TYPE_EMPLOYER;
            }

            if (item instanceof EmployeeViewModel) {
                return VIEW_TYPE_EMPLOYEE;
            }

            return 0;
        }
    });*/

}
