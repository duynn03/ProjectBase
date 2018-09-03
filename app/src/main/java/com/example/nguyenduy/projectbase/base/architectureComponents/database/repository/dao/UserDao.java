package com.example.nguyenduy.projectbase.base.architectureComponents.database.repository.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.nguyenduy.projectbase.base.architectureComponents.database.dto.UserDto;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.entity.User;

import java.util.List;

@Dao
public interface UserDao extends BaseDao<User> {

    // nên ghi hết các field, không nên dùng *
    @Query("SELECT * FROM User")
    List<User> getAll();

    // nên ghi hết các field, không nên dùng *
    @Query("SELECT DISTINCT last_name FROM User")
    List<String> getAllDistinct();

    // param có thể là array hoặc list
    // VD: LiveData<List<User>> getByID(List<int> ids);
    @Query("SELECT * FROM User WHERE id IN (:ids)")
    List<User> getByID(int... ids);

    @Query("SELECT * FROM User WHERE id == :id")
    User getByID(int id);

    @Query("SELECT * FROM User WHERE first_name LIKE :name OR last_name LIKE :name")
    List<User> getByNames(String name);

    @Query("SELECT * FROM User WHERE first_name LIKE :name OR last_name LIKE :name LIMIT 1")
    User getByName(String name);

    // get field first_name and last_name
    // có thể sử dụng @Embedded để thay thế Dto
    @Query("SELECT first_name, last_name FROM User LIMIT 1")
    UserDto getUserDto();

    @Query("DELETE FROM User")
    void deleteAll();

    // nên ghi hết các field, không nên dùng *
    @Query("SELECT * FROM User")
    LiveData<List<User>> getAllUserLiveData();

    @Query("SELECT * FROM User WHERE id == :id")
    LiveData<User> getByIDLiveData(int id);
}
