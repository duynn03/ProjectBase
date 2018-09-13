package com.example.nguyenduy.projectbase.base.listView.listener.drag;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import static android.support.v7.widget.helper.ItemTouchHelper.ACTION_STATE_IDLE;

/*https://medium.com/fueled-engineering/swipe-drag-bind-recyclerview-817408125530*/
public class DragCallback extends ItemTouchHelper.SimpleCallback {

    private static final float ALPHA_FULL = 1.0f;

    public interface DragCallbackListener {

        void onItemDragged(int source, int destination);

    }

    private boolean isEnabled;
    private DragCallbackListener listener;

    /*xác định hướng của 1 event: kéo và vuốt*/
    private DragCallback(int dragDirections, int swipeDirections) {
        super(dragDirections, swipeDirections);
    }

    private DragCallback(Builder builder) {
        this(builder.dragDirections, builder.swipeDirections);
        isEnabled = builder.isEnabled;
        listener = builder.listener;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return isEnabled;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder source, @NonNull RecyclerView.ViewHolder target) {
        if (source.getItemViewType() != target.getItemViewType()) {
            return false;
        }
        // Notify the adapter of the move
        if (null != listener) {
            listener.onItemDragged(source.getAdapterPosition(), target.getAdapterPosition());
            return true;
        }
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

    }

    /*https://medium.com/@ipaulpro/drag-and-swipe-with-recyclerview-6a6f0c422efd*/
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        // We only want the active item to change
        if (actionState != ACTION_STATE_IDLE) {
            viewHolder.itemView.setAlpha(ALPHA_FULL / 2);
            viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        viewHolder.itemView.setAlpha(ALPHA_FULL);
        viewHolder.itemView.setBackgroundColor(0);
        super.clearView(recyclerView, viewHolder);
    }

    public static final class Builder {

        private int dragDirections;
        private int swipeDirections;
        private DragCallbackListener listener;
        private boolean isEnabled;

        public Builder(int dragDirections, int swipeDirections) {
            this.dragDirections = dragDirections;
            this.swipeDirections = swipeDirections;
        }

        public Builder listener(DragCallbackListener listener) {
            this.listener = listener;
            return this;
        }

        public Builder enabled(boolean enabled) {
            isEnabled = enabled;
            return this;
        }

        public DragCallback build() {
            return new DragCallback(this);
        }
    }
}