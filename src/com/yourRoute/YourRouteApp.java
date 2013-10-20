package com.yourRoute;

import android.app.Application;
import android.util.Log;
import com.yourRoute.model.Favorites;
import com.yourRoute.model.RoutesHolder;
import com.yourRoute.model.SelectedStops;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 7/22/13
 * Time: 8:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class YourRouteApp extends Application {
    private final String LOG_TAG = "YourRouteApp";
    private static RoutesHolder routesHolder;
    private static SelectedStops selectedStops;
    private static Favorites favorites;

    @Override
    public void onCreate() {

        Log.d(LOG_TAG, "Application was created");
        super.onCreate();
        Preferences.initialize(this);
        routesHolder = new RoutesHolder(getContentResolver());
        selectedStops = new SelectedStops();
        favorites = new Favorites();
    }

    public  static RoutesHolder getRoutesHolder() {
        return routesHolder;
    }

    public static SelectedStops getSelectedStops() {
        return selectedStops;
    }

    public static Favorites getFavorites() {
        return favorites;
    }

}
