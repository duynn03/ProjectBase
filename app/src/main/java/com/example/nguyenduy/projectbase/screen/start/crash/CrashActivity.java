package com.example.nguyenduy.projectbase.screen.start.crash;

import android.app.Activity;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseActivity;
import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;
import com.microsoft.appcenter.crashes.Crashes;
import com.microsoft.appcenter.crashes.model.ErrorReport;
import com.microsoft.appcenter.utils.async.AppCenterConsumer;

import butterknife.OnClick;

public class CrashActivity extends BaseActivity<ICrashPresenter> implements ICrashView {

    private static final String TAG = MethodUtils.getTagClass(CrashActivity.class);

    @Override
    public int getIdLayout() {
        return R.layout.activity_crash;
    }

    @Override
    public ICrashPresenter initPresenter() {
        return new CrashPresenterImp(this);
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initComponents() {

    }

    @Override
    public void setEvents() {

    }

    @Override
    public void prepareComplete() {
        getInformationCrashReport();
    }

    private void getInformationCrashReport() {
        Crashes.getLastSessionCrashReport().thenAccept(new AppCenterConsumer<ErrorReport>() {
            @Override
            public void accept(ErrorReport report) {
                if (report != null) {
                    LogUtils.e(TAG + "getLastSessionCrashReport(): " + report.getThrowable().toString());
                }
            }
        });
    }

    @OnClick(R.id.btn_back_to_continue)
    public void onClickButtonBackToContinue() {
        setResult(Activity.RESULT_OK);
        finish();
    }
}
