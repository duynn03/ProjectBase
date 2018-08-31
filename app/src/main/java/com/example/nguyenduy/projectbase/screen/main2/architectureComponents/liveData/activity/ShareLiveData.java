package com.example.nguyenduy.projectbase.screen.main2.architectureComponents.liveData.activity;

import android.arch.lifecycle.LiveData;
import android.support.annotation.MainThread;

import java.math.BigDecimal;

/*
public class ShareLiveData extends LiveData<BigDecimal> {
    private static ShareLiveData sInstance;
    private StockManager mStockManager;

    private SimplePriceListener mListener = new SimplePriceListener() {
        @Override
        public void onPriceChanged(BigDecimal price) {
            setValue(price);
        }
    };

    @MainThread
    public static ShareLiveData get(String symbol) {
        if (sInstance == null) {
            sInstance = new ShareLiveData(symbol);
        }
        return sInstance;
    }

    private ShareLiveData(String symbol) {
        mStockManager = new StockManager(symbol);
    }

    @Override
    protected void onActive() {
        mStockManager.requestPriceUpdates(mListener);
    }

    @Override
    protected void onInactive() {
        mStockManager.removeUpdates(mListener);
    }
}*/
