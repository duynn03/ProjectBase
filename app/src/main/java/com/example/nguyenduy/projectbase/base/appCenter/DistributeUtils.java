package com.example.nguyenduy.projectbase.base.appCenter;

import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.distribute.Distribute;

public class DistributeUtils {

    public DistributeUtils() {
        Distribute.setListener(new MyDistributeListener());
        Distribute.setEnabled(true);
        AppCenter.start(Distribute.class);

    }
}
