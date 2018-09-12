package com.example.nguyenduy.projectbase.base.listView.swipe;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class SwipeUtils {

    private SwipeCallback swipeCallback;

    public SwipeUtils(RecyclerView recyclerView) {
        SwipeCallback swipeCallback = new SwipeCallback();
        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeCallback);
        itemTouchhelper.attachToRecyclerView(recyclerView);
    }


}
