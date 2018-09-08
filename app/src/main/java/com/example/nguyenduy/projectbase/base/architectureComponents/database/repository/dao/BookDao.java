package com.example.nguyenduy.projectbase.base.architectureComponents.database.repository.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.nguyenduy.projectbase.base.architectureComponents.database.entity.Book;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.entity.User;

@Dao
public interface BookDao extends BaseDao<Book> {

    @Query("DELETE FROM Book")
    void deleteAll();

}
