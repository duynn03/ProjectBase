package com.example.nguyenduy.projectbase.base.sharedPreference;

public class UserInformation {

    private String id;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String birthDay;
    private String avatarUrl;

    public UserInformation() {
    }

    public String getId() {
        return id;
    }

    public UserInformation setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserInformation setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserInformation setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserInformation setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserInformation setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public UserInformation setBirthDay(String birthDay) {
        this.birthDay = birthDay;
        return this;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public UserInformation setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }

    @Override
    public String toString() {
        return "UserInformation: id = " + id + " UserName = " + name + " Email = " + email;
    }
}
