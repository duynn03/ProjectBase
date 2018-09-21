package com.example.nguyenduy.projectbase.screen.main.retrofit;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;
import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFragment extends BaseFragment<IRetrofitPresenter> implements IRetrofitView {

    private static final String TAG = MethodUtils.getTagClass(RetrofitFragment.class);

    private Retrofit retrofit;

    @Override
    public int getIdLayout() {
        return R.layout.fragment_retrofit;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new RetrofitPresenterImp(this);
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initComponents() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IMatrixApi api = retrofit.create(IMatrixApi.class);
        Call<Object> call = api.getAccounts("21.0303541,105.7844067", "2.189594,102.25008679999996", "driving", "en-EN", "false", "AIzaSyDUhsdSELhf7hKtpERCcQTpifKlDleVFuU");
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                showToast(response.body().toString());
                LogUtils.d(TAG + response.body().toString());
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                showToast(t.getMessage());
                LogUtils.e(TAG + t.getMessage());
            }
        });
    }

    @Override
    public void setEvents() {

    }

    @Override
    public void prepareComplete() {

    }
}
