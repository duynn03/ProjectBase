package com.example.nguyenduy.projectbase.base.retrofit.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseResponseGet<T> {

    @SerializedName("pageCurrent")
    private int pageCurrent;

    @SerializedName("totalPage")
    private int totalPage;

    @SerializedName("numberRecordPerPage")
    private int numberRecordPerPage;

    @SerializedName("data")
    private List<T> data;

    public int getPageCurrent() {
        return pageCurrent;
    }

    public void setPageCurrent(int pageCurrent) {
        this.pageCurrent = pageCurrent;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getNumberRecordPerPage() {
        return numberRecordPerPage;
    }

    public void setNumberRecordPerPage(int numberRecordPerPage) {
        this.numberRecordPerPage = numberRecordPerPage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
