package com.example.nguyenduy.projectbase.base;

public interface IBaseView {

    int getIdLayout();

    <P extends IBasePresenter> P initPresenter();

    void initViews();

    void initComponents();

    void setEvents();

    void prepareComplete();

}
