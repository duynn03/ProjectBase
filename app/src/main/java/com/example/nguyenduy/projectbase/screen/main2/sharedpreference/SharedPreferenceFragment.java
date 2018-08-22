package com.example.nguyenduy.projectbase.screen.main2.sharedpreference;

import android.content.SharedPreferences;
import android.widget.Button;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;
import com.example.nguyenduy.projectbase.base.sharedPreference.SharedPreferenceUtils;
import com.example.nguyenduy.projectbase.screen.main2.sharedpreference.settingActivity.SettingsActivity;
import com.example.nguyenduy.projectbase.screen.main2.sharedpreference.settingActivity.header.SettingHeaderActivity;
import com.example.nguyenduy.projectbase.screen.main2.sharedpreference.settingFragment.SettingsFragment;
import com.example.nguyenduy.projectbase.utils.method.ViewUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class SharedPreferenceFragment extends BaseFragment<ISharedPreferencePresenter> implements ISharedPreferenceView, SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String KEY_SHARED_PREFERENCE_NUMBER = "KEY_SHARED_PREFERENCE_NUMBER";

    @Override
    public int getIdLayout() {
        return R.layout.fragment_sharedpreference;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new SharedPreferencePresenterImp(this);
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

    }

    @BindView(R.id.btn_number)
    Button btnNumber;

    @OnClick(R.id.btn_number)
    public void onClickButtonNumber() {
        int number = Integer.parseInt(btnNumber.getText().toString());
        SharedPreferenceUtils.getInstance().setValue(KEY_SHARED_PREFERENCE_NUMBER, number + 1);
        ViewUtils.setText(btnNumber, SharedPreferenceUtils.getInstance().getValue(KEY_SHARED_PREFERENCE_NUMBER, -1) + "");
    }

    @OnClick(R.id.btn_register_change_data_share_prefrence)
    public void registerOnSharedPreferenceChangeListener() {
        SharedPreferenceUtils.getInstance().registerOnSharedPreferenceChangeListener(this);
    }

    @OnClick(R.id.btn_unregister_change_data_share_prefrence)
    public void unRegisterOnSharedPreferenceChangeListener() {
        SharedPreferenceUtils.getInstance().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (!KEY_SHARED_PREFERENCE_NUMBER.equals(key)) {
            return;
        }
        showToast("SharedPreference: key: " + key + ", value: " + SharedPreferenceUtils.getInstance().getValue(key, -1));
    }

    @OnClick(R.id.btn_open_setting_activity_shared_preference)
    public void onClickButtonSettingActivitySharedPreference() {
        getRootActivity().startActivity(SettingHeaderActivity.class, null);
    }

    @OnClick(R.id.btn_open_setting_fragment_shared_preference)
    public void onClickButtonSettingFragmentSharedPreference() {
        getRootActivity().replaceFragment(new SettingsFragment(), false);
    }
}
