package com.example.nguyenduy.projectbase.utils.method;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MethodContextUtils {

    public static String getStringInput(TextView view) {
        return view.getText().toString();
    }

    public static String getStringInput(EditText view) {
        return view.getText().toString();
    }

    public static void setVisibility(View view, int visibility){
        view.setVisibility(visibility);
    }

}
