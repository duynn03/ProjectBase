package com.example.nguyenduy.projectbase.utils.method;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewUtils {

    public static void setText(int stringId, TextView view) {
        view.setText(ResourceUtils.getString(stringId));
    }

    public static void setText(int stringId, EditText view) {
        view.setText(ResourceUtils.getString(stringId));
    }

    public static void setText(String value, TextView view) {
        view.setText(value);
    }

    public static void setText(String value, EditText view) {
        view.setText(value);
    }

    public static void setImage(ImageView view, int drawableId) {
        view.setImageDrawable(ResourceUtils.getDrawable(drawableId));
    }

    public static void setColor(int colorId, TextView view) {
        view.setTextColor(ResourceUtils.getColor(colorId));
    }

    public static void setColor(int colorId, EditText view) {
        view.setTextColor(ResourceUtils.getColor(colorId));
    }

}
