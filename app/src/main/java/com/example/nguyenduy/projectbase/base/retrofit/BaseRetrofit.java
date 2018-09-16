package com.example.nguyenduy.projectbase.base.retrofit;

import com.example.nguyenduy.projectbase.base.BaseActivity;
import com.example.nguyenduy.projectbase.base.broadcast.system.network.ConnectionInternetUtils;
import com.example.nguyenduy.projectbase.base.listView.recycleView.adapter2.BaseSingleTypeAdapterRecycleView;
import com.example.nguyenduy.projectbase.base.retrofit.model.BaseResponseGet;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.nguyenduy.projectbase.base.listView.recycleView.RecycleViewConstants.Status.AVAILABLE_RESULT;
import static com.example.nguyenduy.projectbase.base.listView.recycleView.RecycleViewConstants.Status.LAST_RESULT;
import static com.example.nguyenduy.projectbase.base.listView.recycleView.RecycleViewConstants.Status.LOADING_RESULT;
import static com.example.nguyenduy.projectbase.base.listView.recycleView.RecycleViewConstants.Status.NOT_RESULT;

public abstract class BaseRetrofit<T> {

    private T api;

    protected BaseRetrofit(Class<T> clazz) {
        api = RetrofitUtils.create(clazz);
    }

    public interface IGetListener<T> {
        void onResponse(BaseResponseGet<T> response);

        void onFailure(Throwable throwable);
    }

    /*nếu không dùng adapter thì return null*/
    protected <T> void get(BaseSingleTypeAdapterRecycleView<T> adapter, BaseActivity activity, Call<BaseResponseGet<T>> call, IGetListener<T> listener) {
        ConnectionInternetUtils.isConnectInternet(activity, new ConnectionInternetUtils.CallbackConnectionInternetListener() {
            @Override
            public void onResult(boolean isConnectInternet) {
                if (!isConnectInternet) return;
                if (null == adapter)
                    activity.showDialogLoading();
                else
                    adapter.setStatusResult(LOADING_RESULT);
                call.enqueue(new Callback<BaseResponseGet<T>>() {
                    @Override
                    public void onResponse(Call<BaseResponseGet<T>> call, Response<BaseResponseGet<T>> response) {
                        if (null == adapter)
                            activity.hideDialogLoading();
                        else {
                            setStatusResultAdapter(adapter, response.body());
                            adapter.setPageCurrent(adapter.getPageCurrent() + 1);
                        }
                        listener.onResponse(response.body());
                    }

                    @Override
                    public void onFailure(Call<BaseResponseGet<T>> call, Throwable throwable) {
                        if (null == adapter)
                            activity.hideDialogLoading();
                        else
                            adapter.setStatusResult(AVAILABLE_RESULT);
                        listener.onFailure(throwable);
                    }
                });
            }
        });
    }

    private <T> void setStatusResultAdapter(BaseSingleTypeAdapterRecycleView<T> adapter, BaseResponseGet<T> response) {
        if (MethodUtils.isEmpty(response.getData())) {
            adapter.setStatusResult(NOT_RESULT);
            return;
        }

        if (response.getPageCurrent() < response.getTotalPage())
            adapter.setStatusResult(AVAILABLE_RESULT);
        else if (response.getPageCurrent() == response.getTotalPage()) {
            adapter.setStatusResult(LAST_RESULT);
        }
    }

    public T getApi() {
        return api;
    }
}
