package com.example.nguyenduy.projectbase.base.navigation.navigationBottom;

import com.example.nguyenduy.projectbase.base.BaseFragment;
import com.example.nguyenduy.projectbase.base.IBasePresenter;

public abstract class BaseFragmentWithNavigationBottom<P extends IBasePresenter> extends BaseFragment<P> implements NavigationBottomUtils.INavigationBottomListener {

    private NavigationBottomUtils navigationBottomUtils;

    @Override
    protected void initBaseView() {
        super.initBaseView();
        navigationBottomUtils = new NavigationBottomUtils(this, this);
    }

    public void setSelectMenuNavigationBottom(int idMenu) {
        navigationBottomUtils.setSelectMenu(idMenu);
    }

}
