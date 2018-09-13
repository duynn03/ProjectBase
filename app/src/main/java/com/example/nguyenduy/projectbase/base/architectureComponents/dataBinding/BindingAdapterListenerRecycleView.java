package com.example.nguyenduy.projectbase.base.architectureComponents.dataBinding;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.nguyenduy.projectbase.base.listView.listener.drag.DragCallback;
import com.example.nguyenduy.projectbase.base.listView.listener.swipe.SwipeCallback;
import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;

import static android.support.v7.widget.helper.ItemTouchHelper.DOWN;
import static android.support.v7.widget.helper.ItemTouchHelper.END;
import static android.support.v7.widget.helper.ItemTouchHelper.START;
import static android.support.v7.widget.helper.ItemTouchHelper.UP;

public class BindingAdapterListenerRecycleView {

    public static final String TAG = MethodUtils.getTagClass(BindingAdapterListenerRecycleView.class);

    @BindingAdapter(value = {"dragListener"})
    public static void setItemDragToRecyclerView(RecyclerView recyclerView, DragCallback.DragCallbackListener listener) {

        LogUtils.d(TAG + "setItemDragToRecyclerView");

        int dragFlags = UP | DOWN;

        ItemTouchHelper.Callback dragCallback = new DragCallback
                .Builder(dragFlags, 0)
                .enabled(true)
                .listener(listener)
                .build();

        new ItemTouchHelper(dragCallback).attachToRecyclerView(recyclerView);
    }

    @BindingAdapter(value = {
            "drawableSwipeLeft", "drawableSwipeRight",
            "bgColorSwipeLeft", "bgColorSwipeRight",
            "listenerSwipeLeft", "listenerSwipeRight"}, requireAll = false)
    public static void setItemSwipeToRecyclerView(RecyclerView recyclerView,
                                                  Drawable drawableSwipeLeft, Drawable drawableSwipeRight,
                                                  int bgColorSwipeLeft, int bgColorSwipeRight,
                                                  SwipeCallback.SwipeCallBackListener listenerSwipeLeft, SwipeCallback.SwipeCallBackListener listenerSwipeRight) {

        LogUtils.d(TAG + "setItemSwipeToRecyclerView");

        int swipeFlags;
        if (null == listenerSwipeLeft && null == listenerSwipeRight)
            return;
        else if (null != listenerSwipeLeft && null == listenerSwipeRight)
            swipeFlags = START;
        else if (null == listenerSwipeLeft && null != listenerSwipeRight)
            swipeFlags = END;
        else
            swipeFlags = START | END;

        ItemTouchHelper.Callback swipeCallback = new SwipeCallback
                .Builder(0, swipeFlags)
                .bgColorSwipeLeft(bgColorSwipeLeft)
                .bgColorSwipeRight(bgColorSwipeRight)
                .drawableSwipeLeft(drawableSwipeLeft)
                .drawableSwipeRight(drawableSwipeRight)
                .setEnabled(true)
                .listenerSwipeLeft(listenerSwipeLeft)
                .listenerSwipeRight(listenerSwipeRight)
                .build();

        new ItemTouchHelper(swipeCallback).attachToRecyclerView(recyclerView);
    }
}
