package com.example.nguyenduy.projectbase.database;

import io.realm.RealmModel;
import io.realm.annotations.RealmClass;

@RealmClass
public class User implements RealmModel {
    private String name;
    private String password;

    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
