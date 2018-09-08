package com.example.nguyenduy.projectbase.screen.main.architectureComponents.liveData.activity;

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
