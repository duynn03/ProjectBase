package com.example.nguyenduy.projectbase.base.listView.swipe;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import static android.support.v7.widget.helper.ItemTouchHelper.DOWN;
import static android.support.v7.widget.helper.ItemTouchHelper.END;
import static android.support.v7.widget.helper.ItemTouchHelper.START;
import static android.support.v7.widget.helper.ItemTouchHelper.UP;

// ItemTouchHelper.startDrag(RecyclerView.ViewHolder)
// ItemTouchHelper.startSwipe(RecyclerView.ViewHolder)

public class SwipeCallback extends ItemTouchHelper.Callback {

    public SwipeCallback() {
    }

    /*xác định hướng của 1 event: kéo và vuốt*/
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int dragFlags = UP | DOWN;
        int swipeFlags = START | END; /*LEFT | RIGHT*/
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    /*nếu suppport Drag thì return về true*/
    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    /*nếu suppport Swipe thì return về true*/
    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

    }


    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }
}
