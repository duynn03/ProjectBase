package com.example.nguyenduy.projectbase.screen.main.architectureComponents.butterKnife;

import android.widget.TextView;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;
import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;
import com.example.nguyenduy.projectbase.utils.method.ViewUtils;

import java.util.List;

import javax.annotation.Nullable;

import butterknife.BindView;
import butterknife.BindViews;

public class ButterKnifeFragment extends BaseFragment<IButterKnifePresenter> implements IButterKnifeView {

    public static final String TAG = MethodUtils.getTagClass(ButterKnifeFragment.class);

    @BindView(R.id.tv_bindview_single)
    TextView tvBindViewSingle;

    @Nullable
    @BindView(R.id.tv_bindview_single)
    TextView tvNull;

    @BindViews({R.id.tv_bindview_multi_view_1, R.id.tv_bindview_multi_view_2, R.id.tv_bindview_multi_view_3})
    List<TextView> tvBindMultiViews;

    @Override
    public int getIdLayout() {
        return R.layout.fragment_butter_knife;
    }

    @Override
    public IBasePresenter initPresenter() {
        return new ButterKnifePresenterImp(this);
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initComponents() {
        ViewUtils.setText(tvBindViewSingle, "Bind View Single");
        if (null == tvNull) {
            LogUtils.i(TAG + "BindView TextView Null");
        }

        ButterKnife.Action<TextView> FOR_EMPTRY_PASSWORD = new ButterKnife.Action<TextView>() {
            @Override
            public void apply(TextView textView, int index) {
                textView.setText("Password should not be blank");
            }
        };
    }

    @Override
    public void setEvents() {

    }

    @Override
    public void prepareComplete() {

    }
}
