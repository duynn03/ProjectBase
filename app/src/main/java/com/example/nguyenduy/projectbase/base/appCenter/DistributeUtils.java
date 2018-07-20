package com.example.nguyenduy.projectbase.base.appCenter;

import com.example.nguyenduy.projectbase.utils.method.ResourceUtils;
import com.microsoft.appcenter.distribute.Distribute;
import com.example.nguyenduy.projectbase.R;

public class DistributeUtils {

    public DistributeUtils() {
        Distribute.setEnabled(true);
        Distribute.setListener(new CustomDistributeListener());
        Distribute.setInstallUrl(ResourceUtils.getString(R.string.install_url));
        Distribute.setApiUrl(ResourceUtils.getString(R.string.api_url));
    }
}
