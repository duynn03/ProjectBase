package com.example.nguyenduy.projectbase.base;

public interface IBasePresenter {
    IBaseView getView();

    void onDestroy();
}
