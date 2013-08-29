package com.yourRoute;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 5/11/13
 * Time: 5:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class Preferences {

    private static SharedPreferences sPref;
    private static Context context;
    private static String LOG_TAG = "Preferences";

    public static void initialize(Context context) {
        Preferences.context = context;
    }

    public static void saveCityId(int cityId) {

        sPref = context.getSharedPreferences("CityId", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putInt("CityId", cityId);
        ed.commit();
        Log.d(LOG_TAG, cityId + "City id saved");
    }

    public static int getSavedCityId() {

        sPref = context.getSharedPreferences("CityId", Context.MODE_PRIVATE);
        int savedCityId = sPref.getInt("CityId", 24);
        Log.i(LOG_TAG, savedCityId + "City id loaded");
        return savedCityId;
    }

    public static void saveFavoriteRouteId(int routeId) {

        sPref = context.getSharedPreferences("Favorites", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putInt("Favorite" + routeId, routeId);
        ed.commit();
        Log.d(LOG_TAG, "Route id " + routeId + "was saved to Preferences");
    }

}
