package com.example.nguyenduy.projectbase.base.architectureComponents.database.repository.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.nguyenduy.projectbase.base.architectureComponents.database.entity.User;

import java.util.List;

public interface UserDao {

    // nên ghi hết các field, không nên dùng *
    @Query("SELECT * FROM User")
    List<User> getAllUsers();

    @Query("SELECT * FROM User WHERE id IN (:ids)")
    // param có thể là array hoặc list
    // VD: LiveData<List<User>> getUserByID(List<int> ids);
    LiveData<List<User>> getUserByID(int... ids);

    @Query("SELECT * FROM User WHERE id == id")
    LiveData<User> getUserByID(int id);

    @Query("SELECT * FROM User WHERE first_name LIKE :name OR last_name LIKE :name")
    LiveData<List<User>> getUsersByName(String name);

    @Query("SELECT * FROM User WHERE first_name LIKE :name OR last_name LIKE :name LIMIT 1")
    LiveData<User> getUserByName(String name);

    // get field first_name and last_name
    // có thể sử dụng @Embedded để thay thế Dto
    @Query("SELECT first_name, last_name FROM User")
    LiveData<User> getUserDto();

    @Insert(onConflict = OnConflictStrategy.ROLLBACK)
    void insertUsers(User... users);

    @Insert(onConflict = OnConflictStrategy.ROLLBACK)
    void insertUser(User user);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateUsers(User... users);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateUser(User user);

    @Delete
    void deleteUsers(User... users);

    @Delete
    void deleteUser(User user);

}
