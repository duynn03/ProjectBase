package com.example.nguyenduy.projectbase.base.appCenter;

import com.example.nguyenduy.projectbase.R;
import com.example.nguyenduy.projectbase.utils.method.ResourceUtils;
import com.microsoft.appcenter.distribute.Distribute;

public class DistributeUtils {

    public static void init() {
        Distribute.setEnabled(true);
        Distribute.setListener(new CustomDistributeListener());
        Distribute.setInstallUrl(ResourceUtils.getString(R.string.install_url));
        Distribute.setApiUrl(ResourceUtils.getString(R.string.api_url));
    }
}
