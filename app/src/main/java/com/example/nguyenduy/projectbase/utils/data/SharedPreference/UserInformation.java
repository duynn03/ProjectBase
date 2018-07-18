package com.example.nguyenduy.projectbase.utils.data.SharedPreference;

public class UserInformation {

    private String id;
    private String username;
    private String email;

    public UserInformation() {
    }

    public String getId() {
        return id;
    }

    public UserInformation setId(String id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserInformation setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserInformation setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public String toString() {
        return "UserInformation: id = " + id + " UserName = " + username + " Email = " + email;
    }
}
