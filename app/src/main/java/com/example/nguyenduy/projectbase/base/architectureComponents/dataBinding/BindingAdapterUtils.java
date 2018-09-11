package com.example.nguyenduy.projectbase.base.architectureComponents.dataBinding;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;
import com.example.nguyenduy.projectbase.utils.method.ViewUtils;
import com.example.nguyenduy.projectbase.utils.method.WindowUtils;

public class BindingAdapterUtils {

    public static final String TAG = MethodUtils.getTagClass(BindingAdapterUtils.class);

    /*requireAll: false nghĩa là không yêu cầu tất cả các attribute*/
    @BindingAdapter(value = {"imageUrl", "placeHolder"}, requireAll = false)
    public static void loadImage(ImageView view, String url, Drawable placeHolder) {
        if (TextUtils.isEmpty(url)) view.setImageDrawable(placeHolder);
        else ViewUtils.setImage(view, url, placeHolder);
    }

    @BindingAdapter(value = "android:layout_width")
    public static void setWidth(View view, int widthDesign) {
        LogUtils.d(TAG + "setWidth: " + widthDesign);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        ViewUtils.setWidthHeightNormal(
                view,
                WindowUtils.convertWidth(widthDesign),
                layoutParams.height);
    }

    @BindingAdapter(value = "android:layout_height")
    public static void setHeight(View view, int heightDesign) {
        LogUtils.d(TAG + "setHeight: " + heightDesign);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        ViewUtils.setWidthHeightNormal(
                view,
                layoutParams.width,
                WindowUtils.convertHeight(heightDesign));
    }

    @BindingAdapter(value = "android:paddingLeft")
    public static void setPaddingLeft(View view, int paddingDesign) {
        LogUtils.d(TAG + "setPaddingLeft: " + paddingDesign);
        ViewUtils.setPaddingNormal(
                view,
                WindowUtils.convertWidth(paddingDesign),
                view.getPaddingTop(),
                view.getPaddingRight(),
                view.getPaddingBottom());
    }

    @BindingAdapter(value = "android:paddingRight")
    public static void setPaddingRight(View view, int paddingDesign) {
        LogUtils.d(TAG + "setPaddingRight: " + paddingDesign);
        ViewUtils.setPaddingNormal(
                view,
                view.getPaddingLeft(),
                view.getPaddingTop(),
                WindowUtils.convertWidth(paddingDesign),
                view.getPaddingBottom());
    }

    @BindingAdapter(value = "android:paddingBottom")
    public static void setPaddingBottom(View view, int paddingDesign) {
        LogUtils.d(TAG + "setPaddingBottom: " + paddingDesign);
        ViewUtils.setPaddingNormal(
                view,
                view.getPaddingLeft(),
                view.getPaddingTop(),
                view.getPaddingRight(),
                WindowUtils.convertHeight(paddingDesign));
    }

    @BindingAdapter(value = "android:paddingTop")
    public static void setPaddingTop(View view, int paddingDesign) {
        LogUtils.d(TAG + "setPaddingTop: " + paddingDesign);
        ViewUtils.setPaddingNormal(
                view,
                view.getPaddingLeft(),
                WindowUtils.convertHeight(paddingDesign),
                view.getPaddingRight(),
                view.getPaddingBottom());
    }

    @BindingAdapter(value = "android:padding")
    public static void setPadding(View view, int paddingDesign) {
        LogUtils.d(TAG + "setPadding: " + paddingDesign);
        ViewUtils.setPaddingNormal(
                view,
                WindowUtils.convertWidth(paddingDesign),
                WindowUtils.convertHeight(paddingDesign),
                WindowUtils.convertWidth(paddingDesign),
                WindowUtils.convertHeight(paddingDesign));
    }

    @BindingAdapter(value = "android:paddingTopExcludeStatusBar")
    public static void setPaddingTopExcludeStatusBar(View view, int paddingDesign) {
        LogUtils.d(TAG + "setPaddingTopExcludeStatusBar: " + paddingDesign);
        ViewUtils.setPaddingNormal(
                view,
                view.getPaddingLeft(),
                WindowUtils.convertHeightExcludeStatusBar(paddingDesign),
                view.getPaddingRight(),
                view.getPaddingBottom());
    }

    @BindingAdapter(value = "android:paddingTopIncludeStatusBar")
    public static void setPaddingTopIncludeStatusBar(View view, int paddingDesign) {
        LogUtils.d(TAG + "setPaddingTopIncludeStatusBar: " + paddingDesign);
        ViewUtils.setPaddingNormal(
                view,
                view.getPaddingLeft(),
                WindowUtils.convertHeightIncludeStatusBar(paddingDesign),
                view.getPaddingRight(),
                view.getPaddingBottom());
    }

    @BindingAdapter(value = "android:layout_marginLeft")
    public static void setLayoutMarginLeft(View view, int marginDesign) {
        LogUtils.d(TAG + "setLayoutMarginLeft: " + marginDesign);
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        ViewUtils.setMarginNormal(
                view,
                WindowUtils.convertWidth(marginDesign),
                layoutParams.topMargin,
                layoutParams.rightMargin,
                layoutParams.bottomMargin);
    }

    @BindingAdapter(value = "android:layout_marginRight")
    public static void setLayoutMarginRight(View view, int marginDesign) {
        LogUtils.d(TAG + "setLayoutMarginRight: " + marginDesign);
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        ViewUtils.setMarginNormal(
                view,
                layoutParams.leftMargin,
                layoutParams.topMargin,
                WindowUtils.convertWidth(marginDesign),
                layoutParams.bottomMargin);
    }

    @BindingAdapter(value = "android:layout_marginBottom")
    public static void setLayoutMarginBottom(View view, int marginDesign) {
        LogUtils.d(TAG + "setLayoutMarginBottom: " + marginDesign);
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        ViewUtils.setMarginNormal(
                view,
                layoutParams.leftMargin,
                layoutParams.topMargin,
                layoutParams.rightMargin,
                WindowUtils.convertHeight(marginDesign));
    }

    @BindingAdapter(value = "android:layout_marginTop")
    public static void setLayoutMarginTop(View view, int marginDesign) {
        LogUtils.d(TAG + "setLayoutMarginTop: " + marginDesign);
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        ViewUtils.setMarginNormal(
                view,
                layoutParams.leftMargin,
                WindowUtils.convertHeight(marginDesign),
                layoutParams.rightMargin,
                layoutParams.bottomMargin);
    }

    @BindingAdapter(value = "android:layout_margin")
    public static void setLayoutMargin(View view, int marginDesign) {
        LogUtils.d(TAG + "setLayoutMargin: " + marginDesign);
        ViewUtils.setMarginNormal(
                view,
                WindowUtils.convertWidth(marginDesign),
                WindowUtils.convertHeight(marginDesign),
                WindowUtils.convertWidth(marginDesign),
                WindowUtils.convertHeight(marginDesign));
    }

    @BindingAdapter(value = "android:layout_marginTopExcludeStatusBar")
    public static void setLayoutMarginTopExcludeStatusBar(View view, int marginDesign) {
        LogUtils.d(TAG + "setLayoutMarginTopExcludeStatusBar: " + marginDesign);
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        ViewUtils.setMarginNormal(
                view,
                layoutParams.leftMargin,
                WindowUtils.convertHeightExcludeStatusBar(marginDesign),
                layoutParams.rightMargin,
                layoutParams.bottomMargin);
    }

    @BindingAdapter(value = "android:layout_marginTopIncludeStatusBar")
    public static void setLayoutMarginTopIncludeStatusBar(View view, int marginDesign) {
        LogUtils.d(TAG + "setLayoutMarginTopIncludeStatusBar: " + marginDesign);
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        ViewUtils.setMarginNormal(
                view,
                layoutParams.leftMargin,
                WindowUtils.convertHeightIncludeStatusBar(marginDesign),
                layoutParams.rightMargin,
                layoutParams.bottomMargin);
    }
}
