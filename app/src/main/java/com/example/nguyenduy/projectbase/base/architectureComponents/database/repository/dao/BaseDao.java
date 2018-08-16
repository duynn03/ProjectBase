package com.example.nguyenduy.projectbase.base.architectureComponents.database.repository.dao;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;

// https://gist.github.com/florina-muntenescu/1c78858f286d196d545c038a71a3e864
// https://gist.github.com/clxy/5e51e9eb000ca07b7a7adb6bde767ff0
public interface BaseDao<T> {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.ROLLBACK)
    void insert(T... objects);

    @Transaction
    @Insert(onConflict = OnConflictStrategy.ROLLBACK)
    void insert(T object);

    @Transaction
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(T... objects);

    @Transaction
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(T object);

    @Transaction
    @Delete
    void delete(T... objects);

    @Delete
    void delete(T object);
}
