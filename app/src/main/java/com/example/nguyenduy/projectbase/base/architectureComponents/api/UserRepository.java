package com.example.nguyenduy.projectbase.base.architectureComponents.api;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.nguyenduy.projectbase.base.architectureComponents.database.dao.UserDao;
import com.example.nguyenduy.projectbase.base.architectureComponents.database.entity.User;
import com.example.nguyenduy.projectbase.base.architectureComponents.service.Webservice;

import java.io.IOException;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Response;
/*
@Singleton
public class UserRepository {

    private Webservice webservice;
    private UserDao userDao;
    private final Executor executor;
    private UserCache userCache;

    @Inject
    public UserRepository(Webservice webservice, UserDao userDao, Executor executor) {
        this.webservice = webservice;
        this.userDao = userDao;
        this.executor = executor;
    }

    // add error network ở đây: https://developer.android.com/jetpack/docs/guide#addendum
    public LiveData<User> getUserByID(String userId) {
        LiveData<User> cached = userCache.get(userId);
        if (cached != null) {
            return cached;
        }

        // query in server
*//*        final MutableLiveData<User> data = new MutableLiveData<>();
        webservice.getUser(userId).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        return data;*//*
        refreshUser(userId);
        return userDao.getUserByID(userId);
    }

    private void refreshUser(final String userId) {
        executor.execute(() -> {
            // running in a background thread
            // check if user was fetched recently
            // tự động refreshUser nếu đã update lâu rồi
            boolean userExists = userDao.hasUser(FRESH_TIMEOUT);
            if (!userExists) {
                // refresh the data
                try {
                    Response<User> response = webservice.getUser(userId).execute();
                    // Update the database.The LiveData will automatically refresh so
                    // we don't need to do anything else here besides updating the database
                    userDao.save(response.body());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public LiveData<Resource<User>> loadUser(final String userId) {
        return new NetworkBoundResource<User,User>() {
            @Override
            protected void saveCallResult(@NonNull User item) {
                userDao.insert(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable User data) {
                return rateLimiter.canFetch(userId) && (data == null || !isFresh(data));
            }

            @NonNull @Override
            protected LiveData<User> loadFromDb() {
                return userDao.load(userId);
            }

            @NonNull @Override
            protected LiveData<ApiResponse<User>> createCall() {
                return webservice.getUser(userId);
            }
        }.getAsLiveData();
    }

}*/
