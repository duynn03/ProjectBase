package com.example.nguyenduy.projectbase.base.architectureComponents.database.dto;

import android.arch.persistence.room.ColumnInfo;

public class UserDto {

    public UserDto() {
    }

    @ColumnInfo(name = "first_name")
    private String firstName;

    @ColumnInfo(name = "last_name")
    private String lastName;
}
