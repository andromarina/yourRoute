package com.andromarina.yourRoute;

import android.app.Application;
import android.util.Log;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 7/22/13
 * Time: 8:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class YourRouteApp extends Application {
    private final String LOG_TAG = "YourRouteApp";

    @Override
    public void onCreate() {

        Log.d(LOG_TAG, "Application was created");
        super.onCreate();
        Preferences.initialize(this);
    }

    @Override
    public void registerActivityLifecycleCallbacks(ActivityLifecycleCallbacks callback) {
        Log.d(LOG_TAG, "activity lifecycle callback: " + callback);
        super.registerActivityLifecycleCallbacks(callback);
    }
}
