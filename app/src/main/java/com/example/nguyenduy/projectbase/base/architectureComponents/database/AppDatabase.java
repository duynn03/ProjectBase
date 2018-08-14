package com.example.nguyenduy.projectbase.base.architectureComponents.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.migration.Migration;

import com.example.nguyenduy.projectbase.application.MyApplication;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.repository.dao.UserDao;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.entity.User;

@Database(
        entities = {User.class},
        version = DatabaseConstants.DATABASE_VERSION)
@TypeConverters({ConverterTypeDatabase.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    private static AppDatabase database;

    public static synchronized AppDatabase getInstance() {
        if (database == null) {
            database = Room.databaseBuilder(
                    MyApplication.getAppContext(),
                    AppDatabase.class,
                    DatabaseConstants.DATABASE_NAME)
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                    .build();
        }
        return database;
    }

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL(
                    "CREATE TABLE `Fruit` (`id` INTEGER, `name` TEXT, PRIMARY KEY(`id`))");
        }
    };

    private static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL(
                    "ALTER TABLE Book ADD COLUMN pub_year INTEGER");
        }
    };


}
