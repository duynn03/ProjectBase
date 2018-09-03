package com.example.nguyenduy.projectbase.screen.main2.architectureComponents.viewModel.activity.shareDataMultiFragment;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {

    private MutableLiveData<Integer> number = new MutableLiveData<>();

    public void setNumber(Integer value) {
        number.setValue(value);
    }

    public LiveData<Integer> getNumber() {
        return number;
    }
}
