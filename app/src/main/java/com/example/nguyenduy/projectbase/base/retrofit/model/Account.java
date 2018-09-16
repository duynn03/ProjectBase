package com.example.nguyenduy.projectbase.base.retrofit.model;

import com.example.nguyenduy.projectbase.base.listView.recycleView.ObjectSingleType;
import com.google.gson.annotations.SerializedName;

public class Account extends ObjectSingleType {

    @SerializedName("accountId")
    private int id;

    @SerializedName("email")
    private String email;

    @SerializedName("fullname")
    private String fullName;

    @SerializedName("username")
    private String userName;

    public Account() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public Account setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public Account setFullName(String fullname) {
        this.fullName = fullname;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public Account setUserName(String userName) {
        this.userName = userName;
        return this;
    }
}
