package com.example.nguyenduy.projectbase.screen.main.architectureComponents.dataBinding.activity.twowayBinding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.nguyenduy.projectbase.BR;

public class TwoWayBinding extends BaseObservable {

    private boolean isCheckTwoWay;

    public TwoWayBinding() {
        this.isCheckTwoWay = true;
    }

    @Bindable
    public boolean isCheckTwoWay() {
        return isCheckTwoWay;
    }

    public void setIsCheckTwoWay(boolean checkTwoWay) {
        // Avoids infinite loops.
        if (isCheckTwoWay != checkTwoWay) {
            isCheckTwoWay = checkTwoWay;

            // Notify observers of a new value.
            notifyPropertyChanged(BR.checkTwoWay);
        }
    }
}