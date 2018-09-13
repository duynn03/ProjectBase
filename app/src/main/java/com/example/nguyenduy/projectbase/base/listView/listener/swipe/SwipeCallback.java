package com.example.nguyenduy.projectbase.base.listView.listener.swipe;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.example.nguyenduy.projectbase.utils.method.ViewUtils;

import static android.support.v7.widget.helper.ItemTouchHelper.ACTION_STATE_SWIPE;
import static android.support.v7.widget.helper.ItemTouchHelper.END;
import static android.support.v7.widget.helper.ItemTouchHelper.START;

/*https://medium.com/fueled-engineering/swipe-drag-bind-recyclerview-817408125530*/
/*https://codeburst.io/android-swipe-menu-with-recyclerview-8f28a235ff28*/
public class SwipeCallback extends ItemTouchHelper.SimpleCallback {

    public interface SwipeCallBackListener {

        void onItemSwiped(int position);

    }

    private Drawable drawableLeft;
    private Drawable drawableRight;
    private Paint paintLeft;
    private Paint paintRight;
    private SwipeCallBackListener listenerSwipeLeft;
    private SwipeCallBackListener listenerSwipeRight;
    private boolean isEnabled;

    private SwipeCallback(int dragDirs, int swipeDirs) {
        super(dragDirs, swipeDirs);
    }

    private SwipeCallback(Builder builder) {
        this(builder.dragDirections, builder.swipeDirections);
        setPaintColor(paintLeft = new Paint(Paint.ANTI_ALIAS_FLAG), builder.bgColorSwipeLeft);
        setPaintColor(paintRight = new Paint(Paint.ANTI_ALIAS_FLAG), builder.bgColorSwipeRight);
        drawableLeft = builder.drawableSwipeLeft;
        drawableRight = builder.drawableSwipeRight;
        isEnabled = builder.isEnabled;
        listenerSwipeLeft = builder.listenerSwipeLeft;
        listenerSwipeRight = builder.listenerSwipeRight;
    }

    private void setPaintColor(Paint paint, int color) {
        paint.setColor(color);
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return isEnabled;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        if (direction == START && null != listenerSwipeLeft) {
            listenerSwipeLeft.onItemSwiped(position);
        } else if (direction == END && null != listenerSwipeRight) {
            listenerSwipeRight.onItemSwiped(position);
        }
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

        if (actionState == ACTION_STATE_SWIPE) {

            View itemView = viewHolder.itemView;
            float height = (float) itemView.getBottom() - (float) itemView.getTop();
            float width = height / 3;

            if (dX > 0) {
                RectF background = new RectF((float) itemView.getLeft(), (float) itemView.getTop(), dX, (float) itemView.getBottom());
                RectF iconDest = new RectF((float) itemView.getLeft() + width, (float) itemView.getTop() + width, (float) itemView.getLeft() + 2 * width, (float) itemView.getBottom() - width);
                c.drawRect(background, paintLeft);
                c.drawBitmap(ViewUtils.covertDrawableToBitmap(drawableLeft), null, iconDest, paintLeft);
            } else {
                RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(), (float) itemView.getRight(), (float) itemView.getBottom());
                RectF iconDest = new RectF((float) itemView.getRight() - 2 * width, (float) itemView.getTop() + width, (float) itemView.getRight() - width, (float) itemView.getBottom() - width);
                c.drawRect(background, paintRight);
                c.drawBitmap(ViewUtils.covertDrawableToBitmap(drawableRight), null, iconDest, paintRight);
            }
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

    public static final class Builder {

        private int dragDirections;
        private int swipeDirections;
        private Drawable drawableSwipeLeft;
        private Drawable drawableSwipeRight;
        private int bgColorSwipeLeft;
        private int bgColorSwipeRight;
        private SwipeCallBackListener listenerSwipeLeft;
        private SwipeCallBackListener listenerSwipeRight;
        private boolean isEnabled;

        public Builder(int dragDirections, int swipeDirections) {
            this.dragDirections = dragDirections;
            this.swipeDirections = swipeDirections;
        }

        public Builder drawableSwipeLeft(Drawable drawable) {
            drawableSwipeLeft = drawable;
            return this;
        }

        public Builder drawableSwipeRight(Drawable drawable) {
            drawableSwipeRight = drawable;
            return this;
        }

        public Builder bgColorSwipeLeft(int bgColor) {
            bgColorSwipeLeft = bgColor;
            return this;
        }

        public Builder bgColorSwipeRight(int bgColor) {
            bgColorSwipeRight = bgColor;
            return this;
        }

        public Builder listenerSwipeLeft(SwipeCallBackListener listener) {
            listenerSwipeLeft = listener;
            return this;
        }

        public Builder listenerSwipeRight(SwipeCallBackListener listener) {
            listenerSwipeRight = listener;
            return this;
        }

        public Builder setEnabled(boolean enabled) {
            isEnabled = enabled;
            return this;
        }

        public SwipeCallback build() {
            return new SwipeCallback(this);
        }
    }
}