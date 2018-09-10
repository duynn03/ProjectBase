package com.example.nguyenduy.projectbase.screen.main.architectureComponents.butterKnife;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;
import com.example.nguyenduy.projectbase.utils.LogUtils;
import com.example.nguyenduy.projectbase.utils.method.MethodUtils;
import com.example.nguyenduy.projectbase.utils.method.ViewUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ButterKnifeFragment extends BaseFragment<IButterKnifePresenter> implements IButterKnifeView {

    public static final String TAG = MethodUtils.getTagClass(ButterKnifeFragment.class);

    @BindView(R.id.tv_bindview_single)
    TextView tvBindViewSingle;

    @Nullable // Optional
    @BindView(R.id.tv_broadcast_system)
    TextView tvNull;

    @BindViews({R.id.tv_bindview_multi_view_1, R.id.tv_bindview_multi_view_2, R.id.tv_bindview_multi_view_3})
    List<TextView> tvBindMultiViews;

    // bind Resource
    //@BindBool()
    //@BindInt()
    //@BindFloat()
    //@BindString()
    //@BindArray()

    //@BindColor()
    //@BindBitmap()
    //@BindDimen()
    //@BindDrawable()

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
            LogUtils.i(TAG + "Bind View TextView Null");
        }

        /*MultiView*/
        ButterKnife.Action<TextView> applyTextForMultiView = new ButterKnife.Action<TextView>() {
            @Override
            public void apply(@NonNull TextView view, int index) {
                view.setText("Bind View Multi View " + (index + 1));
            }
        };
        ButterKnife.apply(tvBindMultiViews, applyTextForMultiView);
    }

    @Override
    public void setEvents() {

    }

    @Override
    public void prepareComplete() {

    }

    @OnClick(R.id.btn_bindclick)
    // private void onClickButtonBindClick(View view)
    // private void onClickButtonBindClick(Button buton)
    public void onClickButtonBindClick() {
        showToast("onClickButtonBindClick");
    }

    //@OnItemLongClick
//    @OnItemClick(R.id.tweet_list)
//    private void onItemClickList(int position) {
//        // React to tweet click.
//    }
}
