package com.example.nguyenduy.projectbase.screen.main2.architectureComponents.room.livedata;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.nguyenduy.projectbase.base.architectureComponents.database.AppDatabase;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.entity.User;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.repository.dao.UserDao;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserDao userDao;
    private LiveData<List<User>> users;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userDao = AppDatabase.getInstance().userDao();
    }

    public LiveData<List<User>> getUsers() {
        if (users == null) {
            users = userDao.getAllUserLiveData();
        }
        return users;
    }

}
