package com.example.nguyenduy.projectbase.screen.main.architectureComponents.dataBinding.activity;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.example.nguyenduy.projectbase.application.MyApplication;
import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;

public class BindingLogicView extends View {

    public static final String TAG = MethodUtils.getTagClass(BindingLogicView.class);

    private int color;

    public BindingLogicView(Context context) {
        super(context);
    }

    public BindingLogicView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BindingLogicView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BindingLogicView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    public void setColor(int color) {
        this.color = color;
        setBackgroundColor(color);
        Toast.makeText(MyApplication.getAppContext(), "BindingSetterView: color: " + color, Toast.LENGTH_SHORT).show();
        LogUtils.i(TAG + "setColor(): " + color);
        invalidate();
    }

    public int getColor() {
        return color;
    }

    /*public void addListener(OnColorChangeListener listener) {
        //...
    }
    public void removeListener(OnColorChangeListener listener) {

    }*/

}
