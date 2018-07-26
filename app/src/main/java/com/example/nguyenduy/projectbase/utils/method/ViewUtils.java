package com.example.nguyenduy.projectbase.utils.method;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.nguyenduy.projectbase.application.MyApplication;

public class ViewUtils {

    public static void setText(TextView view, @IdRes int stringId) {
        view.setText(String.valueOf(ResourceUtils.getString(stringId)));
    }

    public static void setText(TextView view, Object value) {
        view.setText(String.valueOf(value));
    }

    public static String getText(TextView view) {
        return view.getText().toString();
    }

    public static void setImage(ImageView view, @IdRes int drawableId) {
        view.setImageDrawable(ResourceUtils.getDrawable(drawableId));
    }

    public static void setImage(ImageView view, String urlImage, @DrawableRes int idPlaceholder) {
        Glide.with(MyApplication.getAppContext())
                .load(urlImage)
                .apply(new RequestOptions().placeholder(idPlaceholder))
                .into(view);
    }

    public static void setColor(TextView view, @ColorRes int colorId) {
        view.setTextColor(ResourceUtils.getColor(colorId));
    }

    public static void setVisibility(View view, int visibility) {
        view.setVisibility(visibility);
    }

    public static View createView(Context context, int idView) {
        return LayoutInflater.from(context).inflate(idView, null, false);
    }

    // width, height normal
    private static void setWidthHeightNormal(View view, int width, int height) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = width;
        params.height = height;
        view.setLayoutParams(params);
        view.requestLayout();
    }

    // set width height design
    // widthDesign, heightDesign có thể là LayoutParams.MATCH_PARENT hoặc là id trong dimen.xml
    // widthDesign, heightDesign not include statusbar
    public static void setWidthHeight(View view, int widthDesign, int heightDesign) {
        setWidthHeightNormal(
                view,
                WindowUtils.convertWidth(ResourceUtils.getDimension(widthDesign)),
                WindowUtils.convertHeightExcludeStatusBar(ResourceUtils.getDimension(heightDesign)));
    }

    // widthDesign, heightDesign bao gồm cả statusbar
    public static void setWidthHeightIncludeStatusBar(View view, int widthDesign, int heightDesign) {
        setWidthHeightNormal(
                view,
                WindowUtils.convertWidth(ResourceUtils.getDimension(widthDesign)),
                WindowUtils.convertHeightIncludeStatusBar(ResourceUtils.getDimension(heightDesign)));
    }

    public static void setWidthHeightImage(ImageView view, int widthDesign, int heightDesign) {
        int heightActual = WindowUtils.convertHeight(ResourceUtils.getDimension(heightDesign));
        setWidthHeightNormal(view,
                heightActual * ResourceUtils.getDimension(widthDesign) / ResourceUtils.getDimension(heightDesign),
                heightActual
        );
    }

    private static void setMarginNormal(View view, int left, int top, int right, int bottom) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        params.setMargins(left, top, right, bottom);
        view.setLayoutParams(params);
        view.requestLayout();
    }

    public static void setMargin(View view, int leftDesign, int topDesign, int rightDesign, int bottomDesign) {
        setMarginNormal(
                view,
                WindowUtils.convertWidth(ResourceUtils.getDimension(leftDesign)),
                WindowUtils.convertHeight(ResourceUtils.getDimension(topDesign)),
                WindowUtils.convertWidth(ResourceUtils.getDimension(rightDesign)),
                WindowUtils.convertHeight(ResourceUtils.getDimension(bottomDesign))
        );
    }

    /*topDesign include statusbar*/
    public static void setMarginIncludeStatusBar(View view, int leftDesign, int topDesign, int rightDesign, int bottomDesign) {
        setMarginNormal(
                view,
                WindowUtils.convertWidth(ResourceUtils.getDimension(leftDesign)),
                WindowUtils.convertHeightIncludeStatusBar(ResourceUtils.getDimension(topDesign)),
                WindowUtils.convertWidth(ResourceUtils.getDimension(rightDesign)),
                WindowUtils.convertHeight(ResourceUtils.getDimension(bottomDesign))

        );
    }

    /*topDesign exclude statusbar*/
    public static void setMarginExcludeStatusBar(View view, int leftDesign, int topDesign, int rightDesign, int bottomDesign) {
        setMarginNormal(
                view,
                WindowUtils.convertWidth(ResourceUtils.getDimension(leftDesign)),
                WindowUtils.convertHeightExcludeStatusBar(ResourceUtils.getDimension(topDesign)),
                WindowUtils.convertWidth(ResourceUtils.getDimension(rightDesign)),
                WindowUtils.convertHeight(ResourceUtils.getDimension(bottomDesign))

        );
    }

    private static void setPaddingNormal(View view, int left, int top, int right, int bottom) {
        view.setPadding(left, top, right, bottom);
        view.requestLayout();
    }

    public static void setPadding(View view, int leftDesign, int topDesign, int rightDesign, int bottomDesign) {
        setPaddingNormal(
                view,
                WindowUtils.convertWidth(ResourceUtils.getDimension(leftDesign)),
                WindowUtils.convertHeight(ResourceUtils.getDimension(topDesign)),
                WindowUtils.convertWidth(ResourceUtils.getDimension(rightDesign)),
                WindowUtils.convertHeight(ResourceUtils.getDimension(bottomDesign)));
    }

    // topDesign include statusbar
    public static void setPaddingIncludeStatusBar(View view, int leftDesign, int topDesign, int rightDesign, int bottomDesign) {
        setPaddingNormal(
                view,
                WindowUtils.convertWidth(ResourceUtils.getDimension(leftDesign)),
                WindowUtils.convertHeightIncludeStatusBar(ResourceUtils.getDimension(topDesign)),
                WindowUtils.convertWidth(ResourceUtils.getDimension(rightDesign)),
                WindowUtils.convertHeight(ResourceUtils.getDimension(bottomDesign)));
    }

    // topDesign exclude statusbar
    public static void setPaddingExcludeStatusBar(View view, int leftDesign, int topDesign, int rightDesign, int bottomDesign) {
        setPaddingNormal(
                view,
                WindowUtils.convertWidth(ResourceUtils.getDimension(leftDesign)),
                WindowUtils.convertHeightExcludeStatusBar(ResourceUtils.getDimension(topDesign)),
                WindowUtils.convertWidth(ResourceUtils.getDimension(rightDesign)),
                WindowUtils.convertHeight(ResourceUtils.getDimension(bottomDesign)));
    }
}
