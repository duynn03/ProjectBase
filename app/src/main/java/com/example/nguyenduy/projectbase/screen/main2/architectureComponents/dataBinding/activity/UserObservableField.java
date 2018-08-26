package com.example.nguyenduy.projectbase.screen.main2.architectureComponents.dataBinding.activity;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;

public class UserObservableField {

    private ObservableField<String> firstName;
    private ObservableField<String> lastName;
    private ObservableInt age;

    public UserObservableField() {
    }

    public ObservableField<String> getFirstName() {
        return firstName;
    }

    public ObservableField<String> getLastName() {
        return lastName;
    }

    public void setFirstName(ObservableField<String> firstName) {
        this.firstName = firstName;
    }

    public void setLastName(ObservableField<String> lastName) {
        this.lastName = lastName;
    }

    public ObservableInt getAge() {
        return age;
    }

    public void setAge(ObservableInt age) {
        this.age = age;
    }
}


