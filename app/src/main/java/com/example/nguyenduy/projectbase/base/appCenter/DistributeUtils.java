package com.example.nguyenduy.projectbase.base.appCenter;

import com.microsoft.appcenter.distribute.Distribute;

public class DistributeUtils {

    public DistributeUtils() {
        Distribute.setEnabled(true);
        Distribute.setListener(new CustomDistributeListener());
    }

    private void setup(){
        
    }
}
