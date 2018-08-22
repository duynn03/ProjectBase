package com.example.nguyenduy.projectbase.base.architectureComponents.database.repository.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.nguyenduy.projectbase.base.architectureComponents.database.dto.UserAndAccountDto;

import java.util.List;

@Dao
public interface UserAndAccountDao{

    @Query("SELECT * FROM User")
    List<UserAndAccountDto> getAllUsersAndAccounts();

}
