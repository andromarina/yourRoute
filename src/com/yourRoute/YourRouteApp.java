package com.yourRoute;

import android.app.Application;
import android.util.Log;
import com.yourRoute.model.RoutesHolder;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 7/22/13
 * Time: 8:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class YourRouteApp extends Application {
    private final String LOG_TAG = "YourRouteApp";
    private static String searchPhrase = "";
    private static String optionalSearchPhrase = "";
    private static RoutesHolder routesHolder;

    @Override
    public void onCreate() {

        Log.d(LOG_TAG, "Application was created");
        super.onCreate();
        Preferences.initialize(this);
        routesHolder = new RoutesHolder(getContentResolver());
    }

    public static void saveSearchPhrase(String string) {
        searchPhrase = string;
        Log.d("YourRouteApp", "search phrase: " + searchPhrase);
    }

    public static void saveOptionalSearchPhrase(String string) {
        optionalSearchPhrase = string;
        Log.d("YourRouteApp", "search phrase optional: " + optionalSearchPhrase);
    }

    public static String getSearchPhrase() {
        return searchPhrase;
    }

    public static String getOptionalSearchPhrase() {
        return optionalSearchPhrase;
    }

    public  static RoutesHolder getRoutesHolder() {
        return routesHolder;
    }

}
