package com.example.nguyenduy.projectbase.base.architectureComponents.database.repository.dao;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Update;

import java.util.List;

// https://gist.github.com/florina-muntenescu/1c78858f286d196d545c038a71a3e864
// https://gist.github.com/clxy/5e51e9eb000ca07b7a7adb6bde767ff0
public interface BaseDao<T> {

    /**
     * insert
     *
     * @param objects
     * @return list id row inserted
     */
    @Insert(onConflict = OnConflictStrategy.ROLLBACK)
    List<Long> insert(List<T> objects);

    /**
     * insert
     *
     * @param objects
     * @return list id row inserted
     */
    @Insert(onConflict = OnConflictStrategy.ROLLBACK)
    long[] insert(T... objects);

    /**
     * insert
     *
     * @param object
     * @return id row inserted
     */
    @Insert(onConflict = OnConflictStrategy.ROLLBACK)
    long insert(T object);

    /**
     * update
     *
     * @param objects
     * @return number of rows has been updated success
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    int update(List<T> objects);

    /**
     * update
     *
     * @param objects
     * @return number of rows has been updated success
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    int update(T... objects);

    /**
     * update
     *
     * @param object
     * @return number of rows has been updated success
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    int update(T object);

    /**
     * delete
     *
     * @param objects
     * @return number of rows has been deleted success
     */
    @Delete
    int delete(List<T> objects);

    /**
     * delete
     *
     * @param objects
     * @return number of rows has been deleted success
     */
    @Delete
    int delete(T... objects);

    /**
     * delete
     *
     * @param object
     * @return number of rows has been deleted success
     */
    @Delete
    int delete(T object);
}
