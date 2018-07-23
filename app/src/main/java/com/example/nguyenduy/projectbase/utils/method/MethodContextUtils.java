package com.example.nguyenduy.projectbase.utils.method;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.nguyenduy.projectbase.application.MyApplication;

public class MethodContextUtils {

    public static void loadImage(ImageView view, String urlImage, int idPlaceholder) {
        Glide.with(MyApplication.getAppContext())
                .load(urlImage)
                .apply(new RequestOptions().placeholder(idPlaceholder))
                .into(view);
    }

    public static void setText(TextView view, Object value) {
        view.setText(String.valueOf(value));
    }

    public static String getStringInput(TextView view) {
        return view.getText().toString();
    }

    public static void setVisibility(View view, int visibility) {
        view.setVisibility(visibility);
    }

    public static View createView(Context context, int idView) {
        return LayoutInflater.from(context).inflate(idView, null, false);
    }

}
