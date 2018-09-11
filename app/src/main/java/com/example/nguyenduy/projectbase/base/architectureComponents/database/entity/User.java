package com.example.nguyenduy.projectbase.base.architectureComponents.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "User",
        indices = {
                // Unique
                @Index(
                        value = {
                                "first_name",
                                "last_name"
                        },
                        unique = true
                )
        }
)
public class User {

    public User() {
    }

    @Ignore
    public User(String firstName, String lastName, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "first_name")
    private String firstName;

    @ColumnInfo(name = "last_name")
    private String lastName;

    @Ignore
    private String fullName;

    @Ignore
    private String avatarUrl;

    @Embedded(prefix = "home_")
    private Address address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public User setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (null != firstName) {
            builder.append("firstName: " + firstName);
        }
        if (null != lastName) {
            builder.append(", lastName: " + lastName);
        }
        if (null != fullName) {
            builder.append(", fullName: " + fullName);
        }
        if (null != address) {
            builder.append(", address: " + address.toString());
        }
        return builder.toString();
    }
}
