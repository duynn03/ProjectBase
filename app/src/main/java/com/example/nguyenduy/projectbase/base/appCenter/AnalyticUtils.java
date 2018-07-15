package com.example.nguyenduy.projectbase.base.appCenter;

import com.microsoft.appcenter.analytics.Analytics;

import java.util.HashMap;
import java.util.Map;

public class AnalyticUtils {

    public AnalyticUtils() {
        Analytics.setEnabled(true);
    }

    public void event() {
        Map<String, String> properties = new HashMap<>();
        properties.put("Category", "Music");
        properties.put("FileName", "favorite.avi");
        Analytics.trackEvent("Video clicked", properties);
        // Analytics.trackEvent("Video clicked");
    }
}
