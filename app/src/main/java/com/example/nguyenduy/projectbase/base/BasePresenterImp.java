package com.example.nguyenduy.projectbase.base;

public class BasePresenterImp<V extends IBaseView> implements IBasePresenter {

    private V mView;

    public BasePresenterImp(V view) {
        mView = view;
    }

    @Override
    public V getView() {
        return mView;
    }

    @Override
    public void onDestroy() {
        mView = null;
    }
}
