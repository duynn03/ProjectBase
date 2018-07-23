package com.example.nguyenduy.projectbase.utils.method;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.nguyenduy.projectbase.application.MyApplication;

public class ViewUtils {

    public static void setText(TextView view, int stringId) {
        view.setText(String.valueOf(ResourceUtils.getString(stringId)));
    }

    public static void setText(TextView view, Object value) {
        view.setText(String.valueOf(value));
    }

    public static String getText(TextView view) {
        return view.getText().toString();
    }

    public static void setImage(ImageView view, int drawableId) {
        view.setImageDrawable(ResourceUtils.getDrawable(drawableId));
    }

    public static void loadImage(ImageView view, String urlImage, int idPlaceholder) {
        Glide.with(MyApplication.getAppContext())
                .load(urlImage)
                .apply(new RequestOptions().placeholder(idPlaceholder))
                .into(view);
    }

    public static void setColor(TextView view, int colorId) {
        view.setTextColor(ResourceUtils.getColor(colorId));
    }

    public static void setVisibility(View view, int visibility) {
        view.setVisibility(visibility);
    }

    public static View createView(Context context, int idView) {
        return LayoutInflater.from(context).inflate(idView, null, false);
    }

    // widthDesign, heightDesign normal
    public static void setWidthHeightNormal(View view, int widthDesign, int heightDesign) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = widthDesign;
        params.height = heightDesign;
        view.setLayoutParams(params);
        view.requestLayout();
    }

    // LayoutParams.MATCH_PARENT
    // widthDesign, heightDesign có thể là LayoutParams.MATCH_PARENT hoặc là id trong dimen.xml
    public static void setWidthHeight(View view, int widthDesign, int heightDesign) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = WindowUtils.convertWidth(widthDesign <= 0 ? widthDesign : ResourceUtils.getDimension(widthDesign));
        params.height = WindowUtils.convertHeight(heightDesign <= 0 ? heightDesign : ResourceUtils.getDimension(heightDesign));
        view.setLayoutParams(params);
        view.requestLayout();
    }

    public static void setWidthHeightImage(ImageView view, int widthDesign, int heightDesign) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        int heightActual = WindowUtils.convertHeight(ResourceUtils.getDimension(heightDesign));
        params.width = heightActual * ResourceUtils.getDimension(widthDesign) / ResourceUtils.getDimension(heightDesign);
        params.height = heightActual;
        view.setLayoutParams(params);
        view.requestLayout();
    }

    public static void setMargin(View view, int leftDesign, int topDesign, int rightDesign, int bottomDesign) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        params.setMargins(
                WindowUtils.convertWidth(leftDesign <= 0 ? leftDesign : ResourceUtils.getDimension(leftDesign)),
                WindowUtils.convertHeight(topDesign <= 0 ? topDesign : ResourceUtils.getDimension(topDesign)),
                WindowUtils.convertWidth(rightDesign <= 0 ? rightDesign : ResourceUtils.getDimension(rightDesign)),
                WindowUtils.convertHeight(bottomDesign <= 0 ? bottomDesign : ResourceUtils.getDimension(bottomDesign)));
        view.setLayoutParams(params);
        view.requestLayout();
    }

    public static void setPadding(View view, int leftDesign, int topDesign, int rightDesign, int bottomDesign) {
        view.setPadding(
                WindowUtils.convertWidth(leftDesign <= 0 ? leftDesign : ResourceUtils.getDimension(leftDesign)),
                WindowUtils.convertHeight(topDesign <= 0 ? topDesign : ResourceUtils.getDimension(topDesign)),
                WindowUtils.convertWidth(rightDesign <= 0 ? rightDesign : ResourceUtils.getDimension(rightDesign)),
                WindowUtils.convertHeight(bottomDesign <= 0 ? bottomDesign : ResourceUtils.getDimension(bottomDesign)));
        view.requestLayout();
    }

}
