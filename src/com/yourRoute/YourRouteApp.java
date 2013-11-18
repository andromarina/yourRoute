package com.yourRoute;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import com.yourRoute.model.Favorites;
import com.yourRoute.model.RoutesHolder;
import com.yourRoute.model.Selections;
import maps.Map;

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
    private static Selections selections;
    private static Favorites favorites;
    private static Map map;
    public static Context context;

    @Override
    public void onCreate() {

        Log.d(LOG_TAG, "Application was created");
        super.onCreate();
        Preferences.initialize(this);
        context = this;
        routesHolder = new RoutesHolder(getContentResolver());
        selections = new Selections();
        favorites = new Favorites();
        map = new Map();
    }

    public  static RoutesHolder getRoutesHolder() {
        return routesHolder;
    }

    public static Selections getSelections() {
        return selections;
    }

    public static Favorites getFavorites() {
        return favorites;
    }

    public static Map getMap() {
        return map;
    }

    public static Context getYourRouteAppContext() {
        return context;
    }
}
