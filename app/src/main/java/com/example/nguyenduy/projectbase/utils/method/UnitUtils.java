package com.example.nguyenduy.projectbase.utils.method;

public class UnitUtils {
    /*convert dp to pixel*/
    public static float convertDpToPx(int dp) {
        return dp * ResourceUtils.getResource().getDisplayMetrics().density;
    }
}
