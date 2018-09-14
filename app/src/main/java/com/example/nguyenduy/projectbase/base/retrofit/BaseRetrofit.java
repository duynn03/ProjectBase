package com.example.nguyenduy.projectbase.base.retrofit;

import com.example.nguyenduy.projectbase.base.BaseActivity;
import com.example.nguyenduy.projectbase.base.broadcast.system.network.ConnectionInternetUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class BaseRetrofit<T> {

    private T api;

    protected BaseRetrofit(Class<T> clazz) {
        api = RetrofitUtils.create(clazz);
    }

    public interface IGetListener<T> {
        void onResponse(List<T> items);

        void onFailure(Call<List<T>> call, Throwable throwable);
    }

    protected <T> void get(BaseActivity activity, Call<List<T>> call, IGetListener<T> listener) {
        ConnectionInternetUtils.isConnectInternet(activity, new ConnectionInternetUtils.CallbackConnectionInternetListener() {
            @Override
            public void onResult(boolean isConnectInternet) {
                if (!isConnectInternet) return;
                activity.showDialogLoading();
                call.enqueue(new Callback<List<T>>() {
                    @Override
                    public void onResponse(Call<List<T>> call, Response<List<T>> response) {
                        activity.hideDialogLoading();
                        listener.onResponse(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<T>> call, Throwable t) {
                        activity.hideDialogLoading();
                        listener.onFailure(call, t);
                    }
                });
            }
        });
    }

    public T getApi() {
        return api;
    }
}
