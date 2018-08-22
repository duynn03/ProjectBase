package com.example.nguyenduy.projectbase.base.architectureComponents.database.repository.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.nguyenduy.projectbase.base.architectureComponents.database.entity.Account;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.entity.Repo;

@Dao
public interface AccountDao extends BaseDao<Account> {

    @Query("DELETE FROM Account")
    void deleteAll();

}
