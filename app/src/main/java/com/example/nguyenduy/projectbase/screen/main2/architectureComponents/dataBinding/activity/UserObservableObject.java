package com.example.nguyenduy.projectbase.screen.main2.architectureComponents.dataBinding.activity;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.nguyenduy.projectbase.BR;

public class UserObservableObject extends BaseObservable {

    private String firstName;
    private String lastName;

    public UserObservableObject() {
    }

    @Bindable
    public String getFirstName() {
        return firstName;
    }

    @Bindable
    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        // notify update UI
        notifyPropertyChanged(BR.firstName);
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        // notify update UI
        notifyPropertyChanged(BR.lastName);
    }
}


