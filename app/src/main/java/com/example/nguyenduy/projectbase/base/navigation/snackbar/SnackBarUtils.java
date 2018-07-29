package com.example.nguyenduy.projectbase.base.navigation.snackbar;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.example.nguyenduy.projectbase.R;

public class SnackBarUtils {
    // can show, dismiss, isShown

    public static void showSnackBarInTopActivity(Activity activity) {
        new SnackBarBuilder(activity.getWindow().getDecorView().findViewById(android.R.id.content)).setText("Record has deleted (Show in Activity)").build().show();
    }

    public static void showSnackBarInViewGroup(View viewParent) {
        new SnackBarBuilder(viewParent).setText("Record has deleted (Show in Fragment/ViewGroup)").build().show();
    }

    public static void showSnackBarNoAutoDismiss(Activity activity) {
        new SnackBarBuilder(activity.getWindow().getDecorView().findViewById(android.R.id.content)).setText("No Auto Dismiss").setDuration(Snackbar.LENGTH_INDEFINITE).build().show();
    }

    public static void showSnackBarPadding(Activity activity) {
        new SnackBarBuilder(activity.getWindow().getDecorView().findViewById(android.R.id.content)).setText("message padding")
                .setPadding(
                        R.dimen.activity_horizontal_margin,
                        R.dimen.activity_vertical_margin,
                        R.dimen.activity_horizontal_margin,
                        R.dimen.activity_vertical_margin)
                .build().show();
    }

    public static void showSnackBarMargin(Activity activity) {
        new SnackBarBuilder(activity.getWindow().getDecorView().findViewById(android.R.id.content)).setText("message margin")
                .setMargin(
                        R.dimen.activity_horizontal_margin,
                        0,
                        R.dimen.activity_horizontal_margin,
                        R.dimen.activity_vertical_margin)
                .build().show();
    }

    public static void showSnackBarWithButtonAction(Activity activity, View.OnClickListener listener) {
        new SnackBarBuilder(activity.getWindow().getDecorView().findViewById(android.R.id.content)).setText("message with Button Action message with Button Action message with Button Action message with Button Action message with Button Action message with Button Action")
                .setAction("Undo", listener).build().show();
    }

    public static void showSnackBarWithIcon(Activity activity, View.OnClickListener listener) {
        new SnackBarBuilder(activity.getWindow().getDecorView().findViewById(android.R.id.content)).setText("message with Icon message with Icon message with Icon message with Icon message with Icon message with Icon message with Icon message with Icon message with Icon")
                .setIcon(R.drawable.ic_menu_camera).setAction("Undo", listener).build().show();
    }

    public static void showSnackBarWithFloatingActionButtonInActivity(View floatingView) {
        new SnackBarBuilder(floatingView).setText("SnackBar with FloatingButton In Activity").build().show();
    }

    public static void showSnackBarWithFloatingActionButtonInFragment(Activity activity) {
        new SnackBarBuilder(activity.getCurrentFocus()).setText("SnackBar with FloatingButton In Fragment").build()
                .show();
    }

}
