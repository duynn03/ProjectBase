package com.example.nguyenduy.projectbase.base.architectureComponents.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.nguyenduy.projectbase.base.architectureComponents.database.entity.User;

public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(User user);

    @Query("SELECT * FROM user WHERE id = :id")
    LiveData<User> getUserByID(String id);
}
