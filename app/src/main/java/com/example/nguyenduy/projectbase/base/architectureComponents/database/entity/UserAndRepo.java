package com.example.nguyenduy.projectbase.base.architectureComponents.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;

@Entity(tableName = "UserAndRepo",
        primaryKeys = {
                "user_id",
                "repo_id"
        },
        indices = {
                // Unique
                @Index(
                        value = {
                                "user_id",
                                "repo_id"
                        },
                        unique = true
                )
        },
        foreignKeys = {
                @ForeignKey(
                        entity = User.class,
                        parentColumns = "id",
                        childColumns = "user_id"
                ),
                @ForeignKey(
                        entity = Repo.class,
                        parentColumns = "id",
                        childColumns = "repo_id"
                )
        }
)
public class UserAndRepo {

    @ColumnInfo(name = "user_id")
    private int userId;

    @ColumnInfo(name = "repo_id")
    private int repoId;

    public UserAndRepo() {
    }

    @Ignore
    public UserAndRepo(int userId, int repoId) {
        this.userId = userId;
        this.repoId = repoId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRepoId() {
        return repoId;
    }

    public void setRepoId(int repoId) {
        this.repoId = repoId;
    }
}
