package com.example.nguyenduy.projectbase.screen.main2.architectureComponents.viewModel.activity;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.nguyenduy.projectbase.base.architectureComponents.database.AppDatabase;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.entity.User;

import java.util.List;

public class MyViewModel extends ViewModel {

    private MutableLiveData<List<User>> users;

    public LiveData<List<User>> getUsers() {
        if (users == null) {
            users = new MutableLiveData<>();
            loadUsers();
        }
        return users;
    }

    private void loadUsers() {
        users.setValue(AppDatabase.getInstance().userDao().getAll());
    }

    public void addUser(User user) {
        List<User> usersCurrent = users.getValue();
        usersCurrent.add(user);
        users.setValue(usersCurrent);
    }
}
