package com.example.nguyenduy.projectbase.base.architectureComponents.database.repository.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.nguyenduy.projectbase.base.architectureComponents.database.entity.UserAndRepo;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.entity.Repo;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.entity.User;

import java.util.List;

@Dao
public interface UserAndRepoDao extends BaseDao<UserAndRepo> {

    @Query("SELECT * FROM User " +
            "INNER JOIN UserAndRepo ON User.id = UserAndRepo.user_id " +
            "WHERE UserAndRepo.repo_id = :repoId")
    List<User> getAllUsers(final int repoId);

    @Query("SELECT * FROM Repo " +
            "INNER JOIN UserAndRepo ON Repo.id = UserAndRepo.repo_id " +
            "WHERE UserAndRepo.user_id = :userId")
    List<Repo> getAllRepositories(final int userId);

    @Query("DELETE FROM UserAndRepo")
    void deleteAll();

}
