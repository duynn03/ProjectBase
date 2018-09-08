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
import com.example.nguyenduy.projectbase.base.architectureComponents.database.entity.Account;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.entity.Address;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.entity.Book;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.entity.Repo;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.entity.User;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.entity.UserAndRepo;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.repository.dao.AccountDao;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.repository.dao.BookDao;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.repository.dao.RepoDao;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.repository.dao.UserAndAccountDao;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.repository.dao.UserAndRepoDao;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.repository.dao.UserDao;
import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;

import java.util.ArrayList;
import java.util.List;

@Database(
        entities = {User.class, Book.class, Repo.class, UserAndRepo.class, Account.class},
        version = DatabaseConstants.DATABASE_VERSION)
@TypeConverters({ConverterTypeDatabase.class})
public abstract class AppDatabase extends RoomDatabase {

    private static final String TAG = MethodUtils.getTagClass(AppDatabase.class);

    public abstract UserDao userDao();

    public abstract RepoDao repoDao();

    public abstract UserAndRepoDao userRepoDao();

    public abstract AccountDao accountDao();

    public abstract UserAndAccountDao userAndAccountDao();

    public abstract BookDao bookDao();

    private static AppDatabase instance;

    private static String getDatabasePath() {
        return MyApplication.getAppContext().getDatabasePath(DatabaseConstants.DATABASE_NAME + ".db").getAbsolutePath();
    }

    public void closeDatabase() {
        if (null != instance)
            instance.close();
    }

    public static synchronized AppDatabase getInstance() {
        if (null == instance) {
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
        private final RepoDao repoDao;
        private final UserAndRepoDao userAndRepoDao;
        private final AccountDao accountDao;
        private final BookDao bookDao;

        List<User> users = new ArrayList<>();
        List<Book> books = new ArrayList<>();
        List<Repo> repos = new ArrayList<>();
        List<UserAndRepo> userAndRepos = new ArrayList<>();
        List<Account> accounts = new ArrayList<>();

        initDefaultDataAsyncTask(AppDatabase database) {
            userDao = database.userDao();
            repoDao = database.repoDao();
            userAndRepoDao = database.userRepoDao();
            accountDao = database.accountDao();
            bookDao = database.bookDao();

            initUser();
            initBook();
            initRepo();
            initUserAndRepo();
            initAccount();
        }

        private void initUser() {
            users.add(new User("Nguyen", "Duy1", new Address("Tố Hữu", "Hà đông", "Hà Nội", 20)));
            users.add(new User("Nguyen", "Duy2", new Address("Tố Hữu", "Hà đông", "Hà Nội", 20)));
            users.add(new User("Nguyen", "Duy3", new Address("Tố Hữu", "Hà đông", "Hà Nội", 20)));
            users.add(new User("Nguyen", "Duy4", new Address("Tố Hữu", "Hà đông", "Hà Nội", 20)));
            users.add(new User("Nguyen", "Duy5", new Address("Tố Hữu", "Hà đông", "Hà Nội", 20)));
        }

        private void initBook() {
            books.add(new Book("Book 1 User Id 1", 1));
            books.add(new Book("Book 2 User Id 1", 1));
            books.add(new Book("Book 3 User Id 1", 1));
            books.add(new Book("Book 4 User Id 2", 2));
            books.add(new Book("Book 5 User Id 3", 3));
            books.add(new Book("Book 6 User Id 4", 4));
        }

        private void initRepo() {
            repos.add(new Repo("Repo 1"));
            repos.add(new Repo("Repo 2"));
            repos.add(new Repo("Repo 3"));
            repos.add(new Repo("Repo 4"));
            repos.add(new Repo("Repo 5"));
        }

        private void initUserAndRepo() {
            userAndRepos.add(new UserAndRepo(1, 1));
            userAndRepos.add(new UserAndRepo(1, 2));
            userAndRepos.add(new UserAndRepo(1, 3));
            userAndRepos.add(new UserAndRepo(2, 1));
            userAndRepos.add(new UserAndRepo(2, 2));
            userAndRepos.add(new UserAndRepo(2, 3));
            userAndRepos.add(new UserAndRepo(2, 4));
        }

        private void initAccount() {
            accounts.add(new Account("Account 1", 1));
            accounts.add(new Account("Account 2", 1));
            accounts.add(new Account("Account 3", 2));
            accounts.add(new Account("Account 4", 2));
            accounts.add(new Account("Account 5", 3));
        }

        @Transaction
        @Override
        protected Void doInBackground(final Void... params) {
            // user
            userDao.deleteAll();
            userDao.insert(users);

            // book
            bookDao.deleteAll();
            bookDao.insert(books);

            // repo
            repoDao.deleteAll();
            repoDao.insert(repos);

            // user and repo
            userAndRepoDao.deleteAll();
            userAndRepoDao.insert(userAndRepos);

            // account
            accountDao.deleteAll();
            accountDao.insert(accounts);

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
