package com.example.nguyenduy.projectbase.base.architectureComponents.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.migration.Migration;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.nguyenduy.projectbase.application.MyApplication;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.entity.Address;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.entity.User;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.repository.dao.UserDao;
import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;

import java.util.ArrayList;
import java.util.List;

@Database(
        entities = {User.class},
        version = DatabaseConstants.DATABASE_VERSION)
@TypeConverters({ConverterTypeDatabase.class})
public abstract class AppDatabase extends RoomDatabase {

    private static final String TAG = MethodUtils.getTagClass(AppDatabase.class);

    public abstract UserDao userDao();

    private static AppDatabase instance;

    private static String getDatabasePath() {
        return MyApplication.getAppContext().getDatabasePath(DatabaseConstants.DATABASE_NAME + ".db").getAbsolutePath();
    }

    public static synchronized AppDatabase getInstance() {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    MyApplication.getAppContext(),
                    AppDatabase.class,
                    DatabaseConstants.DATABASE_NAME)
                    // .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                    // init data default
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            LogUtils.i(TAG + "getInstance(): addCallback(): onCreate()");
                            new initDefaultDataAsyncTask(instance).execute();
                        }

                        @Override
                        public void onOpen(@NonNull SupportSQLiteDatabase db) {
                            super.onOpen(db);
                            LogUtils.i(TAG + "getInstance(): addCallback(): onOpen()");
                            LogUtils.i(TAG + "getDatabasePath(): " + getDatabasePath());

                        }
                    })
                    // cho phép truy cập database trên main thread (NOT RECOMMEND)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    /**
     * init data default for instance in the background.
     * TODO
     */
    private static class initDefaultDataAsyncTask extends AsyncTask<Void, Void, Void> {

        private final UserDao userDao;
        List<User> users = new ArrayList<>();

        initDefaultDataAsyncTask(AppDatabase database) {
            userDao = database.userDao();
            initUser();
        }

        private void initUser() {
            users.add(new User("Nguyen", "Duy1", new Address("Tố Hữu", "Hà đông", "Hà Nội", 20)));
            users.add(new User("Nguyen", "Duy2", new Address("Tố Hữu", "Hà đông", "Hà Nội", 20)));
            users.add(new User("Nguyen", "Duy3", new Address("Tố Hữu", "Hà đông", "Hà Nội", 20)));
            users.add(new User("Nguyen", "Duy4", new Address("Tố Hữu", "Hà đông", "Hà Nội", 20)));
            users.add(new User("Nguyen", "Duy5", new Address("Tố Hữu", "Hà đông", "Hà Nội", 20)));
        }

        @Transaction
        @Override
        protected Void doInBackground(final Void... params) {
            userDao.deleteAll();
            userDao.insert(users.toArray(new User[users.size()]));
            return null;
        }
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
