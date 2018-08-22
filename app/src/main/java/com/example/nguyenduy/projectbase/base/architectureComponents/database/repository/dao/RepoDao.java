package com.example.nguyenduy.projectbase.base.architectureComponents.database.repository.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.nguyenduy.projectbase.base.architectureComponents.database.entity.Book;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.entity.Repo;

@Dao
public interface RepoDao extends BaseDao<Repo> {

    @Query("DELETE FROM Repo")
    void deleteAll();

}
