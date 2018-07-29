package com.example.nguyenduy.projectbase.base.navigation.snackbar;

import android.graphics.Typeface;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.nguyenduy.projectbase.utils.method.ViewUtils;
import com.example.nguyenduy.projectbase.utils.method.WindowUtils;

// https://github.com/ankitdubey021/MySnack?utm_source=android-arsenal.com&utm_medium=referral&utm_campaign=6467
public class SnackBarBuilder {

    public static final int BUTTON_ACTION_ID = android.support.design.R.id.snackbar_action;

    private View mViewParent;
    // background color
    private Integer mBgColor;
    // text
    private String mText;
    private Integer mTextColor;
    private Float mTextSize;
    private Typeface mTypeface;
    // action
    private String mActionText;
    private Integer mActionColor;
    private View.OnClickListener mActionListener;

    private Integer mDuration;
    private Integer mIcon;

    private Integer mPaddingLeft;
    private Integer mPaddingTop;
    private Integer mPaddingRight;
    private Integer mPaddingBottom;

    private Integer mMarginLeft;
    private Integer mMarginTop;
    private Integer mMarginRight;
    private Integer mMarginBottom;

    //constructor
    public SnackBarBuilder(View viewParent) {
        mViewParent = viewParent;
        mBgColor = null;
        mTextColor = null;
        mTextSize = null;
        mActionColor = null;
        mDuration = null;
        mIcon = null;

        mPaddingLeft = null;
        mPaddingTop = null;
        mPaddingRight = null;
        mPaddingBottom = null;

        mMarginLeft = null;
        mMarginTop = null;
        mMarginRight = null;
        mMarginBottom = null;
    }

    public SnackBarBuilder setBackgroundColor(@ColorRes int color) {
        mBgColor = color;
        return this;
    }

    public SnackBarBuilder setText(String text) {
        mText = text;
        return this;
    }

    public SnackBarBuilder setTextColor(@ColorRes int color) {
        mTextColor = color;
        return this;
    }

    public SnackBarBuilder setTextSize(float textSize) {
        mTextSize = textSize;
        return this;
    }

    public SnackBarBuilder setPadding(int leftDesign, int topDesign, int rightDesign, int bottomDesign) {
        mPaddingLeft = leftDesign;
        mPaddingTop = topDesign;
        mPaddingRight = rightDesign;
        mPaddingBottom = bottomDesign;
        return this;
    }

    public SnackBarBuilder setMargin(int leftDesign, int topDesign, int rightDesign, int bottomDesign) {
        mMarginLeft = leftDesign;
        mMarginTop = topDesign;
        mMarginRight = rightDesign;
        mMarginBottom = bottomDesign;
        return this;
    }

    public SnackBarBuilder setTextTypeface(Typeface typeface) {
        mTypeface = typeface;
        return this;
    }

    public SnackBarBuilder setAction(String text, View.OnClickListener listener) {
        mActionText = text;
        mActionListener = listener;
        return this;
    }

    public SnackBarBuilder setActionColor(@ColorRes int color) {
        mActionColor = color;
        return this;
    }

    //set time (second)
    // Snackbar.LENGTH_SHORT, LENGTH_LONG, LENGTH_INDEFINITE
    public SnackBarBuilder setDuration(int duration) {
        mDuration = duration;
        return this;
    }

    //set icon
    public SnackBarBuilder setIcon(@DrawableRes int icon) {
        mIcon = icon;
        return this;
    }

    public Snackbar build() {
        Snackbar snackBar = Snackbar.make(mViewParent, mText, Snackbar.LENGTH_SHORT);
        View snackBarView = snackBar.getView();

        if (null != mPaddingLeft && null != mPaddingTop && null != mPaddingRight && null != mPaddingBottom)
            ViewUtils.setPadding(snackBarView, mPaddingLeft, mPaddingTop, mPaddingRight, mPaddingBottom);

        if (null != mMarginLeft && null != mMarginTop && null != mMarginRight && null != mMarginBottom)
            ViewUtils.setMargin(snackBarView, mMarginLeft, mMarginTop, mMarginRight, mMarginBottom);

        //setting background color
        if (null != mBgColor)
            snackBarView.setBackgroundColor(mBgColor);

        TextView tvText = snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        TextView tvAction = snackBarView.findViewById(android.support.design.R.id.snackbar_action);

        if (null != mTextColor)
            tvText.setTextColor(mTextColor);

        if (null != mTextSize) {
            tvText.setTextSize(mTextSize);
            tvAction.setTextSize(mTextSize);
        }

        if (null != mTypeface) {
            tvText.setTypeface(mTypeface);
        }

        if (null != mActionColor)
            tvAction.setTextColor(mActionColor);

        if (!TextUtils.isEmpty(mActionText) && null != mActionListener)
            snackBar.setAction(mActionText, mActionListener);

        if (null != mDuration) {
            // Snackbar.LENGTH_SHORT, LENGTH_LONG, LENGTH_INDEFINITE
            snackBar.setDuration(mDuration <= 0 ? mDuration : mDuration * 1000);
        }

        if (null != mIcon) {
            tvText.setCompoundDrawablesWithIntrinsicBounds(mIcon, 0, 0, 0);
            tvText.setCompoundDrawablePadding(WindowUtils.convertWidth(18f));
        }

        return snackBar;
    }
}
